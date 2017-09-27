package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.yz.dl.integralmanage.R;

/**
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class IntegralChangeOffice extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_integralchangeoffice);
    }
}
