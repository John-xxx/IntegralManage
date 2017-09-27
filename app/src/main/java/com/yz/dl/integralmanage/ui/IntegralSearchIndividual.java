package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class IntegralSearchIndividual extends Activity {
    @Bind(R.id.history_integral)
    TextView historyIntegral;
    @Bind(R.id.static_point)
    LinearLayout staticPoint;
    @Bind(R.id.dynamics_point)
    LinearLayout dynamicsPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_integralsearchindividual);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.static_point, R.id.dynamics_point,R.id.history_integral})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.static_point:
                intent.setClass(getApplicationContext(), StaticIntegral.class);
                break;
            case R.id.dynamics_point:
                intent.setClass(getApplicationContext(), DynamicsIntegral.class);
                break;
            case R.id.history_integral:
                intent.setClass(getApplicationContext(), HistoryIntegral.class);
                break;
        }
        startActivity(intent);
    }
}
