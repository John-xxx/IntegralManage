package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.comm.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class IntegralSearch extends Activity {
    @Bind(R.id.integralsearch_back)
    ImageView integralsearchBack;
    @Bind(R.id.office_select)
    TextView officeSelect;
    @Bind(R.id.month_select)
    TextView monthSelect;
    @Bind(R.id.date_select)
    TextView dateSelect;
    @Bind(R.id.total_station_num)
    TextView totalStationNum;
    @Bind(R.id.total_person_num)
    TextView totalPersonNum;
    @Bind(R.id.stationManager)
    TextView stationManager;
    @Bind(R.id.stationManager_num)
    TextView stationManagerNum;
    @Bind(R.id.stationManager_avg)
    TextView stationManagerAvg;
    @Bind(R.id.management)
    TextView management;
    @Bind(R.id.management_num)
    TextView managementNum;
    @Bind(R.id.management_avg)
    TextView managementAvg;
    @Bind(R.id.operator)
    TextView operator;
    @Bind(R.id.operator_num)
    TextView operatorNum;
    @Bind(R.id.operator_avg)
    TextView operatorAvg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_integralsearch);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.integralsearch_back, R.id.office_select, R.id.month_select, R.id.date_select, R.id.stationManager, R.id.management, R.id.operator})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.integralsearch_back:
                break;
            case R.id.office_select:
                break;
            case R.id.month_select:
                break;
            case R.id.date_select:
                break;
            case R.id.stationManager:
                intent.setClass(getApplicationContext(),PointRanking.class);
                intent.putExtra("DISPLAY", Constants.TAB_MANAGER);
                startActivity(intent);
                break;
            case R.id.management:
                intent.setClass(getApplicationContext(),PointRanking.class);
                intent.putExtra("DISPLAY", Constants.TAB_MANAGEMENT);
                startActivity(intent);
                break;
            case R.id.operator:
                intent.setClass(getApplicationContext(),PointRanking.class);
                intent.putExtra("DISPLAY", Constants.TAB_OPERATOR);
                startActivity(intent);
                break;
        }
    }
}
