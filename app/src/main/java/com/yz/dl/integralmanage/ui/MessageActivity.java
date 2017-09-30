package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.MessageAdapter;
import com.yz.dl.integralmanage.bean.MessageBean;
import com.yz.dl.integralmanage.bean.RankingBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 消息推送列表
 * Created by Administrator on 2017/8/29.
 */

public class MessageActivity extends Activity {

    @Bind(R.id.message_title)
    TextView messageTitle;
    @Bind(R.id.message_back)
    ImageView messageBack;
    @Bind(R.id.message_send_img)
    ImageView messageSendImg;
    @Bind(R.id.message_listview)
    ListView messageListview;

    MessageAdapter adapter;
    ArrayList<MessageBean>  arrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            MessageBean messageBean = new MessageBean();
            messageBean.setMsgcon("测试内容");
            messageBean.setSendname("测试");
            messageBean.setDate("8月7日");
            arrayList.add(messageBean);
        }
        adapter = new MessageAdapter(getApplicationContext(),arrayList);
        messageListview.setAdapter(adapter);
        messageListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(),MessageDetail.class));
            }
        });
    }

    @OnClick({R.id.message_back, R.id.message_send_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_back:
                this.finish();
                break;
            case R.id.message_send_img:
                startActivity(new Intent(getApplicationContext(),MessageSendActivity.class));
                break;
        }
    }
}
