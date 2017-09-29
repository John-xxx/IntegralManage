package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.view.BanSlideViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/29.
 */

public class ChangeOfficeDetail extends FragmentActivity {
    @Bind(R.id.change_office_detail_back)
    ImageView changeOfficeDetailBack;
    @Bind(R.id.change_office_detail_search)
    ImageView changeOfficeDetailSearch;
    @Bind(R.id.changge_office_tab_voucher)
    RadioButton changgeOfficeTabVoucher;
    @Bind(R.id.changge_office_tab_vocation)
    RadioButton changgeOfficeTabVocation;
    @Bind(R.id.bottom_layout)
    RelativeLayout bottomLayout;
    @Bind(R.id.integral_linear)
    LinearLayout integralLinear;
    @Bind(R.id.change_office_detail_viewpager)
    BanSlideViewPager changeOfficeDetailViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeofficedetail);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.change_office_detail_back, R.id.change_office_detail_search, R.id.changge_office_tab_voucher, R.id.changge_office_tab_vocation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_office_detail_back:
                break;
            case R.id.change_office_detail_search:
                break;
            case R.id.changge_office_tab_voucher:
                break;
            case R.id.changge_office_tab_vocation:
                break;
        }
    }
}
