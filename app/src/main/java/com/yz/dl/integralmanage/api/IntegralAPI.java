package com.yz.dl.integralmanage.api;

import android.content.Context;

import com.yz.dl.integralmanage.utils.ConfigUtils;
import com.yz.dl.integralmanage.utils.HTTP;
import com.yz.dl.integralmanage.utils.SignUtils;
import com.yz.dl.integralmanage.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 请求API类
 * Created by chaman on 2017/10/8.
 */

public class IntegralAPI {

    public static double LNG = 0;
    public static double LAT = 0;


    /**
     * 积分项目定制请求方法
     *
     * @param context
     * @param method
     * @param action
     * @param prm
     * @param token
     * @param securityKey
     * @param callback
     */
    public static void requestIntegral(Context context, String method, String action, JSONObject prm,
                                       String token, String securityKey, Callback callback) {
        //后台分配的appKey
        String appKey = ConfigUtils.getInstance().getString("appKey", "123456");     //数据请求主地址
        String dataServlet = url + action;
        //时间戳
        String timeStamp = System.currentTimeMillis() + "";
        //数字签名
        String sign = SignUtils.sign(action, method, prm == null ? null : prm.toString(),
                timeStamp, appKey, token, securityKey);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("MATRIX-APPKEY", appKey);
        headers.put("MATRIX-TIMESTAMP", timeStamp);
        if (token != null) {
            headers.put("MATRIX-TOKEN", token);
        }
        headers.put("MATRIX-SIGNATURE", sign);
        String imei = Utils.getIMEI_IMSI(context, 0);
        headers.put("IMEI", imei == null ? "" : imei);
        String imsi = Utils.getIMEI_IMSI(context, 1);
        headers.put("IMSI", imsi == null ? "" : imsi);
        headers.put("LNG", LNG + "");
        headers.put("LAT", LAT + "");
        // 执行请求
        LogUtils.e("HEADERS:" + headers.toString());
        LogUtils.e("URI:" + dataServlet);
        LogUtils.e("REQUEST:" + prm);
        LogUtils.e("tag-->start-->" + context.getClass().getName());
        if (method.equals(HTTP.METHOD.POST)) {
            OkHttpUtils
                    .postString()
                    .mediaType(JSON)
                    .url(dataServlet)
                    .headers(headers)
                    .tag(context.getClass().getName())
                    .content(prm == null ? "" : prm.toString())
                    .build()
                    .execute(callback);
        } else {
            OkHttpUtils
                    .get()
                    .url(dataServlet)
                    .headers(headers)
                    .params((HashMap) MSRUtils.convertJSONToMap(prm))
                    .tag(context.getClass().getName())
                    .build()
                    .execute(callback);
        }

    }
}
