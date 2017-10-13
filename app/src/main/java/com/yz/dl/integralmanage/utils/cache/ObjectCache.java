package com.yz.dl.integralmanage.utils.cache;

import com.yz.dl.integralmanage.utils.IOUtils;
import com.yz.dl.integralmanage.utils.IntegralHashMap;
import com.yz.dl.integralmanage.utils.LogUtils;
import com.yz.dl.integralmanage.utils.OtherUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by chaman on 2017/10/12.
 */

public class ObjectCache {
    private final int DISK_CACHE_INDEX = 0;

    private LruDiskCache mDiskLruCache;
    private LruMemoryCache<String, Object> mMemoryCache;

    private final Object mDiskCacheLock = new Object();

    private ObjectGlobalConfig globalConfig;

    /**
     * Creating a new ImageCache object using the specified parameters.
     *
     * @param globalConfig The cache parameters to use to initialize the cache
     */
    public ObjectCache(ObjectGlobalConfig globalConfig) {
        if (globalConfig == null)
            throw new IllegalArgumentException("globalConfig may not be null");
        this.globalConfig = globalConfig;
    }

    /**
     * Initialize the memory cache
     */
    public void initMemoryCache() {
        if (!globalConfig.isMemoryCacheEnabled())
            return;

        // Set up memory cache
        if (mMemoryCache != null) {
            try {
                clearMemoryCache();
            } catch (Throwable e) {
            }
        }
        mMemoryCache = new LruMemoryCache<String, Object>(
                globalConfig.getMemoryCacheSize()) {
            /**
             * Measure item size in bytes rather than units which is more
             * practical for a bitmap cache
             */
            @Override
            protected int sizeOf(String key, Object object) {
                if (object == null)
                    return 0;
                if (object instanceof String)
                    return String.valueOf(object).getBytes().length;
                else if (object instanceof IntegralHashMap)
                    return object.toString().getBytes().length;
                else
                    return 1;
            }
        };
    }

    /**
     * Initializes the disk cache. Note that this includes disk access so this
     * should not be executed on the main/UI thread. By default an ImageCache
     * does not initialize the disk cache when it is created, instead you should
     * call initDiskCache() to initialize it on a background thread.
     */
    public void initDiskCache() {
        // Set up disk cache
        synchronized (mDiskCacheLock) {
            if (globalConfig.isDiskCacheEnabled()
                    && (mDiskLruCache == null || mDiskLruCache.isClosed())) {
                File diskCacheDir = new File(globalConfig.getDiskCachePath());
                if (diskCacheDir.exists() || diskCacheDir.mkdirs()) {
                    long availableSpace = OtherUtils.getAvailableSpace(diskCacheDir);
                    long diskCacheSize = globalConfig.getDiskCacheSize();
                    diskCacheSize = availableSpace > diskCacheSize ? diskCacheSize
                            : availableSpace;
                    try {
                        mDiskLruCache = LruDiskCache.open(diskCacheDir, 1, 1,
                                diskCacheSize);
                        mDiskLruCache.setFileNameGenerator(globalConfig
                                .getFileNameGenerator());
                        LogUtils.d("create disk cache success");
                    } catch (Throwable e) {
                        mDiskLruCache = null;
                        LogUtils.e("create disk cache error", e);
                    }
                }
            }
        }
    }

    public void setMemoryCacheSize(int maxSize) {
        if (mMemoryCache != null) {
            mMemoryCache.setMaxSize(maxSize);
        }
    }

    public void setDiskCacheSize(int maxSize) {
        synchronized (mDiskCacheLock) {
            if (mDiskLruCache != null) {
                mDiskLruCache.setMaxSize(maxSize);
            }
        }
    }

    public void setDiskCacheFileNameGenerator(
            FileNameGenerator fileNameGenerator) {
        synchronized (mDiskCacheLock) {
            if (mDiskLruCache != null && fileNameGenerator != null) {
                mDiskLruCache.setFileNameGenerator(fileNameGenerator);
            }
        }
    }

    public boolean addObjectToMemoryCache(String key, Object object, long expiryTimestamp)
            throws IOException {
        if (key != null
                && globalConfig.isMemoryCacheEnabled() && mMemoryCache != null) {
            key = mDiskLruCache.getFileNameGenerator().generate(key);
            if (mMemoryCache.put(key, object, expiryTimestamp) != null)
                return true;
        }
        return false;
    }

