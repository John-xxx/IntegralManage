package com.yz.dl.integralmanage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.IntrgralBaseAdapter;
import com.yz.dl.integralmanage.database.MessageBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/1.
 */

public class MessageAdapter extends IntrgralBaseAdapter {


    public MessageAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null){
            view = getInflater().inflate(R.layout.item_message, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        MessageBean messageBean = (MessageBean) getData().get(position);
        viewHolder.itemMessageDate.setText(messageBean.getDate());
        viewHolder.itemMessageSend.setText(messageBean.getSendname());
        viewHolder.itemMessageTitle.setText(messageBean.getMsgcon());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.item_message_date)
        TextView itemMessageDate;
        @Bind(R.id.item_message_title)
        TextView itemMessageTitle;
        @Bind(R.id.item_message_send)
        TextView itemMessageSend;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
