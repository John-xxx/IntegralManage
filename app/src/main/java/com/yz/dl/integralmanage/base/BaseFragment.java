package com.yz.dl.integralmanage.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.event.LoadFailListenter;
import com.yz.dl.integralmanage.utils.Utils;
import com.yz.dl.integralmanage.view.SpinnerLoader;

import butterknife.ButterKnife;

/**
 * Created by I'M CHAMAN on 2017/9/26.
 */

public abstract class BaseFragment extends Fragment {


    protected ViewGroup view;
    protected View loadView;
    private ViewGroup loadViewParent;
    private LinearLayout loading;
    private LinearLayout loadfail;
    private int offset;
    private LoadFailListenter loadFailListenter;
    protected final int NULL_LOADVIEW = -1;
    protected final int NULL_OFFSET = 0;
    protected boolean isFirstVisiableToUser = true;

    protected View inflate(LayoutInflater inflater, ViewGroup container, int layoutID, int loadViewID, int offset) {
        view = (ViewGroup) inflater.inflate(layoutID, container, false);
        if (loadViewID != NULL_LOADVIEW) {
            loadView = view.findViewById(loadViewID);
            loadViewParent = (ViewGroup) loadView.getParent();
            loading = (LinearLayout) inflater.inflate(R.layout.base_fragment_loader, null);
            loadfail = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.base_fragment_loadfail, null);
        }
        this.offset = offset;
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TODO(view, savedInstanceState);
    }

    /**
     * loading
     */
    protected void loadWait() {
        if (loadView == null || loading.getParent() != null) return;
        loading.setLayoutParams(loadView.getLayoutParams());
        SpinnerLoader progress = (SpinnerLoader) loading.findViewById(R.id.base_fragment_loader_progress);
        if (offset != NULL_OFFSET) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) progress.getLayoutParams();
            params.bottomMargin = Utils.dp2px(offset, getActivity());
            progress.setLayoutParams(params);
        }
        loadViewParent.removeView(loadView);
        loadViewParent.removeView(loadfail);
        if (loading.getParent() == null)
            loadViewParent.addView(loading);
    }

    /**
     * load fail
     */
    protected void loadFail() {
        if (loadView == null || loadfail.getParent() != null) return;
        loadViewParent.removeView(loadView);
        loadViewParent.removeView(loading);
        if (loadfail.getParent() == null)
            loadViewParent.addView(loadfail);
        loadfail.setLayoutParams(loadView.getLayoutParams());
        LinearLayout error = (LinearLayout) loadfail.findViewById(R.id.base_fragment_loadfial_lin);
        if (offset != NULL_OFFSET) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) error.getLayoutParams();
            params.bottomMargin = Utils.dp2px(offset, getActivity());
            error.setLayoutParams(params);
        }
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadFailListenter != null) {
                    loadFailListenter.loadfialTODO(v);
                }
            }
        });
    }

    /**
     * load success
     */
    protected void loadSuccess() {
        if (loadView == null || loadView.getParent() != null) return;
        loadViewParent.removeView(loadfail);
        loadViewParent.removeView(loading);
        loadViewParent.addView(loadView);
    }

    protected View getLoadfailView() {
        return loadfail;
    }

    /**
     * 设置加载背景
     *
     * @param resID
     */
    protected void setLoadingBackgroundColor(int resID) {
        if (loading != null)
            loading.setBackgroundColor(resID);

    }

    /**
     * 设置加载失败的文字的大小
     *
     * @param size
     */
    protected void setLoadFailTextSize(int size) {
        if (loadfail != null) {
            ((TextView) loadfail.findViewById(R.id.base_fragment_loadfail_tv)).setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
    }

    /**
     * 设置加载失败的第二行文字的大小
     *
     * @param size
     */
    protected void setLoadFailTextSize2(int size) {
        if (loadfail != null) {
            ((TextView) loadfail.findViewById(R.id.base_fragment_loadfail_tv2)).setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
    }

    /**
     * 设置加载失败的文字
     *
     * @param text
     */
    protected void setLoadFailText(String text) {
        if (loadfail != null) {
            ((TextView) loadfail.findViewById(R.id.base_fragment_loadfail_tv)).setText(text);
        }
    }

    /**
     * 设置第二行加载失败的文字
     *
     * @param text
     */
    protected void setLoadFailText2(String text) {
        if (loadfail != null) {
            ((TextView) loadfail.findViewById(R.id.base_fragment_loadfail_tv2)).setText(text);
        }
    }

    /**
     * 设置加载失败的文字颜色
     *
     * @param color
     */
    protected void setLoadFailTextColor(int color) {
        if (loadfail != null) {
            ((TextView) loadfail.findViewById(R.id.base_fragment_loadfail_tv)).setTextColor(color);
        }
    }

    /**
     * 设置加载失败的第二行文字颜色
     *
     * @param color
     */
    protected void setLoadFailTextColor2(int color) {
        if (loadfail != null) {
            ((TextView) loadfail.findViewById(R.id.base_fragment_loadfail_tv2)).setTextColor(color);
        }
    }

    /**
     * 设置加载失败的图片
     *
     * @param resID
     */
    protected void setLoadFailImg(int resID) {
        if (loadfail != null) {
            ((ImageView) loadfail.findViewById(R.id.base_fragment_loadfail_img)).setImageResource(resID);
        }
    }

    protected abstract void TODO(View view, Bundle savedInstanceState);

    public LoadFailListenter getLoadFailListenter() {
        return loadFailListenter;
    }

    public void setLoadFailListenter(LoadFailListenter loadFailListenter) {
        this.loadFailListenter = loadFailListenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public Fragment getThis() {
        return this;
    }
}
