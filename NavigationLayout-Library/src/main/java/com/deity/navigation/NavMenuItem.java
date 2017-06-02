package com.deity.navigation;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.deity.navigation.utils.I18NData;

/**
 * Item
 * Created by Deity on 2017/5/27.
 */

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class NavMenuItem extends FrameLayout {
    private final static String TAG = NavMenuItem.class.getSimpleName();

    private View mRootView;
    private ImageView navImageView;//导航图片
    private TextView navText;//导航描述
    private TextView unreadNum;//未读信息
    private TextView tvMessage;//导航额外信息提示
    private TextView tvPoint;//红点

    /**导航Item的文字描述*/
    private String mText;
    /**图片资源*/
    private int mIconResNormal = 0;
    /**选中状态下的资源*/
    private int mIconResSelected = 0;
    /**文本未选中的状态颜色,默认黑色*/
    private int mTextColorNormal;
    /**文本选中的状态颜色,默认红色*/
    private int mTextColorSelected;
    /**文本大小,默认大小*/
    private int mTextSize = 12;
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
        mRootView = LayoutInflater.from(context).inflate(R.layout.item_nav_menu,this,true);
        navImageView = (ImageView) mRootView.findViewById(R.id.nav_image);
        navText = (TextView) mRootView.findViewById(R.id.nav_description);
        unreadNum = (TextView) mRootView.findViewById(R.id.tv_unreadNum);
        tvMessage = (TextView) mRootView.findViewById(R.id.tv_message);
        tvPoint = (TextView) mRootView.findViewById(R.id.tv_point);
    }

    /**
     * 当Item处于选中状态的情况下
     * 修改Item的的图片信息及文字颜色
     */
    private void refreshState(boolean isSelected){
        if (isSelected){
            navImageView.setBackgroundResource(mIconResSelected);
            navText.setTextColor(I18NData.obtain18NColor(mTextColorSelected));
        }else {
            navImageView.setBackgroundResource(mIconResNormal);
            navText.setTextColor(I18NData.obtain18NColor(mTextColorNormal));
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

    /**
     * 设置被选中的图片资源
     * @param mIconResSelected 被选中的图片资源
     */
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

    /**
     * 设置NavMenuItem的文字描述
     * @param mText 文本信息
     */
    public void setmText(String mText) {
        this.mText = mText;
        if (!TextUtils.isEmpty(mText)){
            navText.setText(mText); //设置NavMenuItem的文本内容
        }
    }

    private void hideView(View view){
        if(View.VISIBLE==view.getVisibility()){
            view.setVisibility(View.GONE);
        }
    }

    public void hideAllExtraView(){
        hideView(tvMessage);
        hideView(tvPoint);
        hideView(unreadNum);
    }

    /**显示小红点*/
    public void setRedPoint(){
        hideAllExtraView();
        if (View.VISIBLE!=tvPoint.getVisibility()){
            tvPoint.setVisibility(VISIBLE);
        }
    }

    public void setUnreadNum(String num){
        hideAllExtraView();
        if (View.VISIBLE!=unreadNum.getVisibility()){
            unreadNum.setVisibility(VISIBLE);
        }
        unreadNum.setText(num);
    }

    public void setTvMessage(String message){
        hideAllExtraView();
        if (View.VISIBLE!=tvMessage.getVisibility()){
            tvMessage.setVisibility(VISIBLE);
        }
        tvMessage.setText(String.valueOf(message));
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        if (mTextSize>0){////设置文本大小
            navText.setTextSize(mTextSize);
        }
    }

    public void clearRedPoint(){
        hideView(tvPoint);
    }

    public void clearUnreadNumber(){
        hideView(unreadNum);
    }

    public void clearMessage(){
        hideView(tvMessage);
    }
}
