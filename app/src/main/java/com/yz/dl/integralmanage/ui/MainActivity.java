package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.comm.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/25.
 */

public class MainActivity extends Activity {
    @Bind(R.id.main_itegralSearch)
    TextView mainItegralSearch;
    @Bind(R.id.main_checkPoint)
    TextView mainCheckPoint;
    @Bind(R.id.main_integralChange)
    TextView mainIntegralChange;
    @Bind(R.id.feed_problem)
    TextView feedProblem;
    @Bind(R.id.push_message)
    TextView pushMessage;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    private String IDcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        IDcard = getIntent().getStringExtra("PERSON");
    }

    @OnClick({R.id.main_itegralSearch, R.id.main_checkPoint, R.id.main_integralChange,R.id.feed_problem, R.id.push_message})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.main_itegralSearch:
                if (IDcard.equals(Constants.TYPE_MANAGER)) {
                    intent.setClass(getApplicationContext(), IntegralSearch.class);
                } else if (IDcard.equals(Constants.TYPE_OPERATOR)) {
                    intent.setClass(getApplicationContext(), IntegralSearchIndividual.class);
                }
                break;
            case R.id.main_checkPoint:
                if (IDcard.equals(Constants.TYPE_MANAGER)) {
                    intent.setClass(getApplicationContext(), InspectorChecked.class);
                } else if (IDcard.equals(Constants.TYPE_OPERATOR)) {
                    intent.setClass(getApplicationContext(), ManagerChecked.class);
                }
                break;
            case R.id.main_integralChange:
                if (IDcard.equals(Constants.TYPE_MANAGER)) {
                    intent.setClass(getApplicationContext(), IntegralChangeOffice.class);
                } else if (IDcard.equals(Constants.TYPE_OPERATOR)) {
                    intent.setClass(getApplicationContext(), IntegralChangePersonal.class);
                }
                break;
            case R.id.feed_problem:
                if (IDcard.equals(Constants.TYPE_MANAGER)) {
                    intent.setClass(getApplicationContext(), FeedProblemOffice.class);
                } else if (IDcard.equals(Constants.TYPE_OPERATOR)) {
                    intent.setClass(getApplicationContext(), FeedProblemPersonal.class);
                }
                break;
            case R.id.push_message:
                intent.setClass(getApplicationContext(),MessageActivity.class);
                break;
        }
        startActivity(intent);
    }
}
