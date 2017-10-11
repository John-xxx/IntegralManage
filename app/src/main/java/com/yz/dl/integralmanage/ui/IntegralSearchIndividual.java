package com.yz.dl.integralmanage.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.IntegralSearchFragmentAdapter;
import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.view.BanSlideViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 加油员->积分查询类
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class IntegralSearchIndividual extends FragmentActivity {

    @Bind(R.id.integralsearch_back)
    ImageView integralsearchBack;
    @Bind(R.id.integral_tab_present)
    RadioButton integralTabPresent;
    @Bind(R.id.integral_tab_history)
    RadioButton integralTabHistory;
    @Bind(R.id.integralsearch_viewpager)
    BanSlideViewPager integralsearchViewpager;

    IntegralSearchFragmentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integralsearchindividual);
        ButterKnife.bind(this);
        adapter = new IntegralSearchFragmentAdapter(getSupportFragmentManager());
        integralsearchViewpager.setAdapter(adapter);
        integralsearchViewpager.setOffscreenPageLimit(2);
        integralsearchViewpager.setScrollEnable(false);
        integralsearchViewpager.setCurrentItem(Constants.TAB_PRESENT, false);
    }

    @OnClick({R.id.integralsearch_back, R.id.integral_tab_present, R.id.integral_tab_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.integralsearch_back:
                this.finish();
                break;
            case R.id.integral_tab_present:
                integralsearchViewpager.setCurrentItem(Constants.TAB_PRESENT, false);
                break;
            case R.id.integral_tab_history:
                integralsearchViewpager.setCurrentItem(Constants.TAB_HISTORY, false);
                break;
        }
    }
}
