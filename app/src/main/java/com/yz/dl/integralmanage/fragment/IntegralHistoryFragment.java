package com.yz.dl.integralmanage.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.HistoryIntegralListAdapter;
import com.yz.dl.integralmanage.base.BaseFragment;
import com.yz.dl.integralmanage.bean.HistoryItemBean;
import com.yz.dl.integralmanage.bean.RankingBean;
import com.yz.dl.integralmanage.ui.IntegralSearch;
import com.yz.dl.integralmanage.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 历史积分Fragment
 * Created by I'M CHAMAN on 2017/9/28.
 */

public class IntegralHistoryFragment extends BaseFragment {
    @Bind(R.id.integralhistory_fromDate)
    TextView integralhistoryFromDate;
    @Bind(R.id.integralhistory_toDate)
    TextView integralhistoryToDate;
    @Bind(R.id.integralhistory_person)
    TextView integralhistoryPerson;
    @Bind(R.id.integralhistory_search)
    ImageView integralhistorySearch;
    @Bind(R.id.integralhistory_list)
    ListViewForScrollView integralhistoryList;


    private Calendar calendar;// 用来装日期的
    private DatePickerDialog dialog;

    HistoryIntegralListAdapter historyIntegralListAdapter;
    ArrayList<HistoryItemBean> list;

    @Override
    protected void TODO(View view, Bundle savedInstanceState) {

        list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            HistoryItemBean historyItemBean = new HistoryItemBean();
            historyItemBean.setDateTime("2017年10月" + i + "日");
            historyItemBean.setPoint(i+10);
            historyItemBean.setTypeName("纯枪销量");
            historyItemBean.setTypeNum(9000+i*8 + "吨");
            list.add(historyItemBean);
        }

        historyIntegralListAdapter = new HistoryIntegralListAdapter(getActivity(),list);

        integralhistoryList.setAdapter(historyIntegralListAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflate(inflater, container, R.layout.fragment_integralhistory, NULL_LOADVIEW, 0);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.integralhistory_fromDate, R.id.integralhistory_toDate, R.id.integralhistory_person, R.id.integralhistory_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.integralhistory_fromDate:
                dateSelect(integralhistoryFromDate);
                break;
            case R.id.integralhistory_toDate:
                dateSelect(integralhistoryToDate);
                break;
            case R.id.integralhistory_person:
                officeSelect();
                break;
            case R.id.integralhistory_search:
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_LONG).show();
                break;
        }
    }


    /**
     * 人员选择
     */
    private void officeSelect() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] items = {"李四", "张彬", "陈红"};

//        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                integralhistoryPerson.setText(items[i]);
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /**
     * 时间选择器
     */
    private void dateSelect(final TextView textView) {
        calendar = Calendar.getInstance();
        dialog = new DatePickerDialog(getActivity(), DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                textView.setText(year + "年" + monthOfYear + "月");
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        hideDatePickerDay(dialog);
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

}
