package com.yz.dl.integralmanage.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.RankingFragmentAdapter;
import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.view.BanSlideViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class PointRanking extends FragmentActivity {
    @Bind(R.id.pointranking_back)
    ImageView pointrankingBack;
    @Bind(R.id.ranking_search)
    ImageView rankingSearch;
    @Bind(R.id.ranking_tab_manager)
    RadioButton rankingTabManager;
    @Bind(R.id.ranking_tab_managment)
    RadioButton rankingTabManagment;
    @Bind(R.id.ranking_tab_operator)
    RadioButton rankingTabOperator;
    @Bind(R.id.homepage_bottom_layout)
    RelativeLayout homepageBottomLayout;
    @Bind(R.id.main_linear)
    LinearLayout mainLinear;
    @Bind(R.id.ranking_viewpager)
    BanSlideViewPager rankingViewpager;

    private RankingFragmentAdapter adapter;
    private int display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pointranking);
        ButterKnife.bind(this);
        display = getIntent().getIntExtra("DISPLAY",Constants.TAB_MANAGER);
        adapter = new RankingFragmentAdapter(getSupportFragmentManager());
        rankingViewpager.setAdapter(adapter);
        rankingViewpager.setOffscreenPageLimit(3);
        rankingViewpager.setScrollEnable(true);
        rankingViewpager.setCurrentItem(display, false);
    }

    @OnClick({R.id.pointranking_back, R.id.ranking_search, R.id.ranking_tab_manager, R.id.ranking_tab_managment, R.id.ranking_tab_operator})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pointranking_back:
                this.finish();
                break;
            case R.id.ranking_search:
                Toast.makeText(getApplicationContext(), "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ranking_tab_manager:
                rankingViewpager.setCurrentItem(Constants.TAB_MANAGER, false);
                break;
            case R.id.ranking_tab_managment:
                rankingViewpager.setCurrentItem(Constants.TAB_MANAGEMENT, false);
                break;
            case R.id.ranking_tab_operator:
                rankingViewpager.setCurrentItem(Constants.TAB_OPERATOR, false);
                break;
        }
    }
}
