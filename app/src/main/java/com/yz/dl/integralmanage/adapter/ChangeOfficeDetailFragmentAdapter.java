package com.yz.dl.integralmanage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.fragment.ChangedVocationFragment;
import com.yz.dl.integralmanage.fragment.ChangedVoucherFragment;
import com.yz.dl.integralmanage.fragment.IntegralHistoryFragment;
import com.yz.dl.integralmanage.fragment.IntegralPresentFragment;

/**
 * 奖励明细Fragment适配器
 * Created by I'M CHAMAN on 2017/9/28.
 */

public class ChangeOfficeDetailFragmentAdapter extends FragmentPagerAdapter {

    int pageSize = 2;

    public ChangeOfficeDetailFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Constants.TAB_CHANGEDVOUCHAER:
                ChangedVoucherFragment changedVoucherFragment = new ChangedVoucherFragment();
                if (changedVoucherFragment != null) {
                    return changedVoucherFragment;
                }
                return new ChangedVoucherFragment();
            case Constants.TAB_CHANGEDVOCATION:
                ChangedVocationFragment changedVocationFragment = new ChangedVocationFragment();
                if (changedVocationFragment != null) {
                    return changedVocationFragment;
                }
                return new ChangedVocationFragment();
            default:
                ChangedVoucherFragment d_integralPresentFragment = new ChangedVoucherFragment();
                if (d_integralPresentFragment != null) {
                    return d_integralPresentFragment;
                }
                return new ChangedVoucherFragment();
        }
    }

    @Override
    public int getCount() {
        return pageSize;
    }
}
