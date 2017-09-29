package com.yz.dl.integralmanage.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.fragment.GoodsChangedFragment;
import com.yz.dl.integralmanage.fragment.IntegralAwardFragment;
import com.yz.dl.integralmanage.fragment.ManagerRankingFragment;
import com.yz.dl.integralmanage.fragment.ManagmentRankingFragment;
import com.yz.dl.integralmanage.fragment.OperatorRankingFragment;
import com.yz.dl.integralmanage.fragment.VocationChangedFragment;

/**
 * 个人积分兑换Fragment适配器
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class IntegralChangedFragmentAdapter extends FragmentPagerAdapter {

    private int pageSize = 3;

    public IntegralChangedFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case Constants.TAB_GOODSCHANGE:
                GoodsChangedFragment fragment_goodsPage = new GoodsChangedFragment();
                if (fragment_goodsPage != null) {
                    return fragment_goodsPage;
                }
                return new GoodsChangedFragment();
            case Constants.TAB_VOCATIONCHANGE:
                VocationChangedFragment fragment_vocationPage = new VocationChangedFragment();
                if (fragment_vocationPage != null) {
                    return fragment_vocationPage;
                }
                return new VocationChangedFragment();
            case Constants.TAB_INTEGRALAWARD:
                IntegralAwardFragment fragment_integralPage = new IntegralAwardFragment();
                if (fragment_integralPage != null) {
                    return fragment_integralPage;
                }
                return new IntegralAwardFragment();
            default:
                GoodsChangedFragment d_fragment_goodsPage = new GoodsChangedFragment();
                if (d_fragment_goodsPage != null) {
                    return d_fragment_goodsPage;
                }
                return new GoodsChangedFragment();
        }
    }

    @Override
    public int getCount() {
        return pageSize;
    }
}
