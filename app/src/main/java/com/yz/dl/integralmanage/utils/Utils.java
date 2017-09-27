package com.yz.dl.integralmanage.utils;

import android.content.Context;

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
}
