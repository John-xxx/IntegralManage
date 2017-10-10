package com.yz.dl.integralmanage.utils;

/**
 * 签名工具类
 * Created by chaman on 2017/10/9.
 */

public class SignUtils {
    /**
     * 数字签名实现
     *
     * @param uri
     * @param method
     * @param body
     * @param timeStamp
     * @param appKey
     * @param token
     * @param securityKey
     * @return
     */
    public static String sign(String uri, String method, String body, String timeStamp, String appKey, String token, String securityKey) {
        StringBuilder builder = new StringBuilder();
        builder.append("uri=").append(uri).append('&');
        builder.append("method=").append(method).append('&');
        if (method.equals(HTTP.METHOD.POST)) {
            if (body != null) {
                builder.append("body=").append(body).append('&');
            } else {
                builder.append("body=").append("&");
            }
        } else {
            builder.append("body=").append("&");
        }

        builder.append("timestamp=").append(timeStamp).append('&');
        builder.append("appKey=").append(appKey);
        if (token != null && securityKey != null) {
            builder.append("&token=").append(token).append('&');
            builder.append("securityKey=").append(securityKey);

        }
        MD5 md5 = new MD5();
        return md5.encrypt(builder.toString());
    }


}
