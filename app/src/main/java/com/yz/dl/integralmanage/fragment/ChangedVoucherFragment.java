package com.yz.dl.integralmanage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by I'M CHAMAN on 2017/9/29.
 */

public class ChangedVoucherFragment extends BaseFragment {
    @Bind(R.id.fragment_voucher_listview)
    ListView fragmentVoucherListview;

    @Override
    protected void TODO(View view, Bundle savedInstanceState) {

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
