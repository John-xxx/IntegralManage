package com.yz.dl.integralmanage.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.utils.IntegralUI;

/**
 * 等待加载提示框
 * Created by chaman on 2017/10/10.
 */

public class IntegarlWaitDialog extends IntegralUI {

    public static Dialog openIntegralWaitDialog(Context context, String msg) {

        // 此处直接new一个Dialog对象出来，在实例化的时候传入主题
        Dialog dialog = new Dialog(context, R.style.IntegralWaitDialog);
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.dialog_integralwaitdialog, null);
        TextView textview = (TextView) view
                .findViewById(R.id.ldialog_integralwaitdialog_text);
        textview.setText(msg);

        // 设置它的ContentView
        dialog.setContentView(view);
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() / 5 * 4); // 设置宽度
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        return dialog;
    }

}
