package com.yz.dl.integralmanage.utils.cache;

/**
 * Created by chaman on 2017/10/13.
 */

public interface ObjectCacheListener {
    void onInitMemoryCacheFinished();

    void onInitDiskFinished();

    void onClearCacheFinished();

    void onClearMemoryCacheFinished();

    void onClearDiskCacheFinished();

    void onClearCacheFinished(String key);

    void onClearMemoryCacheFinished(String key);

    void onClearDiskCacheFinished(String key);

    void onFlushCacheFinished();

    void onCloseCacheFinished();

    void onGetObjectFromMemCacheFinished(String key);

    void onGetObjectFromDiskCacheFinished(String key);
}
