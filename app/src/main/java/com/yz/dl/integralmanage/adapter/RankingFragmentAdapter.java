package com.yz.dl.integralmanage.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.fragment.ManagerRankingFragment;
import com.yz.dl.integralmanage.fragment.ManagmentRankingFragment;
import com.yz.dl.integralmanage.fragment.OperatorRankingFragment;

/**
 * 排名Fragment适配器
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class RankingFragmentAdapter extends FragmentPagerAdapter {

    private int pageSize = 3;

    public RankingFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case Constants.TAB_MANAGER:
                ManagerRankingFragment fragment_managerPage = new ManagerRankingFragment();
                if (fragment_managerPage != null) {
                    return fragment_managerPage;
                }
                return new ManagerRankingFragment();
            case Constants.TAB_MANAGEMENT:
                ManagmentRankingFragment fragment_managementPage = new ManagmentRankingFragment();
                if (fragment_managementPage != null) {
                    return fragment_managementPage;
                }
                return new ManagmentRankingFragment();
            case Constants.TAB_OPERATOR:
                OperatorRankingFragment fragment_operatorPage = new OperatorRankingFragment();
                if (fragment_operatorPage != null) {
                    return fragment_operatorPage;
                }
                return new OperatorRankingFragment();
            default:
                ManagerRankingFragment d_fragment_managerPage = new ManagerRankingFragment();
                if (d_fragment_managerPage != null) {
                    return d_fragment_managerPage;
                }
                return new ManagerRankingFragment();
        }
    }

    @Override
    public int getCount() {
        return pageSize;
    }
}
