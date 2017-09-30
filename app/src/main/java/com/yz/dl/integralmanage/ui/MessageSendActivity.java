package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.view.AddAccessoryView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MessageSendActivity extends Activity {


    @Bind(R.id.message_send_back)
    ImageView messageSendBack;
    @Bind(R.id.message_send_titletext)
    TextView messageSendTitletext;
    @Bind(R.id.message_send_select)
    TextView messageSendSelect;
    @Bind(R.id.message_send_title)
    EditText messageSendTitle;
    @Bind(R.id.message_send_content)
    EditText messageSendContent;
    @Bind(R.id.messagesend_add_accessory)
    ImageView messagesendAddAccessory;
    @Bind(R.id.messagesend_accessory_layout)
    AddAccessoryView messagesendAccessoryLayout;
    @Bind(R.id.message_send_send)
    Button messageSendSend;
    @Bind(R.id.message_send_save)
    Button messageSendSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.message_send_back, R.id.message_send_select, R.id.messagesend_add_accessory, R.id.message_send_send, R.id.message_send_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_send_back:
                this.finish();
                break;
            case R.id.message_send_select:
                break;
            case R.id.messagesend_add_accessory:
                break;
            case R.id.message_send_send:
                break;
            case R.id.message_send_save:
                break;
        }
    }

//    /**
//     * 判断填写项目是否为空
//     */
//
//    private boolean isPostionIsemty() {
//        // Utils.showMessage("填写信息不能为空");
//
//        boolean isEmtip = false;
//        if (sendTv.getText().toString().replaceAll(" ", "").length() == 0) {
//            Utils.showMessage("请输入发送对象");
//            return true;
//        }
//        if (titleEdit.getText().toString().replaceAll(" ", "").length() == 0) {
//            Utils.showMessage("请输入消息标题");
//            return true;
//        }
//        if (conEdit.getText().toString().length() == 0) {
//            Utils.showMessage("请输入消息内容");
//            return true;
//        }
//        return isEmtip;
//    }
}
