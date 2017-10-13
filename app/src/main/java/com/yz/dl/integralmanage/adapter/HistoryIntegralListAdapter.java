package com.yz.dl.integralmanage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.IntrgralBaseAdapter;
import com.yz.dl.integralmanage.bean.HistoryItemBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 历史积分列表适配器
 * Created by chaman on 2017/10/9.
 */

public class HistoryIntegralListAdapter extends IntrgralBaseAdapter {
    public HistoryIntegralListAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null) {
            view = getInflater().inflate(R.layout.item_historyintegral_listview, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        HistoryItemBean historyItemBean = (HistoryItemBean) getData().get(position);


        viewHolder.itemHistoryintegralDate.setText(historyItemBean.getDateTime());
        viewHolder.itemHistoryintegralTypeName.setText(historyItemBean.getTypeName());
        viewHolder.itemHistoryintegralTypeNum.setText(historyItemBean.getTypeNum());
        if (position % 2 == 0) {
            viewHolder.itemHistoryintegralPoint.setTextColor(getContext().getResources().getColor(R.color.gray_holo_dark));
            viewHolder.itemHistoryintegralPoint.setText("-" + historyItemBean.getPoint());
        }else {
            viewHolder.itemHistoryintegralPoint.setText("+" + historyItemBean.getPoint());
        }
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.item_historyintegral_date)
        TextView itemHistoryintegralDate;
        @Bind(R.id.item_historyintegral_typeName)
        TextView itemHistoryintegralTypeName;
        @Bind(R.id.item_historyintegral_typeNum)
        TextView itemHistoryintegralTypeNum;
        @Bind(R.id.item_historyintegral_point)
        TextView itemHistoryintegralPoint;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
