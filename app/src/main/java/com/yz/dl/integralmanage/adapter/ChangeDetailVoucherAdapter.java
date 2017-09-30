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
 * 兑换详情 -> 代金券列表适配器
 * Created by I'M CHAMAN on 2017/9/30.
 */

public class ChangeDetailVoucherAdapter extends IntrgralBaseAdapter {
    public ChangeDetailVoucherAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = getInflater().inflate(R.layout.item_voucher_changedlist, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        AwardListBean awardListBean = (AwardListBean) getData().get(position);

        viewHolder.itemVoucherGoods.setText(awardListBean.getAward());
        viewHolder.itemVoucherPlace.setText(awardListBean.getPlace());
        viewHolder.itemVoucherTime.setText(awardListBean.getTime());
        viewHolder.itemVoucherName.setText(awardListBean.getName());
        switch (awardListBean.getState()) {
            case Constants.STATE_CHANGED:
                viewHolder.itemVoucherState.setImageResource(R.mipmap.state_change);
                break;
            case Constants.STATE_GIVE:
                viewHolder.itemVoucherState.setImageResource(R.mipmap.state_give);
                break;
        }

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.item_voucher_place)
        TextView itemVoucherPlace;
        @Bind(R.id.item_voucher_time)
        TextView itemVoucherTime;
        @Bind(R.id.item_voucher_name)
        TextView itemVoucherName;
        @Bind(R.id.item_voucher_state)
        ImageView itemVoucherState;
        @Bind(R.id.item_voucher_goods)
        TextView itemVoucherGoods;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
