package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.view.SlideUnlockView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends Activity implements SlideUnlockView.OnLockListener {

    @Bind(R.id.login_idcard)
    EditText loginIdcard;
    @Bind(R.id.login_password)
    EditText loginPassword;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.exit)
    Button exit;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.slideunlock)
    SlideUnlockView slideUnlockView;

    private boolean isUnlocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        slideUnlockView.setmLockListener(this);
    }

    @OnClick({R.id.login, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                if (isUnlocked && !loginIdcard.getText().toString().equals("")) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("PERSON", loginIdcard.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "请先解锁！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.exit:
                break;
        }
    }

    @Override
    public void onOpenLockSuccess() {
        Toast.makeText(getApplication(), "解锁成功", Toast.LENGTH_SHORT).show();
        isUnlocked = true;
    }
}
