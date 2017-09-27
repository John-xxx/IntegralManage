package com.yz.dl.integralmanage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.RankingAdapter;
import com.yz.dl.integralmanage.base.BaseFragment;
import com.yz.dl.integralmanage.bean.RankingBean;
import com.yz.dl.integralmanage.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class ManagerRankingFragment extends BaseFragment {
    @Bind(R.id.ranking_list)
    ListViewForScrollView managerName;

    ArrayList<RankingBean> list;
    RankingAdapter adapter;

    @Override
    protected void TODO(View view, Bundle savedInstanceState) {
        list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            RankingBean rankingBean = new RankingBean();
            rankingBean.setArea("成都");
            rankingBean.setNum(i);
            rankingBean.setGas("南苑" + i);
            rankingBean.setName("张强" + i);
            rankingBean.setTotal((90 - i) + " ");
            list.add(rankingBean);
        }
        adapter = new RankingAdapter(getActivity(), list);
        managerName.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflate(inflater, container, R.layout.fragment_manager_ranking, NULL_LOADVIEW, 0);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void sortInfo() {
        Comparator<RankingBean> itemComparator = new Comparator<RankingBean>() {
            public int compare(RankingBean info1, RankingBean info2) {
                return info1.getTotal().compareTo(info2.getTotal());
            }
        };
        Collections.sort(list, itemComparator);
    }

    private void refreshAdapter() {
        sortInfo();
//        managerName.setAdapter(new RankingAdapter(getActivity(), list));
        adapter.notifyDataSetChanged();
    }


    @OnClick(R.id.rangking_click)
    public void onViewClicked() {
        refreshAdapter();
    }
}
