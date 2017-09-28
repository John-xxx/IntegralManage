package com.yz.dl.integralmanage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.IntrgralBaseAdapter;
import com.yz.dl.integralmanage.bean.RankingBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 综管员排名适器
 * Created by I'M CHAMAN on 2017/9/28.
 */

public class ManagementRankingAdapter extends IntrgralBaseAdapter {


    public ManagementRankingAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = getInflater().inflate(R.layout.item_management_ranking, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        RankingBean rankingBean = (RankingBean) getData().get(position);
        viewHolder.itemRangkingNum.setText(rankingBean.getNum() + "");
        viewHolder.itemRangkingName.setText(rankingBean.getName());
        viewHolder.itemRangkingArea.setText(rankingBean.getArea());
        viewHolder.itemRangkingGas.setText(rankingBean.getGas());
        viewHolder.itemRangkingTotal.setText(rankingBean.getTotal());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.item_managementrangking_num)
        TextView itemRangkingNum;
        @Bind(R.id.item_managementrangking_name)
        TextView itemRangkingName;
        @Bind(R.id.item_managementrangking_area)
        TextView itemRangkingArea;
        @Bind(R.id.item_managementrangking_gas)
        TextView itemRangkingGas;
        @Bind(R.id.item_managementrangking_total)
        TextView itemRangkingTotal;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
