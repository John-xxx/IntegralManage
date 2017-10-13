package com.yz.dl.integralmanage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.OperatorRankingAdapter;
import com.yz.dl.integralmanage.base.BaseFragment;
import com.yz.dl.integralmanage.bean.RankingBean;
import com.yz.dl.integralmanage.ui.IntegralSearchIndividual;
import com.yz.dl.integralmanage.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 加油员排名Fragment
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class OperatorRankingFragment extends BaseFragment {
    @Bind(R.id.operator_ranking_click)
    TextView operatorRankingClick;
    @Bind(R.id.operator_ranking_list)
    ListViewForScrollView operatorRankingList;

    private OperatorRankingAdapter adapter;
    ArrayList<RankingBean> list;
    private boolean isSort = false;

    @Override
    protected void TODO(View view, Bundle savedInstanceState) {
        list = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            RankingBean person = new RankingBean();
            person.setArea("安县");
            person.setTotal("" + (88 - i));
            person.setName("张开");
            person.setGas("成家坝");
            person.setNum(i + 2);
            list.add(person);
        }


        adapter = new OperatorRankingAdapter(getActivity(), list);
        operatorRankingList.setAdapter(adapter);
        operatorRankingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), IntegralSearchIndividual.class));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflate(inflater, container, R.layout.fragment_operator_ranking, NULL_LOADVIEW, 0);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.operator_ranking_click)
    public void onViewClicked() {

        refreshAdapter();
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
}
