package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yz.dl.integralmanage.R;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class InspectorChecked extends Activity {
    @Bind(R.id.inspectorchecked_back)
    ImageView inspectorcheckedBack;
    @Bind(R.id.inspector_office_select)
    TextView inspectorOfficeSelect;
    @Bind(R.id.add_accessory)
    ImageView addAccessory;
    @Bind(R.id.accessory_layout)
    LinearLayout accessoryLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_checked);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.inspectorchecked_back, R.id.inspector_office_select, R.id.add_accessory})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inspectorchecked_back:
                break;
            case R.id.inspector_office_select:
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                //设置标题
//                builder.setTitle("请选择");
//                //设置图标
//                builder.setIcon(R.mipmap.ic_launcher);
//                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getApplicationContext(), "你点击的是条目" + i, Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.create();
//                builder.show();
                break;
            case R.id.add_accessory:
                break;
        }
    }
}
