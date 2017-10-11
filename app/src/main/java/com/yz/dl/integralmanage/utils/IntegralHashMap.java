package com.yz.dl.integralmanage.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class IntegralHashMap extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1000L;

    public IntegralHashMap() {
    }

    public static IntegralHashMap fromJson2SAFHashMap(String jsonString) {
        try {
            return Utils.convertJSONToMap(new JSONObject(jsonString));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getString(String key) {
        return (String) get(key);
    }

    public boolean getBoolean(String key) {
        return (Boolean) get(key);
    }

    public int getInt(String key) {
        return (Integer) get(key);
    }

    public float getFloat(String key) {
        return (Float) get(key);
    }

    public double getDouble(String key) {
        return (Double) get(key);
    }

    public long getLong(String key) {
        return (Long) (get(key));
    }

    public IntegralHashMap getInrgralHashMap(String key) {
        return (IntegralHashMap) get(key);
    }

    public InrgralArrayList getInrgralArrayList(String key) {
        return (InrgralArrayList) get(key);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}