package com.yz.dl.integralmanage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.adapter.ManagementRankingAdapter;
import com.yz.dl.integralmanage.base.BaseFragment;
import com.yz.dl.integralmanage.bean.RankingBean;
import com.yz.dl.integralmanage.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yz.dl.integralmanage.R.id.managment_ranking_list;

/**
 * Created by I'M CHAMAN on 2017/9/26.
 */

public class ManagmentRankingFragment extends BaseFragment {
    @Bind(R.id.managment_ranking_click)
    TextView managmentRankingClick;
    @Bind(managment_ranking_list)
    ListViewForScrollView managmentRankingList;
    ManagementRankingAdapter adapter;

    ArrayList<RankingBean> list;
    private boolean isSort = false;

    @Override
    protected void TODO(View view, Bundle savedInstanceState) {
        list = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            RankingBean person = new RankingBean();
            person.setArea("广安" + (10 - i));
            person.setTotal("" + (98 - i));
            person.setName("刘成" + (10 - i));
            person.setGas("张家坝" + (10 - i));
            person.setNum(i + 3);
            list.add(person);
        }


        adapter = new ManagementRankingAdapter(getActivity(), list);
        managmentRankingList.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflate(inflater, container, R.layout.fragment_managementr_ranking, NULL_LOADVIEW, 0);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.managment_ranking_click)
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
