package com.yz.dl.integralmanage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.ChangeDetailVoucherAdapter;
import com.yz.dl.integralmanage.base.BaseFragment;
import com.yz.dl.integralmanage.bean.AwardListBean;
import com.yz.dl.integralmanage.comm.Constants;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 代金券奖励Fragment
 * Created by I'M CHAMAN on 2017/9/29.
 */

public class ChangedVoucherFragment extends BaseFragment {
    @Bind(R.id.fragment_voucher_listview)
    ListView fragmentVoucherListview;
    ChangeDetailVoucherAdapter adapter;
    ArrayList<AwardListBean> arrayList;

    @Override
    protected void TODO(View view, Bundle savedInstanceState) {
        arrayList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            AwardListBean awardListBean = new AwardListBean();
            if (i % 2 == 0) {
                awardListBean.setState(Constants.STATE_GIVE);
            } else {
                awardListBean.setState(Constants.STATE_CHANGED);
            }
            awardListBean.setAward("奖励假期" + i + " 天");
            awardListBean.setName("章程" + i);
            awardListBean.setPlace("锦江区 - 城北加油站");
            awardListBean.setTime("2017-05-09");
            arrayList.add(awardListBean);
        }
        adapter = new ChangeDetailVoucherAdapter(getActivity(),arrayList);
        fragmentVoucherListview.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voucher_changed, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
