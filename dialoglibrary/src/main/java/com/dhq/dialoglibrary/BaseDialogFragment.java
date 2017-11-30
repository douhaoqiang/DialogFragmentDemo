package com.dhq.dialoglibrary;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * 基础dialogfragment
 */
public abstract class BaseDialogFragment extends DialogFragment {
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String BOTTOM = "show_bottom";
    private static final String CANCEL = "out_cancel";
    private static final String LAYOUT = "layout_id";

    private int width;//宽度
    private int height;//高度
    private float dimAmount = 0.5f;//灰度深浅
    private boolean showBottom;//是否底部显示
    private boolean outCancel = true;//是否点击外部取消
    @StyleRes
    private int animStyle;
    @LayoutRes
    protected int layoutId;

    private View mContentView;

    public abstract int intLayoutId();

    public abstract void convertView(ViewHolder holder, BaseDialogFragment dialog);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BaseDialogStyle);
        layoutId = intLayoutId();

        //恢复保存的数据
        if (savedInstanceState != null) {
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            showBottom = savedInstanceState.getBoolean(BOTTOM);
            outCancel = savedInstanceState.getBoolean(CANCEL);
            layoutId = savedInstanceState.getInt(LAYOUT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.dialog_base_lay, container, false);
        mContentView = inflater.inflate(layoutId, view, false);
        view.addView(mContentView);
        convertView(ViewHolder.create(view), this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(WIDTH, width);
        outState.putInt(HEIGHT, height);
        outState.putFloat(DIM, dimAmount);
        outState.putBoolean(BOTTOM, showBottom);
        outState.putBoolean(CANCEL, outCancel);
        outState.putInt(LAYOUT, layoutId);
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.dimAmount = 0;//window背景为透明颜色，颜色透明度利用本布局颜色
            window.setAttributes(lp);
        }

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mContentView.getLayoutParams();
        //是否在底部显示
        if (showBottom) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//            //从下向上飘入动画（）
//            TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                    1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//            mHiddenAction.setDuration(500);
//            mContentView.startAnimation(mHiddenAction);
        } else {
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        }

        //设置dialog背景的透明度(from=0,to=255)
        int alpha = (int) (dimAmount * 255);
        getView().getBackground().setAlpha(alpha);

        mContentView.setLayoutParams(layoutParams);

        setCancelable(outCancel);
        if (outCancel) {
            getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            mContentView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

    }


    public BaseDialogFragment setDimAmount(float dimAmount) {
        if (dimAmount >= 0 && dimAmount <= 1) {
            this.dimAmount = dimAmount;
        }
        return this;
    }

    public BaseDialogFragment setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;
        return this;
    }

    public BaseDialogFragment setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return this;
    }

    public BaseDialogFragment setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    public BaseDialogFragment show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }
}
