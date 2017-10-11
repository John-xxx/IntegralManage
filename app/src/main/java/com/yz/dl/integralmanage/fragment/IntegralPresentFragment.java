package com.yz.dl.integralmanage.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.BaseFragment;
import com.yz.dl.integralmanage.ui.DynamicsIntegral;
import com.yz.dl.integralmanage.ui.IntegralSearch;
import com.yz.dl.integralmanage.ui.PointRanking;
import com.yz.dl.integralmanage.ui.StaticIntegral;
import com.yz.dl.integralmanage.view.LineChartView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    @Bind(R.id.fragment_integralpresent_layout)
    RelativeLayout fragmentIntegralpresentLayout;


    private Calendar calendar;// 用来装日期的
    private DatePickerDialog dialog;

    @Override
    protected void TODO(View view, Bundle savedInstanceState) {
        setView();
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
                officeSelect();
                break;
            case R.id.person_month_select:
            case R.id.person_date_select:
                dateSelect();
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

    /**
     * 时间选择器
     */
    private void dateSelect() {
        calendar = Calendar.getInstance();
        dialog = new DatePickerDialog(getActivity(), DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                personDateSelect.setText(year + "年");
                personMonthSelect.setText(monthOfYear + 1 + "月");
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        hideDatePickerDay(dialog);
        dialog.getDatePicker().setCalendarViewShown(false);
        dialog.show();
    }


    /**
     * 隐藏显示的某月的某一天
     *
     * @param customDialog
     */
    private void hideDatePickerDay(DatePickerDialog customDialog) {
        ViewGroup group = ((ViewGroup) ((ViewGroup) customDialog.getDatePicker().getChildAt(0))
                .getChildAt(0));
        boolean bool = false;
        for (int i = 0; i < group.getChildCount(); i++) {
            View view = group.getChildAt(i);
            String viewName = view.getClass().getName();
            if (bool) {
                view.setVisibility(View.GONE);
                continue;
            }
            if (view instanceof android.widget.NumberPicker) {
                int maxNum = ((NumberPicker) view).getMaxValue();
                if (maxNum == 11) bool = true;
            }
        }
    }

    /**
     * 人员选择
     */
    private void officeSelect() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] items = {"聂仁杰", "王思聪"};
//        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                personSelect.setText(items[i]);
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setView() {
        List<String> xLineDate = new ArrayList<>();
        List<Integer> yLineDate = new ArrayList<>();
        List<Integer> lineOneDate = new ArrayList<>();
        List<Integer> lineTwoDate = new ArrayList<>();
        int[] oneInt = {24, 35, 43, 16, 24, 37, 56};
        int[] twoInt = {64, 15, 23, 46, 54, 47, 26};
        int[] yInt = {0, 20, 40, 60, 80};
        String[] xString = {"1月", "2月", "3月", "4月", "5月", "6月", "7月"};
        for (int i = 0; i < oneInt.length; i++) {
            xLineDate.add(xString[i]);
            lineOneDate.add(oneInt[i]);
            lineTwoDate.add(twoInt[i]);
        }
        for (int j = 0; j < yInt.length; j++) {
            yLineDate.add(yInt[j]);
        }
        fragmentIntegralpresentLayout.addView(new LineChartView(getActivity(), lineOneDate, lineTwoDate, xLineDate, yLineDate));
    }
}
