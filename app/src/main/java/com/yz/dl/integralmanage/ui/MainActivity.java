package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.utils.CacheUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/25.
 */

public class MainActivity extends Activity {
    @Bind(R.id.main_itegralSearch)
    TextView mainItegralSearch;
    @Bind(R.id.main_checkPoint)
    TextView mainCheckPoint;
    @Bind(R.id.main_integralChange)
    TextView mainIntegralChange;
    @Bind(R.id.feed_problem)
    TextView feedProblem;
    @Bind(R.id.push_message)
    TextView pushMessage;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.main_exit)
    ImageView mainExit;

    private String IDcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        IDcard = getIntent().getStringExtra("PERSON");
    }

    @OnClick({R.id.main_itegralSearch, R.id.main_checkPoint, R.id.main_integralChange, R.id.feed_problem, R.id.push_message, R.id.main_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_itegralSearch:
                if (IDcard.equals(Constants.TYPE_MANAGER)) {
                    mStartActivity(IntegralSearch.class);
                } else if (IDcard.equals(Constants.TYPE_OPERATOR)) {
                    mStartActivity(IntegralSearchIndividual.class);
                }
                break;
            case R.id.main_checkPoint:
                if (IDcard.equals(Constants.TYPE_MANAGER)) {
                    mStartActivity(InspectorChecked.class);
                } else if (IDcard.equals(Constants.TYPE_OPERATOR)) {
                    mStartActivity(ManagerChecked.class);
                }
                break;
            case R.id.main_integralChange:
                if (IDcard.equals(Constants.TYPE_MANAGER)) {
                    mStartActivity(IntegralChangeOffice.class);
                } else if (IDcard.equals(Constants.TYPE_OPERATOR)) {
                    mStartActivity(IntegralChangePersonal.class);
                }
                break;
            case R.id.feed_problem:
                if (IDcard.equals(Constants.TYPE_MANAGER)) {
                    mStartActivity(FeedProblemOffice.class);
                } else if (IDcard.equals(Constants.TYPE_OPERATOR)) {
                    mStartActivity(FeedProblemPersonal.class);
                }
                break;
            case R.id.push_message:
                mStartActivity(MessageActivity.class);
                break;
            case R.id.main_exit:
                exitDialog();
        }
    }

    /**
     * 启动activity
     *
     * @param cls
     */
    private void mStartActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), cls);
        startActivity(intent);
    }

    /**
     * 退出当前账号
     */
    private void exitDialog() {

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] Items = {"退出", "取消"};
        builder.setItems(Items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        MainActivity.this.finish();
                        break;
                    case 1:

                        break;
                }
            }
        });
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.setTitle("退出当前账号?");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                exitDialog();
                break;
        }


        return super.onKeyDown(keyCode, event);
    }
}
