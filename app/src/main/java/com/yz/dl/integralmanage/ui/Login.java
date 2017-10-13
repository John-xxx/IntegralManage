package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.api.IntegralAPI;
import com.yz.dl.integralmanage.callback.IntegralHashMapCallback;
import com.yz.dl.integralmanage.utils.IntegralHashMap;
import com.yz.dl.integralmanage.utils.IntegralUI;
import com.yz.dl.integralmanage.utils.MD5;
import com.yz.dl.integralmanage.view.IntegarlWaitDialog;
import com.yz.dl.integralmanage.view.SlideUnlockView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;


/**
 * 登录界面
 */
public class Login extends Activity implements SlideUnlockView.OnLockListener {

    @Bind(R.id.login_idcard)
    EditText loginIdcard;
    @Bind(R.id.login_password)
    EditText loginPassword;
    //    @Bind(R.id.login)
//    Button login;
    @Bind(R.id.exit)
    Button exit;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.slideunlock)
    SlideUnlockView slideUnlockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        slideUnlockView.setmLockListener(this);
    }

    @OnClick({R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit:
                break;
        }
    }

    @Override
    public void onOpenLockSuccess() {
        if (!loginIdcard.getText().toString().equals("")) {

//            IntegralAPI.login(getApplicationContext(), loginIdcard.getText().toString(), loginPassword.getText().toString(), loginCallback);
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra("PERSON", loginIdcard.getText().toString());
            Log.i("TAG", MD5.encrypt(loginPassword.getText().toString()));
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "请输入您的身份证号", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 登录回调函数
     */
    IntegralHashMapCallback loginCallback = new IntegralHashMapCallback() {

        Dialog dialog;

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            dialog = IntegarlWaitDialog.openIntegralWaitDialog(getApplicationContext(), "登录中...");
            dialog.show();
        }

        @Override
        public void onError(Call call, Exception e) {
            dialog.dismiss();
        }

        @Override
        public void onResponse(IntegralHashMap response) {
            dialog.dismiss();
            if (response == null) {
//                NewDataToast.makeText(This(), getString(R.string.network_error), Gravity.CENTER, false).show();
            } else {
                if (response.getString("code").equals("200")) {
//                    NewDataToast.makeText(This(), getString(R.string.loginsuccess), Gravity.CENTER, false).show();
//                    ZSTUser.initFields(response.getMSRHashMap("data"));
//                    ZSTUser.getInstance().save(This());
//                    ZSTUser.loadZSTUserDataFromSP(This());
//                    sendBroadcast(new Intent(Constants.RECEIVER_MY_REFRESH));
                    finish();
                } else {
//                    NewDataToast.makeText(This(), response.getString("message"), Gravity.CENTER, false).show();
                }
            }
        }
    };
}
