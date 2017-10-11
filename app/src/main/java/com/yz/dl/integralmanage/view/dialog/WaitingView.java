package com.yz.dl.integralmanage.view.dialog;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.yz.dl.integralmanage.R;

/**
 *
 */
public class WaitingView extends RelativeLayout {
    public WaitingView(Context paramContext) {
        super(paramContext);
        LayoutInflater.from(paramContext).inflate(R.layout.dialog_waiting_view, this);
        ((ImageView) findViewById(R.id.waiting_imageview)).startAnimation(AnimationUtils
                .loadAnimation(paramContext, R.anim.anmi_waiting));
    }

    public WaitingView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        LayoutInflater.from(paramContext).inflate(R.layout.dialog_waiting_view, this);
        ((ImageView) findViewById(R.id.waiting_imageview)).startAnimation(AnimationUtils
                .loadAnimation(paramContext, R.anim.anmi_waiting));
    }

    public WaitingView(Context paramContext, AttributeSet paramAttributeSet,
                       int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        LayoutInflater.from(paramContext).inflate(R.layout.dialog_waiting_view, this);
        ((ImageView) findViewById(R.id.waiting_imageview)).startAnimation(AnimationUtils
                .loadAnimation(paramContext, R.anim.anmi_waiting));
    }
}
