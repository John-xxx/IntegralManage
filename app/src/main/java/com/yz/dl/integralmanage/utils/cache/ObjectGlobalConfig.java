package com.yz.dl.integralmanage.utils.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import com.yz.dl.integralmanage.utils.LogUtils;
import com.yz.dl.integralmanage.utils.OtherUtils;
import com.yz.dl.integralmanage.utils.tasks.Priority;
import com.yz.dl.integralmanage.utils.tasks.PriorityAsyncTask;

import java.util.HashMap;

/**
 * Created by chaman on 2017/10/12.
 */

public class ObjectGlobalConfig {
    private String diskCachePath;
    public final static int MIN_MEMORY_CACHE_SIZE = 1024 * 1024 * 1; // 1M
    private int memoryCacheSize = 1024 * 1024 * 2; // 2MB
    public final static int MIN_DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10M
    private int diskCacheSize = 1024 * 1024 * 30;  // 30M

    private boolean memoryCacheEnabled = true;
    private boolean diskCacheEnabled = true;

    private ObjectCache objectCache;

    private long defaultCacheExpiry = 1000L * 60 * 60 * 24 * 1; // 1 days
    private int defaultConnectTimeout = 1000 * 15; // 15 sec
    private int defaultReadTimeout = 1000 * 15; // 15 sec

    private FileNameGenerator fileNameGenerator;

    private ObjectCacheListener objectCacheListener;

    private Context mContext;
    private final static HashMap<String, ObjectGlobalConfig> configMap = new HashMap<String, ObjectGlobalConfig>(1);

    /**
     * @param context
     * @param diskCachePath If null, use default appCacheDir+"/xBitmapCache"
     */
    private ObjectGlobalConfig(Context context, String diskCachePath) {
        if (context == null) throw new IllegalArgumentException("context may not be null");
        this.mContext = context;
        this.diskCachePath = diskCachePath;
        initObjectCache();
    }

    public synchronized static ObjectGlobalConfig getInstance(Context context, String diskCachePath) {

        if (TextUtils.isEmpty(diskCachePath)) {
            diskCachePath = OtherUtils.getDiskCacheDir(context, "object");
        }

        if (configMap.containsKey(diskCachePath)) {
            return configMap.get(diskCachePath);
        } else {
            ObjectGlobalConfig config = new ObjectGlobalConfig(context, diskCachePath);
            configMap.put(diskCachePath, config);
            return config;
        }
    }

    private void initObjectCache() {
        objectCache = getObjectCache();
        objectCache.initMemoryCache();
        objectCache.initDiskCache();
//        new ObjectCacheManagementTask().execute(ObjectCacheManagementTask.MESSAGE_INIT_MEMORY_CACHE);
//        new ObjectCacheManagementTask().execute(ObjectCacheManagementTask.MESSAGE_INIT_DISK_CACHE);
    }

    public String getDiskCachePath() {
        return diskCachePath;
    }

    public long getDefaultCacheExpiry() {
        return defaultCacheExpiry;
    }

    public void setDefaultCacheExpiry(long defaultCacheExpiry) {
        this.defaultCacheExpiry = defaultCacheExpiry;
    }

    public long size() {
        return getObjectCache().size();
    }

    public int getDefaultConnectTimeout() {
        return defaultConnectTimeout;
    }

    public void setDefaultConnectTimeout(int defaultConnectTimeout) {
        this.defaultConnectTimeout = defaultConnectTimeout;
    }

    public int getDefaultReadTimeout() {
        return defaultReadTimeout;
    }

    public void setDefaultReadTimeout(int defaultReadTimeout) {
        this.defaultReadTimeout = defaultReadTimeout;
    }

    public ObjectCache getObjectCache() {
        if (objectCache == null) {
            objectCache = new ObjectCache(this);
        }
        return objectCache;
    }

    public int getMemoryCacheSize() {
        return memoryCacheSize;
    }

