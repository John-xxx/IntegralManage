package com.yz.dl.integralmanage.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.telephony.SmsManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yz.dl.integralmanage.R;

import java.util.List;

/**
 * 工具组件类
 * Created by chaman on 2017/10/10.
 */

public class IntegralUI {

//    private static View view;
//
//    public static View getView() {
//        return view;
//    }
//
//    public static void setView(View view) {
//        IntegralUI.view = view;
//    }
//
//    /**
//     * 开启等待dialog
//     *
//     * @param context
//     * @return Alertdialog
//     */
//    public static AlertDialog openWaitDialog(Context context, String title,
//                                             String msg) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        View view = inflater.inflate(R.layout.item_openwaitdialog, null);
//
//        TextView textview = (TextView) view
//                .findViewById(R.id.item_openwaitdialog_text);
//        textview.setText(msg);
//
//        builder.setTitle(title);
//        builder.setView(view);
//        AlertDialog loadWaitDialog = builder.create();
//        loadWaitDialog.setCanceledOnTouchOutside(false);
//        loadWaitDialog.show();
//        return loadWaitDialog;
//
//    }
//
//    /**
//     * 开启一个等待对话框
//     *
//     * @param context
//     * @param msg
//     */
//    public static Dialog openWaitDialog(Context context, String msg) {
//
//        // 此处直接new一个Dialog对象出来，在实例化的时候传入主题
//        Dialog dialog = new Dialog(context, R.style.WaitDialog);
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        View view = inflater.inflate(R.layout.layout_openwaitdialog, null);
//        TextView textview = (TextView) view
//                .findViewById(R.id.layout_openwaitdialog_text);
//        textview.setText(msg);
//
//        // 设置它的ContentView
//        dialog.setContentView(view);
//        dialog.show();
//        WindowManager windowManager = ((Activity) context).getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.width = (int) (display.getWidth()); // 设置宽度
//        dialog.getWindow().setAttributes(lp);
//
//        return dialog;
//    }
//
//    /**
//     * 开启一个带有进度的等待框,用于上传进度等
//     *
//     * @param context
//     * @param msg
//     * @return
//     */
//    public static ProgressDialog openProgressDialog(Context context, String msg) {
//
//        // 此处直接new一个Dialog对象出来，在实例化的时候传入主题
//        ProgressDialog dialog = new ProgressDialog(context, R.style.WaitDialog);
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        View view = inflater.inflate(R.layout.layout_openprogressdialog, null);
//        TextView msgText = (TextView) view
//                .findViewById(R.id.layout_openprogressdialog_text);
//        msgText.setText(msg);
//
//        // 设置它的ContentView
//        dialog.setContentView(view);
//        dialog.setCancelable(true);
//        dialog.show();
//        WindowManager windowManager = ((Activity) context).getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.width = (int) (display.getWidth()); // 设置宽度
//        dialog.getWindow().setAttributes(lp);
//
//        return dialog;
//    }
//
//    /**
//     * 开启带确认的dialog
//     *
//     * @param context
//     * @param title
//     * @param msg
//     */
//    public static void openAlertDialog(Context context, String title,
//                                       String msg, DialogInterface.OnClickListener ok) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle(title);
//        builder.setMessage("\n" + msg + "\n");
//        builder.setNegativeButton("确定", ok);
//
//        AlertDialog loadWaitDialog = builder.create();
//        loadWaitDialog.setCanceledOnTouchOutside(false);
//        loadWaitDialog.show();
//    }
//
//    /**
//     * 开启带确认和取消的dialog
//     *
//     * @param context
//     * @param title
//     * @param msg
//     */
//    public static void openConfirmDialog(Context context, String title,
//                                         String msg, String okbutton, DialogInterface.OnClickListener ok, String nobutton,
//                                         DialogInterface.OnClickListener no) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle(title);
//        builder.setMessage("\n" + msg + "\n");
//        builder.setNegativeButton(okbutton, ok);
//        builder.setNeutralButton(nobutton, no);
//        AlertDialog loadWaitDialog = builder.create();
//        loadWaitDialog.setCanceledOnTouchOutside(false);
//        loadWaitDialog.show();
//    }
//
//    /**
//     * 开启不带title带确认和取消的dialog
//     *
//     * @param context
//     * @param msg
//     */
//    public static void openConfirmDialogNoTitle(Context context,
//                                                String msg, String okbutton, DialogInterface.OnClickListener ok, String nobutton,
//                                                DialogInterface.OnClickListener no) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage("\n" + msg + "\n");
//        builder.setNeutralButton(okbutton, ok);
//        builder.setNegativeButton(nobutton, no);
//        AlertDialog loadWaitDialog = builder.create();
//        loadWaitDialog.setCanceledOnTouchOutside(false);
//        loadWaitDialog.show();
//    }
//
//    /**
//     * 发送短信
//     */
//    public static void sendSMS(Context context, String phone, String sms) {
//        SmsManager smsManager = SmsManager.getDefault();
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
//                new Intent(), 0);
//        if (sms.length() > 70) {
//            List<String> texts = smsManager.divideMessage(sms);
//            for (String str : texts) {
//
//                smsManager.sendTextMessage(phone, null, str, pendingIntent,
//                        null);
//            }
//        } else {
//            smsManager.sendTextMessage(phone, null, sms, pendingIntent, null);
//        }
//        Toast.makeText(context, "发送成功", Toast.LENGTH_LONG).show();
//    }
//
//    /**
//     * 创建3个item的dialog背景为白色
//     *
//     * @param ctx
//     * @param labels
//     * @param listener
//     * @param selectflag 如果0则代表没有√ 如果是一则显示√
//     * @return
//     */
//    public static Dialog openListDialog(Context ctx, String[] labels,
//                                        View.OnClickListener listener, int selectflag) {
//        Dialog dialog = new Dialog(ctx, R.style.dialog);
//        LayoutInflater inflater = LayoutInflater.from(ctx);
//        view = inflater.inflate(R.layout.layout_customerdialog, null);
//        setView(view);// 设置view
//        RelativeLayout dialog_op1_ll = (RelativeLayout) view
//                .findViewById(R.id.dialog_op1_ll);
//        RelativeLayout dialog_op2_ll = (RelativeLayout) view
//                .findViewById(R.id.dialog_op2_ll);
//        RelativeLayout dialog_op3_ll = (RelativeLayout) view
//                .findViewById(R.id.dialog_op3_ll);
//        TextView dialog_op1 = (TextView) view.findViewById(R.id.dialog_op1);
//        TextView dialog_op2 = (TextView) view.findViewById(R.id.dialog_op2);
//        TextView dialog_op3 = (TextView) view.findViewById(R.id.dialog_op3);
//        ImageView dialog_op1_selected = (ImageView) view
//                .findViewById(R.id.dialog_op1_selected);
//        ImageView dialog_op2_selected = (ImageView) view
//                .findViewById(R.id.dialog_op2_selected);
//        if (selectflag == 0) {
//            dialog_op1_selected.setVisibility(View.GONE);
//            dialog_op2_selected.setVisibility(View.GONE);
//        }
//        dialog_op1.setText(labels[0]);
//        dialog_op2.setText(labels[1]);
//        dialog_op3.setText(labels[2]);
//        dialog_op1_ll.setOnClickListener(listener);
//        dialog_op2_ll.setOnClickListener(listener);
//        dialog_op3_ll.setOnClickListener(listener);
//        dialog.setContentView(view);
//        dialog.show();
//        return dialog;
//    }
//
//    /**
//     * 创建天气指数对话框
//     *
//     * @param ctx
//     * @param strtitle
//     * @param strdetail
//     * @param flag
//     * @return
//     */
//    public static Dialog openIndexDialog(Context ctx, String strtitle, String strdetail, int flag) {
//        Dialog dialog = new Dialog(ctx, R.style.notitledialog);
//        LayoutInflater inflater = LayoutInflater.from(ctx);
//        view = inflater.inflate(R.layout.dialog_index, null);
//        TextView title = (TextView) view.findViewById(R.id.dialog_index_title);
//        TextView detail = (TextView) view.findViewById(R.id.dialog_index_detail);
//        title.setText(strtitle);
//        detail.setText(strdetail);
//        int resId = R.drawable.ic_index_ultravioletrays_s;
//        if (flag == 1) {
//            resId = R.drawable.ic_index_ultravioletrays_s;
//        } else if (flag == 2) {
//            resId = R.drawable.ic_index_dressing_s;
//        } else if (flag == 3) {
//            resId = R.drawable.ic_index_sport_s;
//        } else if (flag == 4) {
//            resId = R.drawable.ic_index_carwash_s;
//        } else if (flag == 5) {
//            resId = R.drawable.ic_index_tourism_s;
//        } else if (flag == 6) {
//            resId = R.drawable.ic_index_cold_s;
//        }
//        Drawable situation_drawable = ctx.getResources().getDrawable(resId);
//        situation_drawable.setBounds(0, 0, situation_drawable.getMinimumWidth(), situation_drawable.getMinimumHeight());
//        title.setCompoundDrawables(situation_drawable, null, null, null);
//        setView(view);// 设置view
//        dialog.setContentView(view);
//        dialog.show();
//        return dialog;
//    }
//
//    /**
//     * 创建列表对话框
//     *
//     * @param ctx      上下文 必填
//     * @param title    标题 必填
//     * @param itemsId  字符串数组资源id 必填
//     * @param listener 监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
//     * @return
//     */
//    public static Dialog createListDialog(Context ctx, String title,
//                                          int itemsId, DialogInterface.OnClickListener listener) {
//        Dialog dialog = null;
//        AlertDialog.Builder builder = new AlertDialog.Builder(
//                ctx);
//        // 设置对话框的图标
//        // builder.setIcon(iconId);
//        // 设置对话框的标题
//        builder.setTitle(title);
//        // 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
//        builder.setItems(itemsId, listener);
//        // 创建一个列表对话框
//        dialog = builder.create();
//        return dialog;
//    }
//
//    public static class ProgressDialog extends Dialog {
//        public ProgressDialog(Context context) {
//            super(context);
//            // TODO Auto-generated constructor stub
//        }
//
//        protected ProgressDialog(Context context, boolean cancelable,
//                                 OnCancelListener cancelListener) {
//            super(context, cancelable, cancelListener);
//            // TODO Auto-generated constructor stub
//        }
//
//        public ProgressDialog(Context context, int theme) {
//            super(context, theme);
//            // TODO Auto-generated constructor stub
//        }
//
//        public void setProgress(final int progress) {
//
//            ((TextView) findViewById(R.id.layout_openprogressdialog_progress))
//                    .setText(progress + "%");
//
//        }
//    }
//
//
//    public static Dialog openListDialog(Activity ctx) {
//        final Dialog dialog = new Dialog(ctx, R.style.notitledialogrect);
//        LayoutInflater inflater = LayoutInflater.from(ctx);
//        view = inflater.inflate(R.layout.list_dialog, null);
//        setView(view);// 设置view
//        dialog.setContentView(view);
//        Window dialogWindow = dialog.getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        dialogWindow.setGravity(Gravity.CENTER);
//        lp.height = 800; // 高度
//        dialogWindow.setAttributes(lp);
//        dialog.show();
//        return dialog;
//    }
//
//    public static Dialog openWrapListDialog(Activity ctx) {
//        final Dialog dialog = new Dialog(ctx, R.style.notitledialogrect);
//        LayoutInflater inflater = LayoutInflater.from(ctx);
//        view = inflater.inflate(R.layout.list_dialog, null);
//        setView(view);// 设置view
//        dialog.setContentView(view);
////        Window dialogWindow = dialog.getWindow();
////        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
////        dialogWindow.setGravity(Gravity.CENTER);
////        lp.height = 300; // 高度
////        dialogWindow.setAttributes(lp);
//        dialog.show();
//        return dialog;
//    }

}
