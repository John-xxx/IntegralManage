package com.yz.dl.integralmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.bean.MessageBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/1.
 */

public class MessageAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<MessageBean> data;
    private LayoutInflater  inflater;

    public  MessageAdapter(Context context , ArrayList<MessageBean> data){
        this.context =  context;
        this.data  = data;
        this.inflater  = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int postion) {
        return data.get(postion);
    }

    @Override
    public long getItemId(int postion) {
        return postion;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Hold  hold ;
        if(view==null){
            hold = new Hold();
            view = inflater.inflate(R.layout.item_message,null);
            hold.titView  =  (TextView) view.findViewById(R.id.item_message_title);
            hold.sendView = (TextView) view.findViewById(R.id.item_message_send);
            hold.dateView  =  (TextView) view.findViewById(R.id.item_message_date);
            view.setTag(hold);
        }else{
            hold  =  (Hold) view.getTag();
        }
        if(data.size()>0){
            hold.titView.setText(data.get(i).getTitle());
            hold.sendView.setText(data.get(i).getSendname());
            hold.dateView.setText(data.get(i).getDate());
        }
        return view;
    }


    static  class   Hold{
        TextView  titView;
        TextView   sendView;
        TextView  dateView;

    }


}
