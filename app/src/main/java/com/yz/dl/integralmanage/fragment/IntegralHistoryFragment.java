package com.yz.dl.integralmanage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * 历史积分Fragment
 * Created by I'M CHAMAN on 2017/9/28.
 */

public class IntegralHistoryFragment extends BaseFragment {
    @Override
    protected void TODO(View view, Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflate(inflater, container, R.layout.fragment_integralhistory, NULL_LOADVIEW, 0);
        ButterKnife.bind(this, view);
        return view;
    }
}
