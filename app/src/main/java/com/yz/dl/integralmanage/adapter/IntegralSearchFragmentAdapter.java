package com.yz.dl.integralmanage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.fragment.IntegralHistoryFragment;
import com.yz.dl.integralmanage.fragment.IntegralPresentFragment;

/**
 * 积分查询Fragment适配器
 * Created by I'M CHAMAN on 2017/9/28.
 */

public class IntegralSearchFragmentAdapter extends FragmentPagerAdapter {

    int pageSize = 2;

    public IntegralSearchFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Constants.TAB_PRESENT:
                IntegralPresentFragment integralPresentFragment = new IntegralPresentFragment();
                if (integralPresentFragment != null) {
                    return integralPresentFragment;
                }
                return new IntegralPresentFragment();
            case Constants.TAB_HISTORY:
                IntegralHistoryFragment integralHistoryFragment = new IntegralHistoryFragment();
                if (integralHistoryFragment != null) {
                    return integralHistoryFragment;
                }
                return new IntegralHistoryFragment();
            default:
                IntegralPresentFragment d_integralPresentFragment = new IntegralPresentFragment();
                if (d_integralPresentFragment != null) {
                    return d_integralPresentFragment;
                }
                return new IntegralPresentFragment();
        }
    }

    @Override
    public int getCount() {
        return pageSize;
    }
}
