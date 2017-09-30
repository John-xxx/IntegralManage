package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.view.AddAccessoryView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 问题反馈详情页
 * Created by I'M CHAMAN on 2017/9/30.
 */

public class FeedProblemDeatil extends Activity {
    @Bind(R.id.feedback_detail_back)
    ImageView feedbackDetailBack;
    @Bind(R.id.feedback_detail_inspector)
    TextView feedbackDetailInspector;
    @Bind(R.id.feedback_detail_target)
    TextView feedbackDetailTarget;
    @Bind(R.id.feedback_detail_describe)
    TextView feedbackDetailDescribe;
    @Bind(R.id.feedbacd_detailimage_layout)
    AddAccessoryView feedbacdDetailimageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedproblemdetail);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.feedback_detail_back, R.id.feedbacd_detailimage_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.feedback_detail_back:
                this.finish();
                break;
            case R.id.feedbacd_detailimage_layout:
                break;
        }
    }
}
