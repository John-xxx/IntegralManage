package com.yz.dl.integralmanage.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 配置文件管理器
 * Created by chaman on 2017/10/9.
 */

public class ConfigUtils {

    private static ConfigUtils config = new ConfigUtils();

    private ConfigUtils() {
    }

    private Context c;
    private JSONObject mainJSON;
    private int configVersion;
    private JSONObject configContent;
    private String DynamicConfig;
    private SharedPreferences preferences;

    public static ConfigUtils getInstance() {
        return config;
    }

    /**
     * 初始化safconfig
     *
     * @param c
     * @return
     */
    public static ConfigUtils getInstance(Context c) {
        config.c = c;
        String configString = null;

        // 首先将assets目录中的config.json加载为字符串
        try {
            configString = Utils.readInputStreamToString(config.c
                    .getAssets().open("config.json"));
        } catch (IOException e) {
            LogUtils.e(e.getMessage(), e);
        }

        // 然后将其转换为jsonObject
        if (configString != null && configString != "") {
            try {
                config.mainJSON = new JSONObject(configString);
                config.configVersion = config.mainJSON.getInt("ConfigVersion");
                config.configContent = config.mainJSON
                        .getJSONObject("ConfigContent");
                config.DynamicConfig = config.mainJSON
                        .getString("DynamicConfig");
            } catch (JSONException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }

        // 初始化动态配置文件
        config.initDynamicConfig();

        return config;
    }

    /**
     * 初始化动态配置文件
     */
    public void initDynamicConfig() {
        // 获取sharedpreferences对象
        preferences = config.c.getSharedPreferences(config.DynamicConfig,
                Context.MODE_PRIVATE);

        // 这里是需要初始化的内容，每次更新时应该注意修改这里

        if (preferences.contains("")) {

        }

    }

    public String getDynamicConfig() {
        return DynamicConfig;
    }

    public void setDynamicConfig(String dynamicConfig) {
        DynamicConfig = dynamicConfig;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    /**
     * 获取一个int型配置项，如果不存在则返回defaultValue
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(String key, int defaultValue) {
        if (config.configContent.has(key)) {
            try {
                return config.configContent.getInt(key);
            } catch (JSONException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        return defaultValue;
    }

    /**
     * 获取一个long型配置项，如果不存在则返回defaultValue
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public long getLong(String key, long defaultValue) {
        if (config.configContent.has(key)) {
            try {
                return config.configContent.getLong(key);
            } catch (JSONException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        return defaultValue;
    }

    /**
     * 获取一个jsonobject型配置项
     *
     * @param key
     * @return
     */
    public JSONObject getJSONObject(String key) {
        if (config.configContent.has(key)) {
            try {
                return config.configContent.getJSONObject(key);
            } catch (JSONException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 获取一个jsonobject型配置项
     *
     * @param key
     * @return
     */
    public JSONArray getJSONArray(String key) {
        if (config.configContent.has(key)) {
            try {
                return config.configContent.getJSONArray(key);
            } catch (JSONException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 获取一个String型配置项，如果不存在则返回defaultValue
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        if (config.configContent.has(key)) {
            try {
                return config.configContent.getString(key);
            } catch (JSONException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        return defaultValue;
    }

    public JSONObject getMainJSON() {
        return mainJSON;
    }

    public void setMainJSON(JSONObject mainJSON) {
        this.mainJSON = mainJSON;
    }

    public int getConfigVersion() {
        return configVersion;
    }

    public void setConfigVersion(int configVersion) {
        this.configVersion = configVersion;
    }

    public JSONObject getConfigContent() {
        return configContent;
    }

    public void setConfigContent(JSONObject configContent) {
        this.configContent = configContent;
    }

}
