package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.IntegralChangedFragmentAdapter;
import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.view.BanSlideViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人积分兑换界面
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class IntegralChangePersonal extends FragmentActivity {
    @Bind(R.id.integralpersonal_back)
    ImageView integralpersonalBack;
    @Bind(R.id.changepersonal_tab_goods)
    RadioButton changepersonalTabGoods;
    @Bind(R.id.changepersonal_tab_vocation)
    RadioButton changepersonalTabVocation;
    @Bind(R.id.changepersonal_tab_inegral)
    RadioButton changepersonalTabInegral;
    @Bind(R.id.changepersonal_viewpager)
    BanSlideViewPager changepersonalViewpager;

    private IntegralChangedFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integralchangepersonal);
        ButterKnife.bind(this);

        adapter = new IntegralChangedFragmentAdapter(getSupportFragmentManager());
        changepersonalViewpager.setAdapter(adapter);
        changepersonalViewpager.setScrollEnable(true);
        changepersonalViewpager.setOffscreenPageLimit(3);
        changepersonalViewpager.setCurrentItem(Constants.TAB_GOODSCHANGE,false);

    }

    @OnClick({R.id.integralpersonal_back, R.id.changepersonal_tab_goods, R.id.changepersonal_tab_vocation, R.id.changepersonal_tab_inegral})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.integralpersonal_back:
                this.finish();
                break;
            case R.id.changepersonal_tab_goods:
                changepersonalViewpager.setCurrentItem(Constants.TAB_GOODSCHANGE,false);
                break;
            case R.id.changepersonal_tab_vocation:
                changepersonalViewpager.setCurrentItem(Constants.TAB_VOCATIONCHANGE,false);
                break;
            case R.id.changepersonal_tab_inegral:
                changepersonalViewpager.setCurrentItem(Constants.TAB_INTEGRALAWARD,false);
                break;
        }
    }
}
