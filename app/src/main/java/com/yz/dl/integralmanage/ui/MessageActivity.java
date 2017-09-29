package com.yz.dl.integralmanage.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.yze.zsy.zsyjif.R;
import com.yze.zsy.zsyjif.adapter.MessageAdapter;
import com.yze.zsy.zsyjif.bean.Message;
import com.yze.zsy.zsyjif.comtools.BaseActivity;
import com.yze.zsy.zsyjif.db.DbManager;
import com.yze.zsy.zsyjif.mvp.p.MessagePresenter;
import com.yze.zsy.zsyjif.mvp.v.OnMessageViewListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/8/29.
 */

public class MessageActivity extends BaseActivity implements OnMessageViewListener {

    private String  msg;
    private ListView  msglist;
    private MessageAdapter messageAdapter ;
    private ImageView back;
    private MessagePresenter messagePresenter   =new MessagePresenter(this);
    private  ArrayList<Message>  data;
    private  ImageView sendImg;
    private  static   final   String  TAG =  MessageActivity.class.getSimpleName();
    private  static  final   int  RUI = 1;
    private Handler  handler  =new Handler(){

        @Override
        public void handleMessage(android.os.Message  msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case  RUI:
                    messageAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };


    @Override
    public void before() {
        setContentView(R.layout.activity_message);
       // messagePresenter.getMessages();
        data  =new ArrayList<Message>();
        Message msg = new Message();
        msg.setMsgcon("测试内容");
        msg.setSendname("测试");
        msg.setDate("8月7日");
        data.add(msg);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_message);
        back  = (ImageView) findViewById(R.id.message_back);
        sendImg   =   (ImageView) findViewById(R.id.message_send_img);
        msglist   =  (ListView) findViewById(R.id.message_listview);
        messageAdapter  =  new MessageAdapter(this,data);
        msglist.setAdapter(messageAdapter);

    }
    @Override
    public void afterprc() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        messageAdapter.notifyDataSetChanged();
    }

    @Override
    public void setViewOnListener() {
        back.setOnClickListener(this);
        sendImg.setOnClickListener(this);
        //长按删除单条消息
        msglist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int postion, long l) {
                new AlertDialog.Builder(MessageActivity.this).setTitle("删除").setMessage("是否删除该消息").setCancelable(false).setNegativeButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DbManager.deleteMessage(data.get(postion));
                        data.remove(postion);
                        messageAdapter.notifyDataSetChanged();
                    }
                }).setPositiveButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
                return false;
            }
        });
        msglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle  bundle  =new Bundle();
                Intent  intent =  new Intent();
                bundle.putString("name",data.get(i).getSendname());
                bundle.putString("con",data.get(i).getMsgcon());
                bundle.putString("date",data.get(i).getDate());
                bundle.putInt("id",data.get(i).getId());
                data.get(i).setMsgstate(1);
                intent.putExtra("msg",bundle);
                intent.setClass(MessageActivity.this,MessageConActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.message_back:
                MessageActivity.this.finish();
                break;
            case  R.id.message_send_img:
                startActivity(new Intent(MessageActivity.this,MessageSendActivity.class));
                break;
        }
    }

    @Override
    public void messages(List<Message> messages) {
  //     System.out.println(TAG +"==================收到消息了==数量================="+messages.size());
      // System.out.println(TAG +"==================收到消息了==================="+messages.get(0).getSendname());
      if(messages!=null){
          data.addAll(messages);
          handler.sendEmptyMessage(RUI);
      }
    }

}
