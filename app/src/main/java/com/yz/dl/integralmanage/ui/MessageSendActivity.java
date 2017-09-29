package com.yz.dl.integralmanage.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yze.zsy.zsyjif.R;
import com.yze.zsy.zsyjif.comtools.BaseActivity;
import com.yze.zsy.zsyjif.comtools.Utils;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MessageSendActivity  extends BaseActivity {
    private ImageView back, addImg;
    private TextView sendTv;
    private RelativeLayout sendNameRel;
    private EditText titleEdit;
    private EditText conEdit;
    private LinearLayout addImg_oneline, addImg_twoline;

    @Override
    public void before() {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_send_message);
        back = (ImageView) findViewById(R.id.message_send_back);
        sendTv = (TextView) findViewById(R.id.send_message_name_tv);
        sendNameRel = (RelativeLayout) findViewById(R.id.send_message_name_rel);
        titleEdit = (EditText) findViewById(R.id.send_message_title_edit);
        conEdit = (EditText) findViewById(R.id.send_message_con_edit);
        addImg_oneline = (LinearLayout) findViewById(R.id.message_send_add_img_line_one);
        addImg_twoline = (LinearLayout) findViewById(R.id.message_send_add_img_line_two);
        addImg = (ImageView) findViewById(R.id.message_send_add_img_add);
    }

    @Override
    public void setViewOnListener() {
        back.setOnClickListener(this);
        sendNameRel.setOnClickListener(this);
    }

    @Override
    public void afterprc() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message_send_back:
                finish();
                break;
            case R.id.send_message_name_rel:
                break;
        }
    }

    //发送消息
    public void sendMessage(View view) {
       if(isPostionIsemty()){

       }

    }

    //暂存消息
    public void zancunMessage(View view) {

    }

    /**
     * 判断填写项目是否为空
     */

    private boolean isPostionIsemty() {
        // Utils.showMessage("填写信息不能为空");

        boolean isEmtip = false;
        if (sendTv.getText().toString().replaceAll(" ", "").length() == 0) {
            Utils.showMessage("请输入发送对象");
            return true;
        }
        if (titleEdit.getText().toString().replaceAll(" ", "").length() == 0) {
            Utils.showMessage("请输入消息标题");
            return true;
        }
        if (conEdit.getText().toString().length() == 0) {
            Utils.showMessage("请输入消息内容");
            return true;
        }
        return  isEmtip;
    }


}
