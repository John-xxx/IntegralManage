package com.yz.dl.integralmanage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.IntrgralBaseAdapter;
import com.yz.dl.integralmanage.bean.RankingBean;
import com.yz.dl.integralmanage.utils.InrgralHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class RankingAdapter extends IntrgralBaseAdapter {

    public RankingAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = getInflater().inflate(R.layout.item_ranking, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        RankingBean rankingBean = (RankingBean) getData().get(position);
        viewHolder.itemRangkingNum.setText(rankingBean.getNum()+"");
        viewHolder.itemRangkingName.setText(rankingBean.getName());
        viewHolder.itemRangkingArea.setText(rankingBean.getArea());
        viewHolder.itemRangkingGas.setText(rankingBean.getGas());
        viewHolder.itemRangkingTotal.setText(rankingBean.getTotal());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.item_rangking_num)
        TextView itemRangkingNum;
        @Bind(R.id.item_rangking_name)
        TextView itemRangkingName;
        @Bind(R.id.item_rangking_area)
        TextView itemRangkingArea;
        @Bind(R.id.item_rangking_gas)
        TextView itemRangkingGas;
        @Bind(R.id.item_rangking_total)
        TextView itemRangkingTotal;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