    public boolean addObjectToDiskCache(String key, Object object, long expiryTimestamp)
            throws IOException {

        if (key != null
                && globalConfig.isDiskCacheEnabled() && mDiskLruCache != null) {
            key = mDiskLruCache.getFileNameGenerator().generate(key);
            OutputStream outputStream = null;
            LruDiskCache.Editor editor = mDiskLruCache
                    .edit(key);
            if (editor != null) {
                outputStream = editor
                        .newOutputStream(DISK_CACHE_INDEX);
                // 创建对象输出流，传入文件输出流
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                // 写出object，并关闭流
                oos.writeObject(object);
                oos.flush();
                IOUtils.closeQuietly(outputStream);
                IOUtils.closeQuietly(oos);
                editor.setEntryExpiryTimestamp(expiryTimestamp);
                editor.commit();
                return true;
            }
        } else {
            LogUtils.e("addObjectToDiskCache Fail!");
        }

        return false;
    }

    /**
     * Get the object from memory cache.
     *
     * @param key Unique identifier for which item to get
     * @return The object if found in cache, null otherwise
     */
    public Object getObjectFromMemCache(String key) {
        if (mMemoryCache != null && globalConfig.isMemoryCacheEnabled()) {
            key = mDiskLruCache.getFileNameGenerator().generate(key);
            return mMemoryCache.get(key);
        }
        return null;
    }

    /**
     * Get the object file from disk cache.
     *
     * @param key Unique identifier for which item to get
     * @return The file if found in cache.
     */
    public Object getObjectFileFromDiskCache(String key) throws IOException, ClassNotFoundException {
        if (mDiskLruCache != null) {
            key = mDiskLruCache.getFileNameGenerator().generate(key);
            File file = mDiskLruCache.getCacheFile(key, DISK_CACHE_INDEX);
            if (file != null) {
                // 创建文件输入流
                FileInputStream fis = new FileInputStream(file);
                // 创建对象输入流
                ObjectInputStream ois = new ObjectInputStream(fis);
                // 返回反序列化后的对象，并关闭IO流
                Object o = ois.readObject();
                IOUtils.closeQuietly(ois);
                IOUtils.closeQuietly(fis);
                return o;
            }
            return null;
        } else {
            return null;
        }
    }


    /**
     * Clears both the memory and disk cache associated with this ImageCache
     * object. Note that this includes disk access so this should not be
     * executed on the main/UI thread.
     */
    public void clearCache() {
        clearMemoryCache();
        clearDiskCache();
    }

    public void clearMemoryCache() {
        if (mMemoryCache != null) {
            mMemoryCache.evictAll();
        }
    }

    public void clearDiskCache() {
        synchronized (mDiskCacheLock) {
            if (mDiskLruCache != null && !mDiskLruCache.isClosed()) {
                try {
                    mDiskLruCache.delete();
                    mDiskLruCache.close();
                } catch (Throwable e) {
                    LogUtils.e(e.getMessage(), e);
                }
                mDiskLruCache = null;
            }
        }
        initDiskCache();
    }

    public void clearCache(String uri) {
        clearMemoryCache(uri);
        clearDiskCache(uri);
    }

    public void clearMemoryCache(String key) {
        key = mDiskLruCache.getFileNameGenerator().generate(key);
        if (mMemoryCache != null) {
            while (mMemoryCache.containsKey(key)) {
                mMemoryCache.remove(key);
            }
        }
    }

    public void clearDiskCache(String key) {
        synchronized (mDiskCacheLock) {
            if (mDiskLruCache != null && !mDiskLruCache.isClosed()) {
                key = mDiskLruCache.getFileNameGenerator().generate(key);
                try {
                    mDiskLruCache.remove(key);
                } catch (Throwable e) {
                    LogUtils.e(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Flushes the disk cache associated with this ImageCache object. Note that
     * this includes disk access so this should not be executed on the main/UI
     * thread.
     */
    public void flush() {
        synchronized (mDiskCacheLock) {
            if (mDiskLruCache != null) {
                try {
                    mDiskLruCache.flush();
                } catch (Throwable e) {
                    LogUtils.e(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Closes the disk cache associated with this ImageCache object. Note that
     * this includes disk access so this should not be executed on the main/UI
     * thread.
     */
    public void close() {
        synchronized (mDiskCacheLock) {
            if (mDiskLruCache != null) {
                try {
                    if (!mDiskLruCache.isClosed()) {
                        mDiskLruCache.close();
                    }
                } catch (Throwable e) {
                    LogUtils.e(e.getMessage(), e);
                }
                mDiskLruCache = null;
            }
        }
    }

    /**
     * return disklrucache size
     *
     * @return
     */
    public long size() {
        return mDiskLruCache == null ? 0 : mDiskLruCache.size();
    }
}
