package com.yz.dl.integralmanage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.BaseFragment;
import com.yz.dl.integralmanage.ui.IntegralSearchIndividual;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class ManagerRankingFragment extends BaseFragment {
    @Bind(R.id.manager_name)
    TextView managerName;

    @Override
    protected void TODO(View view, Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflate(inflater, container, R.layout.fragment_manager_ranking, NULL_LOADVIEW, 0);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.manager_name)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), IntegralSearchIndividual.class));
    }
}
