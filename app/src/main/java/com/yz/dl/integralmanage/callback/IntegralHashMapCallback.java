package com.yz.dl.integralmanage.callback;

import com.yz.dl.integralmanage.utils.IntegralHashMap;
import com.yz.dl.integralmanage.utils.LogUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 封装HashMap回调
 *
 * Created by chaman on 2017/10/10.
 */

public class IntegralHashMapCallback extends Callback<IntegralHashMap> {

    public IntegralHashMap parseNetworkResponse(Response response) throws Exception {
        String res = response.body().string();
        LogUtils.e(res);
        return IntegralHashMap.fromJson2SAFHashMap(res);
    }


    @Override
    public IntegralHashMap parseNetworkResponse(Response response, int id) throws Exception {
        return parseNetworkResponse(response);
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        onError(call, e);
    }

    @Override
    public void onResponse(IntegralHashMap response, int id) {
        onResponse(response);
    }

    @Override
    public void onBefore(Request request, int id) {
        onBefore(request);
    }

    @Override
    public void onAfter(int id) {
        onAfter();
    }

    public void onError(Call call, Exception e) {

    }

    public void onResponse(IntegralHashMap response) {

    }


    public void onBefore(Request request) {

    }

    public void onAfter() {
    }
}