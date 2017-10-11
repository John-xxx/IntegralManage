package com.yz.dl.integralmanage.api;

import android.content.Context;

import com.yz.dl.integralmanage.utils.ConfigUtils;
import com.yz.dl.integralmanage.utils.HTTP;
import com.yz.dl.integralmanage.utils.LogUtils;
import com.yz.dl.integralmanage.utils.MD5;
import com.yz.dl.integralmanage.utils.SignUtils;
import com.yz.dl.integralmanage.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.MediaType;

/**
 * 请求API类
 * Created by chaman on 2017/10/8.
 */

public class IntegralAPI {


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static double LNG = 0;
    public static double LAT = 0;

    //-------------------------------------main--------------------------------------------
    private static final String url = "http://api.qa.com";
    private static final String version = "/v1";
    //-------------------------------------action------------------------------------------
    private static final String login = version + "/login";


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
    public static void requestIntegral(Context context, String method, String action, JSONObject prm,String token, String securityKey, Callback callback) {
        //后台分配的appKey
        String appKey = ConfigUtils.getInstance().getString("appKey", "123456");
        //数据请求主地址
        String dataServlet = url + action;
        //时间戳
        String timeStamp = System.currentTimeMillis() + "";
        //数字签名
        String sign = SignUtils.sign(action, method, prm == null ? null : prm.toString(),timeStamp, appKey, token, securityKey);
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
                    .params((HashMap) Utils.convertJSONToMap(prm))
                    .tag(context.getClass().getName())
                    .build()
                    .execute(callback);
        }

    }

    /**
     * 普通Request
     *
     * @param context
     * @param action
     * @param prm
     * @param callback
     */
    public static void request(Context context, String action, JSONObject prm, Callback callback) {
        // 取得数据Servlet地址
        String dataServlet = ConfigUtils.getInstance().getString("DataServlet", "") + action;

        // 执行请求
        LogUtils.e("ACTION:" + action);
        LogUtils.e("REQUEST:" + prm);
        OkHttpUtils
                .postString()
                .url(dataServlet)
                .tag(context)
                .content(prm == null ? "" : prm.toString())
                .build()
                .execute(callback);
    }

    /**
     * 用户登录
     *
     * @param context
     * @param account
     * @param password
     * @param callback
     */
    public static void login(Context context, String account, String password, Callback callback) {
        JSONObject prm = new JSONObject();
        String pw = MD5.encrypt(password);
        try {
            prm.put("loginName", account);
            prm.put("userPwd", pw);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestIntegral(context, HTTP.METHOD.POST, login, prm, null, null, callback);
    }
}
