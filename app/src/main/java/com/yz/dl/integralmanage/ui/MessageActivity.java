package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.MessageAdapter;
import com.yz.dl.integralmanage.database.MessageBean;
import com.yz.dl.integralmanage.greendao.DaoMaster;
import com.yz.dl.integralmanage.greendao.DaoSession;
import com.yz.dl.integralmanage.greendao.MessageBeanDao;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 消息推送列表
 * Created by Administrator on 2017/8/29.
 */

public class MessageActivity extends Activity {

    @Bind(R.id.message_title)
    TextView messageTitle;
    @Bind(R.id.message_back)
    ImageView messageBack;
    @Bind(R.id.message_send_img)
    ImageView messageSendImg;
    @Bind(R.id.message_listview)
    ListView messageListview;

    MessageAdapter adapter;
    ArrayList<MessageBean> arrayList;

    DaoMaster.OpenHelper helper;
    DaoMaster daoMaster;
    DaoSession daoSession;


    MessageBeanDao messageBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);


//        helper = new DaoMaster.DevOpenHelper(getApplicationContext(), "message-db", null);
//        daoMaster = new DaoMaster(helper.getWritableDatabase());
//        daoSession = daoMaster.newSession();
//        messageBeanDao = daoSession.getMessageBeanDao();

        arrayList = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            MessageBean messageBean = new MessageBean();
            messageBean.setMsgcon("测试内容");
            messageBean.setSendname("测试");
            messageBean.setDate("8月7日");
            arrayList.add(messageBean);
//            messageBeanDao.save(messageBean);
        }
        adapter = new MessageAdapter(getApplicationContext(), arrayList);
        messageListview.setAdapter(adapter);
        messageListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(), MessageDetail.class));
            }
        });
        messageListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showSimpleListDialog(i);
                return true;
            }
        });
    }

    @OnClick({R.id.message_back, R.id.message_send_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_back:
                this.finish();
                break;
            case R.id.message_send_img:
                startActivity(new Intent(getApplicationContext(), MessageSendActivity.class));
                break;
        }
    }

    /**
     * 长按listviewItem弹出选择框
     */
    private void showSimpleListDialog(final int j) {
        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] Items = {"删除", "取消"};
        builder.setItems(Items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Toast.makeText(MessageActivity.this, "删除", Toast.LENGTH_LONG).show();
                        deleteMessage(j);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:

                        break;
                }
            }
        });
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.show();
    }

    private void deleteMessage(int id) {
        Long i = Long.parseLong(id + "");
//        messageBeanDao.deleteByKey(i);
        arrayList.remove(id);
        Log.i("TAG", i + "");
    }

}
