package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;

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
                break;
            case R.id.changge_office_month_select:
                break;
            case R.id.change_office_voucher:
                startActivity(new Intent(getApplicationContext(),ChangeOfficeDetail.class));
                break;
            case R.id.change_office_vocation:
                startActivity(new Intent(getApplicationContext(),ChangeOfficeDetail.class));
                break;
        }
    }
}
