package com.yz.dl.integralmanage.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yze.zsy.zsyjif.R;
import com.yze.zsy.zsyjif.bean.Message;
import com.yze.zsy.zsyjif.comtools.BaseActivity;
import com.yze.zsy.zsyjif.db.DbManager;

/**
 * Created by Administrator on 2017/9/11.
 */

public class MessageConActivity  extends BaseActivity {
    private ImageView back;
    private TextView  name,date,msg_con;
    private Bundle  bundle = new Bundle() ;
    private  int  msgId =1;
    private String  nameStr,dateStr, conStr;
    @Override
    public void before() {
        bundle  = getIntent().getBundleExtra("msg");
        nameStr  = bundle.getString("name");
        dateStr  = bundle.getString("date");
        conStr   =  bundle.getString("con");
        msgId  =  bundle.getInt("id");

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_message_con);
        back    =   (ImageView) findViewById(R.id.message_con_back);
        name  =  (TextView) findViewById(R.id.message_con_title);
        msg_con =  (TextView) findViewById(R.id.message_con_con);
        date   =  (TextView) findViewById(R.id.message_con_date);
        name .setText(nameStr);
        msg_con.setText(conStr);
        date.setText(dateStr);
    }

    @Override
    public void setViewOnListener() {
        back.setOnClickListener(this);

    }

    @Override
    public void afterprc() {
        Message message  = new Message();
        message.setId(msgId);
        message.setDate(dateStr);
        message.setSendname(nameStr);
        message.setMsgcon(conStr);
        message.setMsgstate(1);
        DbManager.upMessage(message);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.message_con_back:
                finish();
                break;
        }

    }
}
