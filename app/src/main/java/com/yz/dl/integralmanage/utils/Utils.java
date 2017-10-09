package com.yz.dl.integralmanage.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class Utils {





    /**
     * 将输入的dp转换为对应的px值，并返回px值
     *
     * @param dp
     * @param c
     * @return
     */
    public static int dp2px(int dp, Context c) {

        return (int) (dp * c.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 将json转换为map
     *
     * @param json
     * @return
     */
    public static InrgralHashMap convertJSONToMap(JSONObject json) {
        if (json == null) {
            return null;
        }
        InrgralHashMap map = new InrgralHashMap();

        Iterator<String> i = json.keys();
        while (i.hasNext()) {
            String name = i.next();
            Object o = null;
            try {
                o = json.get(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (o == null) {
                continue;
            }
            if (o.equals(null)) {
                map.put(name, "");
                continue;
            }
            if (o instanceof JSONObject) {
                map.put(name, convertJSONToMap((JSONObject) o));
            } else if (o instanceof JSONArray) {
                map.put(name, convertJSONArrayToList((JSONArray) o));
            } else {
                map.put(name, o);
            }

        }
        return map;
    }


    /**
     * 将jsonarray 转换为List
     *
     * @param array
     * @return
     */
    public static InrgralArrayList convertJSONArrayToList(JSONArray array) {
        if (array == null) {
            return null;
        }
        InrgralArrayList list = new InrgralArrayList();

        for (int i = 0; i < array.length(); i++) {
            Object o = null;
            try {
                o = array.get(i);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (o == null) {
                continue;
            }
            if (o.equals(null)) {
                list.add("");
                continue;
            }
            if (o instanceof JSONObject) {
                list.add(convertJSONToMap((JSONObject) o));
            } else if (o instanceof JSONArray) {
                list.add(convertJSONArrayToList((JSONArray) o));
            } else {
                list.add(o);
            }

        }
        return list;
    }

    public static Date strtoDate(String sTime, String format){
        SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式在android中，创建文件时，文件名中不能包含“：”冒号
        String filename = df.format(new Date());
        return null;
    }

}
