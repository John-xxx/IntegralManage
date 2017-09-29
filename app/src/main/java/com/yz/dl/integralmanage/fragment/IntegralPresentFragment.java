package com.yz.dl.integralmanage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.BaseFragment;
import com.yz.dl.integralmanage.ui.DynamicsIntegral;
import com.yz.dl.integralmanage.ui.PointRanking;
import com.yz.dl.integralmanage.ui.StaticIntegral;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 当前积分Fragment
 * Created by I'M CHAMAN on 2017/9/28.
 */

public class IntegralPresentFragment extends BaseFragment {
    @Bind(R.id.person_select)
    TextView personSelect;
    @Bind(R.id.person_month_select)
    TextView personMonthSelect;
    @Bind(R.id.person_date_select)
    TextView personDateSelect;
    @Bind(R.id.person_area)
    TextView personArea;
    @Bind(R.id.person_gas)
    TextView personGas;
    @Bind(R.id.person_jobs)
    TextView personJobs;
    @Bind(R.id.person_totalpoint)
    TextView personTotalpoint;
    @Bind(R.id.person_staticpoint)
    TextView personStaticpoint;
    @Bind(R.id.static_point)
    LinearLayout staticPoint;
    @Bind(R.id.person_dynamicspoint)
    TextView personDynamicspoint;
    @Bind(R.id.dynamics_point)
    LinearLayout dynamicsPoint;
    @Bind(R.id.present_integral_office_ranking)
    TextView presentIntegralOfficeRanking;
    @Bind(R.id.integral_office_ranking_layout)
    LinearLayout integralOfficeRankingLayout;
    @Bind(R.id.present_integral_area_ranking)
    TextView presentIntegralAreaRanking;
    @Bind(R.id.integral_area_ranking_layout)
    LinearLayout integralAreaRankingLayout;
    @Bind(R.id.present_integral_gas_ranking)
    TextView presentIntegralGasRanking;
    @Bind(R.id.integral_gas_ranking_layout)
    LinearLayout integralGasRankingLayout;

    @Override
    protected void TODO(View view, Bundle savedInstanceState) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflate(inflater, container, R.layout.fragment_integralpresent, NULL_LOADVIEW, 0);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.person_select, R.id.person_month_select, R.id.person_date_select, R.id.static_point, R.id.dynamics_point, R.id.integral_office_ranking_layout, R.id.integral_area_ranking_layout, R.id.integral_gas_ranking_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_select:
                break;
            case R.id.person_month_select:
                break;
            case R.id.person_date_select:
                break;
            case R.id.static_point:
                startActivity(new Intent(getActivity(), StaticIntegral.class));
                break;
            case R.id.dynamics_point:
                startActivity(new Intent(getActivity(), DynamicsIntegral.class));
                break;
            case R.id.integral_office_ranking_layout:
                startActivity(new Intent(getActivity(), PointRanking.class));
                break;
            case R.id.integral_area_ranking_layout:
                startActivity(new Intent(getActivity(), PointRanking.class));
                break;
            case R.id.integral_gas_ranking_layout:
                startActivity(new Intent(getActivity(), PointRanking.class));
                break;
        }
    }
}
