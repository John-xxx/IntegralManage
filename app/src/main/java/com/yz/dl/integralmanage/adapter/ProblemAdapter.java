package com.yz.dl.integralmanage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.IntrgralBaseAdapter;
import com.yz.dl.integralmanage.bean.ProblemBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by I'M CHAMAN on 2017/9/29.
 */

public class ProblemAdapter extends IntrgralBaseAdapter {
    public ProblemAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = getInflater().inflate(R.layout.item_feedbacklist, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ProblemBean problemBean = (ProblemBean) getData().get(position);
        viewHolder.itemFeedbackDescribe.setText(problemBean.getContent());
        viewHolder.itemFeedbackName.setText(problemBean.getName());
        viewHolder.itemFeedbackTime.setText(problemBean.getTime());
        viewHolder.itemFeedbackTarget.setText(problemBean.getTarget());

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.item_feedback_name)
        TextView itemFeedbackName;
        @Bind(R.id.item_feedback_time)
        TextView itemFeedbackTime;
        @Bind(R.id.item_feedback_target)
        TextView itemFeedbackTarget;
        @Bind(R.id.item_feedback_describe)
        TextView itemFeedbackDescribe;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
