package com.yz.dl.integralmanage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.ManagerRankingAdapter;
import com.yz.dl.integralmanage.base.BaseFragment;
import com.yz.dl.integralmanage.bean.RankingBean;
import com.yz.dl.integralmanage.ui.IntegralSearchIndividual;
import com.yz.dl.integralmanage.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 站经理排名Fragment
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class ManagerRankingFragment extends BaseFragment {
    @Bind(R.id.manager_ranking_list)
    ListViewForScrollView managerName;
    @Bind(R.id.manager_rangking_click)
    TextView managerRankingClick;
    ArrayList<RankingBean> list;
    ManagerRankingAdapter adapter;
    private boolean isSort = false;

    @Override
    protected void TODO(View view, Bundle savedInstanceState) {
        list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            RankingBean rankingBean = new RankingBean();
            rankingBean.setArea("成都");
            rankingBean.setNum(i);
            rankingBean.setGas("南苑");
            rankingBean.setName("张强");
            rankingBean.setTotal((90 - i) + " ");
            list.add(rankingBean);
        }
        adapter = new ManagerRankingAdapter(getActivity(), list);
        managerName.setAdapter(adapter);
        managerName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), IntegralSearchIndividual.class));
            }
        });
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
        Comparator<RankingBean> itemComparator = null;
        if (!isSort) {
            itemComparator = new Comparator<RankingBean>() {
                public int compare(RankingBean info1, RankingBean info2) {
                    return info1.getTotal().compareTo(info2.getTotal());
                }
            };
            isSort = true;
        } else {
            itemComparator = new Comparator<RankingBean>() {
                public int compare(RankingBean info1, RankingBean info2) {
                    return info2.getTotal().compareTo(info1.getTotal());
                }
            };
            isSort = false;
        }
        Collections.sort(list, itemComparator);
    }

    private void refreshAdapter() {
        sortInfo();
//        managerName.setAdapter(new RankingAdapter(getActivity(), list));
        adapter.notifyDataSetChanged();
    }


    @OnClick(R.id.manager_rangking_click)
    public void onViewClicked() {
        refreshAdapter();
    }
}
