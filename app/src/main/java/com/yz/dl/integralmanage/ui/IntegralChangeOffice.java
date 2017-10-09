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
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 机关单位积分汇总界面
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class IntegralChangeOffice extends Activity {
    @Bind(R.id.change_office_back)
    ImageView changeOfficeBack;
    @Bind(R.id.change_office_select)
    TextView changeOfficeSelect;
    @Bind(R.id.changge_office_month_select)
    TextView changgeOfficeMonthSelect;
    @Bind(R.id.change_office_voucher)
    LinearLayout changeOfficeVoucher;
    @Bind(R.id.change_office_vocation)
    LinearLayout changeOfficeVocation;
    @Bind(R.id.change_office_residue_point)
    TextView changeOfficeResiduePoint;
    @Bind(R.id.change_office_changed_point)
    TextView changeOfficeChangedPoint;
    @Bind(R.id.change_office_moneyof_goods)
    TextView changeOfficeMoneyofGoods;


    private Calendar calendar;// 用来装日期的
    private DatePickerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_integralchangeoffice);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.change_office_back, R.id.change_office_select, R.id.changge_office_month_select, R.id.change_office_voucher, R.id.change_office_vocation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_office_back:
                this.finish();
                break;
            case R.id.change_office_select:
                officeSelect();
                break;
            case R.id.changge_office_month_select:
                dateSelect();
                break;
            case R.id.change_office_voucher:
                startActivity(new Intent(getApplicationContext(), ChangeOfficeDetail.class));
                break;
            case R.id.change_office_vocation:
                startActivity(new Intent(getApplicationContext(), ChangeOfficeDetail.class));
                break;
        }
    }

    /**
     * 二级公司选择
     */
    private void officeSelect() {
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
                changeOfficeSelect.setText(items[i]);
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /**
     * 时间选择器
     */
    private void dateSelect() {
        calendar = Calendar.getInstance();
        dialog = new DatePickerDialog(IntegralChangeOffice.this,DatePickerDialog.THEME_HOLO_LIGHT,new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                changgeOfficeMonthSelect.setText(year + "年");
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        hideDatePickerDay(dialog);
        dialog.show();
    }


    /**
     * 隐藏显示的某月和某月的某一天
     *
     * @param customDialog
     */
    private void hideDatePickerDay(DatePickerDialog customDialog) {
        ViewGroup group = ((ViewGroup) ((ViewGroup) customDialog.getDatePicker().getChildAt(0)).getChildAt(0));
        boolean bool = false;
        for (int i = 0; i < group.getChildCount(); i++) {
            View view = group.getChildAt(i);
            String viewName = view.getClass().getName();
            if (view instanceof android.widget.NumberPicker) {
                int maxNum = ((NumberPicker) view).getMaxValue();
                if (maxNum == 11) bool = true;
            }
            if (bool) {
                view.setVisibility(View.GONE);
            }
        }
    }

}
