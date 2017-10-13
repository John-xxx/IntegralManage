/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yz.dl.integralmanage.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.yz.dl.integralmanage.utils.cache.FileNameGenerator;
import com.yz.dl.integralmanage.utils.cache.ObjectCacheListener;
import com.yz.dl.integralmanage.utils.cache.ObjectGlobalConfig;

import java.io.IOException;

public class CacheUtils {

    private Context context;
    private ObjectGlobalConfig globalConfig;
    private SharedPreferences spf;
    private final String default_base_name = "base_config";

    /////////////////////////////////////////////// create ///////////////////////////////////////////////////
    public CacheUtils(Context context) {
        this(context, null);
    }

    public CacheUtils(Context context, String diskCachePath) {
        if (context == null) {
            throw new IllegalArgumentException("context may not be null");
        }

        this.context = context.getApplicationContext();
        globalConfig = ObjectGlobalConfig.getInstance(this.context, diskCachePath);
        spf = context.getSharedPreferences(default_base_name, Context.MODE_PRIVATE);
    }

    public CacheUtils(Context context, String diskCachePath, int memoryCacheSize) {
        this(context, diskCachePath);
        globalConfig.setMemoryCacheSize(memoryCacheSize);
    }

    public CacheUtils(Context context, String diskCachePath, int memoryCacheSize, int diskCacheSize) {
        this(context, diskCachePath);
        globalConfig.setMemoryCacheSize(memoryCacheSize);
        globalConfig.setDiskCacheSize(diskCacheSize);
    }

    public CacheUtils(Context context, String diskCachePath, float memoryCachePercent) {
        this(context, diskCachePath);
        globalConfig.setMemCacheSizePercent(memoryCachePercent);
    }

    public CacheUtils(Context context, String diskCachePath, float memoryCachePercent, int diskCacheSize) {
        this(context, diskCachePath);
        globalConfig.setMemCacheSizePercent(memoryCachePercent);
        globalConfig.setDiskCacheSize(diskCacheSize);
    }

    //////////////////////////////////////// config ////////////////////////////////////////////////////////////////////

    public CacheUtils configDefaultCacheExpiry(long defaultExpiry) {
        globalConfig.setDefaultCacheExpiry(defaultExpiry);
        return this;
    }

    public CacheUtils configDefaultConnectTimeout(int connectTimeout) {
        globalConfig.setDefaultConnectTimeout(connectTimeout);
        return this;
    }

    public CacheUtils configDefaultReadTimeout(int readTimeout) {
        globalConfig.setDefaultReadTimeout(readTimeout);
        return this;
    }

    public CacheUtils configMemoryCacheEnabled(boolean enabled) {
        globalConfig.setMemoryCacheEnabled(enabled);
        return this;
    }

    public CacheUtils configDiskCacheEnabled(boolean enabled) {
        globalConfig.setDiskCacheEnabled(enabled);
        return this;
    }

    public CacheUtils configDiskCacheFileNameGenerator(FileNameGenerator fileNameGenerator) {
        globalConfig.setFileNameGenerator(fileNameGenerator);
        return this;
    }

    public CacheUtils configObjectCacheListener(ObjectCacheListener listener) {
        globalConfig.setObjectCacheListener(listener);
        return this;
    }


    /////////////////////////////////////////////// cache /////////////////////////////////////////////////////////////////

    public void clearCache() {
        globalConfig.clearCache();
    }

    public void clearMemoryCache() {
        globalConfig.clearMemoryCache();
    }

    public void clearDiskCache() {
        globalConfig.clearDiskCache();
    }

    public void clearCache(String uri) {
        globalConfig.clearCache(uri);
    }

    public void clearMemoryCache(String uri) {
        globalConfig.clearMemoryCache(uri);
    }

    public void clearDiskCache(String uri) {
        globalConfig.clearDiskCache(uri);
    }

    public void flushCache() {
        globalConfig.flushCache();
    }

    public void closeCache() {
        globalConfig.closeCache();
    }

    /**
     * return default SharePreferences
     *
     * @return
     */
    public SharedPreferences getP() {
        return spf;
    }

    /**
     * get sharedpreferences editor
     *
     * @return
     */
    public SharedPreferences.Editor getEditor() {
        return spf.edit();
    }

    /**
     * 从内存中获取缓存
     *
     * @param key
     * @return
     */
    public Object getObjectFromMemCache(String key) {
        return globalConfig.getObjectCache().getObjectFromMemCache(key);
    }

    /**
     * 从闪存中获取缓存
     *
     * @param key
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object getObjectFromDiskCache(String key) throws IOException, ClassNotFoundException {
        return globalConfig.getObjectCache().getObjectFileFromDiskCache(key);
    }

    /**
     * 将缓存存入内存
     *
     * @param key
     * @param object
     * @param expiryTimestamp 过期时间,如果小于0则采用配置的默认时间
     * @return
     * @throws IOException
     */
    public boolean addObjectmMemCache(String key, Object object, long expiryTimestamp) throws IOException {
        if (expiryTimestamp <= 0)
            expiryTimestamp = globalConfig.getDefaultCacheExpiry();
        return globalConfig.getObjectCache().addObjectToMemoryCache(key, object, expiryTimestamp);
    }

    /**
     * 将缓存存入闪存
     *
     * @param key
     * @param object
     * @param expiryTimestamp 过期时间,如果小于0则采用配置的默认时间
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean addObjectDiskCache(String key, Object object, long expiryTimestamp) throws IOException, ClassNotFoundException {
        if (expiryTimestamp <= 0)
            expiryTimestamp = globalConfig.getDefaultCacheExpiry();
        return globalConfig.getObjectCache().addObjectToDiskCache(key, object, expiryTimestamp);
    }

    /**
     * return ObjectCache size
     *
     * @return
     */
    public long size() throws IOException {
        return globalConfig.size();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
