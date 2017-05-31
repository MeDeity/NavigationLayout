package com.deity.navigation;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Item
 * Created by Deity on 2017/5/27.
 */

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class NavMenuItem extends FrameLayout {

    private View mRootView;
    private ImageView navImageView;//导航图片
    private TextView navText;//导航描述
    private TextView unreadNum;//未读信息
    private TextView tvMessage;//导航额外信息提示
    private TextView tvPoint;//红点

    private String mText;
    /**图片资源*/
    private int mIconResNormal = 0;
    /**选中状态下的资源*/
    private int mIconResSelected = 0;
    /**文本未选中的状态颜色*/
    private int mTextColorNormal = 0;
    /**文本选中的状态颜色*/
    private int mTextColorSelected = 0;
    /**当前是否处于选中状态*/
    private boolean isSelected = false;//当前item是否被选中

    public NavMenuItem(@NonNull Context context) {
        this(context,null);
    }

    public NavMenuItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NavMenuItem(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context){
        mRootView = LayoutInflater.from(context).inflate(R.layout.item_nav_menu,this,false);
        navImageView = (ImageView) mRootView.findViewById(R.id.nav_image);
        navText = (TextView) mRootView.findViewById(R.id.nav_description);
        unreadNum = (TextView) mRootView.findViewById(R.id.tv_unreadNum);
        tvMessage = (TextView) mRootView.findViewById(R.id.tv_message);
        tvPoint = (TextView) mRootView.findViewById(R.id.tv_point);
        addView(mRootView);
    }

    /**
     * 当Item处于选中状态的情况下
     * 修改Item的的图片信息及文字颜色
     */
    public void refreshState(boolean isSelected){
        if (isSelected){
            navImageView.setImageResource(mIconResSelected);
            navText.setTextColor(mTextColorSelected);
        }else {
            navImageView.setImageResource(mIconResNormal);
            navText.setTextColor(mTextColorNormal);
        }
    }

    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
        refreshState(isSelected);
    }

    public int getmIconResNormal() {
        return mIconResNormal;
    }

    public void setmIconResNormal(int mIconResNormal) {
        this.mIconResNormal = mIconResNormal;
    }

    public int getmIconResSelected() {
        return mIconResSelected;
    }

    public void setmIconResSelected(int mIconResSelected) {
        this.mIconResSelected = mIconResSelected;
    }

    public int getmTextColorNormal() {
        return mTextColorNormal;
    }

    public void setmTextColorNormal(int mTextColorNormal) {
        this.mTextColorNormal = mTextColorNormal;
    }

    public int getmTextColorSelected() {
        return mTextColorSelected;
    }

    public void setmTextColorSelected(int mTextColorSelected) {
        this.mTextColorSelected = mTextColorSelected;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }
}