    public void setMemoryCacheSize(int memoryCacheSize) {
        if (memoryCacheSize >= MIN_MEMORY_CACHE_SIZE) {
            this.memoryCacheSize = memoryCacheSize;
            if (objectCache != null) {
                objectCache.setMemoryCacheSize(this.memoryCacheSize);
            }
        } else {
            this.setMemCacheSizePercent(0.3f);// Set default memory cache size.
        }
    }

    /**
     * @param percent between 0.05 and 0.8 (inclusive)
     */
    public void setMemCacheSizePercent(float percent) {
        if (percent < 0.05f || percent > 0.8f) {
            throw new IllegalArgumentException("percent must be between 0.05 and 0.8 (inclusive)");
        }
        this.memoryCacheSize = Math.round(percent * getMemoryClass() * 1024 * 1024);
        if (objectCache != null) {
            objectCache.setMemoryCacheSize(this.memoryCacheSize);
        }
    }

    public int getDiskCacheSize() {
        return diskCacheSize;
    }

    public void setDiskCacheSize(int diskCacheSize) {
        if (diskCacheSize >= MIN_DISK_CACHE_SIZE) {
            this.diskCacheSize = diskCacheSize;
            if (objectCache != null) {
                objectCache.setDiskCacheSize(this.diskCacheSize);
            }
        }
    }

    public boolean isMemoryCacheEnabled() {
        return memoryCacheEnabled;
    }

    public void setMemoryCacheEnabled(boolean memoryCacheEnabled) {
        this.memoryCacheEnabled = memoryCacheEnabled;
    }

    public boolean isDiskCacheEnabled() {
        return diskCacheEnabled;
    }

    public void setDiskCacheEnabled(boolean diskCacheEnabled) {
        this.diskCacheEnabled = diskCacheEnabled;
    }

    public FileNameGenerator getFileNameGenerator() {
        return fileNameGenerator;
    }

    public void setFileNameGenerator(FileNameGenerator fileNameGenerator) {
        this.fileNameGenerator = fileNameGenerator;
        if (objectCache != null) {
            objectCache.setDiskCacheFileNameGenerator(fileNameGenerator);
        }
    }

    public ObjectCacheListener getBitmapCacheListener() {
        return objectCacheListener;
    }

    public void setObjectCacheListener(ObjectCacheListener objectCacheListener) {
        this.objectCacheListener = objectCacheListener;
    }

