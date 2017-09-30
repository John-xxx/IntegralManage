package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消息详情页
 * Created by I'M CHAMAN on 2017/9/30.
 */

public class MessageDetail extends Activity {
    @Bind(R.id.message_detail_back)
    ImageView messageDetailBack;
    @Bind(R.id.message_detail_titletext)
    TextView messageDetailTitletext;
    @Bind(R.id.message_detail_title)
    TextView messageDetailTitle;
    @Bind(R.id.message_detail_content)
    TextView messageDetailContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_con);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.message_detail_back)
    public void onViewClicked() {
        this.finish();
    }
}
