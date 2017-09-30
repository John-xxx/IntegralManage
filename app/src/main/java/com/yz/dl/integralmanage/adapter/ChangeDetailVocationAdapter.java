package com.yz.dl.integralmanage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.IntrgralBaseAdapter;
import com.yz.dl.integralmanage.bean.AwardListBean;
import com.yz.dl.integralmanage.comm.Constants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 兑换详情 -> 假期兑换列表适配器
 * Created by I'M CHAMAN on 2017/9/30.
 */

public class ChangeDetailVocationAdapter extends IntrgralBaseAdapter {
    public ChangeDetailVocationAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = getInflater().inflate(R.layout.item_vocation_changedlist, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        AwardListBean awardListBean = (AwardListBean) getData().get(position);

        viewHolder.itemVocationGoods.setText(awardListBean.getAward());
        viewHolder.itemVocationrPlace.setText(awardListBean.getPlace());
        viewHolder.itemVocationTime.setText(awardListBean.getTime());
        viewHolder.itemVocationName.setText(awardListBean.getName());
        switch (awardListBean.getState()) {
            case Constants.STATE_CHANGED:
                viewHolder.itemVocationState.setImageResource(R.mipmap.state_change);
                break;
            case Constants.STATE_GIVE:
                viewHolder.itemVocationState.setImageResource(R.mipmap.state_give);
                break;
        }
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.item_vocationr_place)
        TextView itemVocationrPlace;
        @Bind(R.id.item_vocation_time)
        TextView itemVocationTime;
        @Bind(R.id.item_vocation_name)
        TextView itemVocationName;
        @Bind(R.id.item_vocation_state)
        ImageView itemVocationState;
        @Bind(R.id.item_vocation_goods)
        TextView itemVocationGoods;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