    private int getMemoryClass() {
        return ((ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
    }

    ////////////////////////////////// bitmap cache management task ///////////////////////////////////////
    private class ObjectCacheManagementTask extends PriorityAsyncTask<Object, Void, Object[]> {
        public static final int MESSAGE_INIT_MEMORY_CACHE = 0;
        public static final int MESSAGE_INIT_DISK_CACHE = 1;
        public static final int MESSAGE_FLUSH = 2;
        public static final int MESSAGE_CLOSE = 3;
        public static final int MESSAGE_CLEAR = 4;
        public static final int MESSAGE_CLEAR_MEMORY = 5;
        public static final int MESSAGE_CLEAR_DISK = 6;
        public static final int MESSAGE_CLEAR_BY_KEY = 7;
        public static final int MESSAGE_CLEAR_MEMORY_BY_KEY = 8;
        public static final int MESSAGE_CLEAR_DISK_BY_KEY = 9;

        private ObjectCacheManagementTask() {
            this.setPriority(Priority.UI_TOP);
        }

        @Override
        protected Object[] doInBackground(Object... params) {
            if (params == null || params.length == 0) return params;
            ObjectCache cache = getObjectCache();
            if (cache == null) return params;
            try {
                switch ((Integer) params[0]) {
                    case MESSAGE_INIT_MEMORY_CACHE:
                        cache.initMemoryCache();
                        break;
                    case MESSAGE_INIT_DISK_CACHE:
                        cache.initDiskCache();
                        break;
                    case MESSAGE_FLUSH:
                        cache.flush();
                        break;
                    case MESSAGE_CLOSE:
                        cache.clearMemoryCache();
                        cache.close();
                        break;
                    case MESSAGE_CLEAR:
                        cache.clearCache();
                        break;
                    case MESSAGE_CLEAR_MEMORY:
                        cache.clearMemoryCache();
                        break;
                    case MESSAGE_CLEAR_DISK:
                        cache.clearDiskCache();
                        break;
                    case MESSAGE_CLEAR_BY_KEY:
                        if (params.length != 2) return params;
                        cache.clearCache(String.valueOf(params[1]));
                        break;
                    case MESSAGE_CLEAR_MEMORY_BY_KEY:
                        if (params.length != 2) return params;
                        cache.clearMemoryCache(String.valueOf(params[1]));
                        break;
                    case MESSAGE_CLEAR_DISK_BY_KEY:
                        if (params.length != 2) return params;
                        cache.clearDiskCache(String.valueOf(params[1]));
                        break;
                    default:
                        break;
                }
            } catch (Throwable e) {
                LogUtils.e(e.getMessage(), e);
            }
            return params;
        }

        @Override
        protected void onPostExecute(Object[] params) {
            if (objectCacheListener == null || params == null || params.length == 0) return;
            try {
                switch ((Integer) params[0]) {
                    case MESSAGE_INIT_MEMORY_CACHE:
                        objectCacheListener.onInitMemoryCacheFinished();
                        break;
                    case MESSAGE_INIT_DISK_CACHE:
                        objectCacheListener.onInitDiskFinished();
                        break;
                    case MESSAGE_FLUSH:
                        objectCacheListener.onFlushCacheFinished();
                        break;
                    case MESSAGE_CLOSE:
                        objectCacheListener.onCloseCacheFinished();
                        break;
                    case MESSAGE_CLEAR:
                        objectCacheListener.onClearCacheFinished();
                        break;
                    case MESSAGE_CLEAR_MEMORY:
                        objectCacheListener.onClearMemoryCacheFinished();
                        break;
                    case MESSAGE_CLEAR_DISK:
                        objectCacheListener.onClearDiskCacheFinished();
                        break;
                    case MESSAGE_CLEAR_BY_KEY:
                        if (params.length != 2) return;
                        objectCacheListener.onClearCacheFinished(String.valueOf(params[1]));
                        break;
                    case MESSAGE_CLEAR_MEMORY_BY_KEY:
                        if (params.length != 2) return;
                        objectCacheListener.onClearMemoryCacheFinished(String.valueOf(params[1]));
                        break;
                    case MESSAGE_CLEAR_DISK_BY_KEY:
                        if (params.length != 2) return;
                        objectCacheListener.onClearDiskCacheFinished(String.valueOf(params[1]));
                        break;
                    default:
                        break;
                }
            } catch (Throwable e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
    }

    public void clearCache() {
        new ObjectCacheManagementTask().execute(ObjectCacheManagementTask.MESSAGE_CLEAR);
    }

    public void clearMemoryCache() {
        new ObjectCacheManagementTask().execute(ObjectCacheManagementTask.MESSAGE_CLEAR_MEMORY);
    }

    public void clearDiskCache() {
        new ObjectCacheManagementTask().execute(ObjectCacheManagementTask.MESSAGE_CLEAR_DISK);
    }

    public void clearCache(String uri) {
        new ObjectCacheManagementTask().execute(ObjectCacheManagementTask.MESSAGE_CLEAR_BY_KEY, uri);
    }

    public void clearMemoryCache(String uri) {
        new ObjectCacheManagementTask().execute(ObjectCacheManagementTask.MESSAGE_CLEAR_MEMORY_BY_KEY, uri);
    }

    public void clearDiskCache(String uri) {
        new ObjectCacheManagementTask().execute(ObjectCacheManagementTask.MESSAGE_CLEAR_DISK_BY_KEY, uri);
    }

    public void flushCache() {
        new ObjectCacheManagementTask().execute(ObjectCacheManagementTask.MESSAGE_FLUSH);
    }

    public void closeCache() {
        new ObjectCacheManagementTask().execute(ObjectCacheManagementTask.MESSAGE_CLOSE);
    }
}
