package com.yz.dl.integralmanage.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.ProblemAdapter;
import com.yz.dl.integralmanage.bean.ProblemBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 问题反馈列表
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class FeedProblemOffice extends Activity {

    @Bind(R.id.feedback_office_back)
    ImageView feedbackOfficeBack;
    @Bind(R.id.feedback_office_search)
    ImageView feedbackOfficeSearch;
    @Bind(R.id.feedback_office_list)
    ListView feedbackOfficeList;
    private ProblemAdapter adapter;

    ArrayList<ProblemBean> list;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_feedproblemoffice);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ProblemBean problemBean = new ProblemBean();
            problemBean.setName("王龙" + i);
            problemBean.setContent(getString(R.string.default_describe) + i);
            problemBean.setTime("12:30" + "");
            problemBean.setTarget("培训类" + i);
            list.add(problemBean);
        }
        adapter = new ProblemAdapter(getApplicationContext(), list);
        feedbackOfficeList.setAdapter(adapter);
        feedbackOfficeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(),FeedProblemDeatil.class));
            }
        });
    }

    @OnClick({R.id.feedback_office_back, R.id.feedback_office_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.feedback_office_back:
                this.finish();
                break;
            case R.id.feedback_office_search:
                startActivity(new Intent(getApplicationContext(),FeedProblemSearch.class));
                break;
        }
    }
}
