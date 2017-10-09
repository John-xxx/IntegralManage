package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.comm.Constants;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 管理人员->积分查询类
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class IntegralSearch extends Activity {
    @Bind(R.id.integralsearch_back)
    ImageView integralsearchBack;
    @Bind(R.id.office_select)
    TextView officeSelect;
    @Bind(R.id.month_select)
    TextView monthSelect;
    @Bind(R.id.date_select)
    TextView dateSelect;
    @Bind(R.id.total_station_num)
    TextView totalStationNum;
    @Bind(R.id.total_person_num)
    TextView totalPersonNum;
    @Bind(R.id.stationManager)
    TextView stationManager;
    @Bind(R.id.stationManager_num)
    TextView stationManagerNum;
    @Bind(R.id.stationManager_avg)
    TextView stationManagerAvg;
    @Bind(R.id.management)
    TextView management;
    @Bind(R.id.management_num)
    TextView managementNum;
    @Bind(R.id.management_avg)
    TextView managementAvg;
    @Bind(R.id.operator)
    TextView operator;
    @Bind(R.id.operator_num)
    TextView operatorNum;
    @Bind(R.id.operator_avg)
    TextView operatorAvg;



    private Calendar calendar;// 用来装日期的
    private DatePickerDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_integralsearch);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.integralsearch_back, R.id.office_select, R.id.month_select, R.id.date_select, R.id.stationManager, R.id.management, R.id.operator})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.integralsearch_back:
                this.finish();
                break;
            case R.id.office_select:
                officeSelect();
                break;
            case R.id.month_select:
            case R.id.date_select:
                dateSelect();
                break;
            case R.id.stationManager:
                intent.setClass(getApplicationContext(),PointRanking.class);
                intent.putExtra("DISPLAY", Constants.TAB_MANAGER);
                startActivity(intent);
                break;
            case R.id.management:
                intent.setClass(getApplicationContext(),PointRanking.class);
                intent.putExtra("DISPLAY", Constants.TAB_MANAGEMENT);
                startActivity(intent);
                break;
            case R.id.operator:
                intent.setClass(getApplicationContext(),PointRanking.class);
                intent.putExtra("DISPLAY", Constants.TAB_OPERATOR);
                startActivity(intent);
                break;
        }
    }

    /**
     * 时间选择器
     */
    private void dateSelect(){
        calendar = Calendar.getInstance();
        dialog = new DatePickerDialog(IntegralSearch.this,DatePickerDialog.THEME_HOLO_LIGHT,new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {

                dateSelect.setText(year + "年");
                monthSelect.setText(monthOfYear + 1 + "月");
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        hideDatePickerDay(dialog);
        dialog.show();
    }


    /**
     * 隐藏显示的某月的某一天
     * @param customDialog
     */
    private void hideDatePickerDay(DatePickerDialog customDialog){
        ViewGroup group=((ViewGroup) ((ViewGroup) customDialog.getDatePicker().getChildAt(0))
                .getChildAt(0));
        boolean bool=false;
        for (int i=0;i<group.getChildCount();i++){
            View view=group.getChildAt(i);
            String viewName=view.getClass().getName();
            if (bool){
                view.setVisibility(View.GONE);
                continue;
            }
            if (view instanceof android.widget.NumberPicker ){
                int maxNum=((NumberPicker)view).getMaxValue();
                if (maxNum==11) bool=true;
            }
        }
    }

    /**
     * 二级公司选择
     */
    private void officeSelect(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items = {"成都分公司", "绵阳分公司"};
//        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                officeSelect.setText(items[i]);
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
