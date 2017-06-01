package com.deity.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * 底部导航栏门面
 * Created by Deity on 2017/5/30.
 */

public class NavMenuLayout extends LinearLayout{
    private int menuCount;
    private Context context;
    private ArrayList<NavMenuItem> menuItems;
    private int currentSelectPosition;
    private OnItemReSelectionListener onItemReSelectionListener;
    private OnItemSelectionListener onItemSelectionListener;
    private NavMenuLayout instance;

    /**普通状态下的资源*/
    private int[] listIconResNormal;
    /**选中状态下的资源*/
    private int[] listIconResSelected;
    /**文本资源*/
    private String[] listTextRes;

    public NavMenuLayout(Context context) {
        this(context,null);
    }

    public NavMenuLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NavMenuLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr){
        instance = this;
        this.context = context;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.NavMenuLayout,defStyleAttr,0);
        if (null!=typedArray){
            menuCount = typedArray.getInt(R.styleable.NavMenuLayout_menuCount,0);
            if (menuCount>0){
                initMenu();
            }else {
                throw new IllegalArgumentException("Menu count must larger than zero");
            }
            typedArray.recycle();
        }
    }

    public NavMenuLayout initMenu(){
        menuItems = new ArrayList<>();
        for (int i=0;i<menuCount;i++){
            final NavMenuItem navMenuItem = new NavMenuItem(context);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
            params.gravity = Gravity.CENTER;
            navMenuItem.setLayoutParams(params);
            navMenuItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getSelectionItem(navMenuItem);
                    if (position == currentSelectPosition){
                        onItemReClick(position);
                    }else {
                        onItemClick(position);
                    }
                }
            });
            menuItems.add(navMenuItem);
            addView(navMenuItem);
        }
        return instance;
    }

    public int getSelectionItem(NavMenuItem navMenuItem){
        for (int i=0;i<menuCount;i++){
            if (navMenuItem == menuItems.get(i)){
                return i;
            }
        }
        return -1;
    }

    public interface OnItemReSelectionListener{
        void onItemReSelect(int postion);
    }

    public interface OnItemSelectionListener{
        void onItemSelect(int postion);
    }

    public void onItemClick(int position){
        for (int i=0;i<menuCount;i++){
            if (position==i){
                setSelected(position);
            }
        }
        if (null!=onItemSelectionListener){
            onItemSelectionListener.onItemSelect(position);
        }
    }

    public void onItemReClick(int position){
        if (null!=onItemReSelectionListener){
            onItemReSelectionListener.onItemReSelect(position);
        }
    }

    private void refreshStatus(){
        for (int i=0;i<menuCount;i++){
            NavMenuItem menuItem = menuItems.get(i);
            if (currentSelectPosition == i){
                menuItem.setSelected(true);
            }else{
                menuItem.setSelected(false);
            }
        }
    }

    /**
     * 设置未选中的图片
     * @param listIconRes 未选中的图片
     * @return 返回实例
     */
    public NavMenuLayout setIconResNormal(int[] listIconRes){
        this.listIconResNormal = listIconRes;
        if (listIconRes.length!=menuCount){
            throw new IllegalArgumentException("listIconRes is not equal menuCount");
        }else {
            for (int i=0;i<menuCount;i++){
                menuItems.get(i).setmIconResNormal(listIconRes[i]);
            }
        }
        return instance;
    }


    public NavMenuLayout setIconResSelected(int[] listIconRes){
        this.listIconResSelected = listIconRes;
        if (listIconRes.length!=menuCount){
            throw new IllegalArgumentException("listIconRes is not equal menuCount");
        }else {
            for (int i=0;i<menuCount;i++){
                menuItems.get(i).setmIconResSelected(listIconRes[i]);
            }
        }
        return instance;
    }


    public NavMenuLayout setTextRes(String[] listTextRes){
        this.listTextRes = listTextRes;
        if (listTextRes.length!=menuCount){
            throw new IllegalArgumentException("listIconRes is not equal menuCount");
        }else {
            for (int i=0;i<menuCount;i++){
                menuItems.get(i).setmText(listTextRes[i]);
            }
        }
        return instance;
    }

    public NavMenuLayout setTextColorStatus(int mTextColorNormal,int mTextColorSelected){
        for (int i=0;i<menuCount;i++){
            menuItems.get(i).setmTextColorNormal(mTextColorNormal);
            menuItems.get(i).setmTextColorSelected(mTextColorSelected);
        }
        return instance;
    }

    public NavMenuLayout setRedPoint(int position){
        if (position>=0&&position<menuItems.size()){
            menuItems.get(position).setRedPoint();
        }
        return instance;
    }

    public NavMenuLayout setUnreadNum(int position,String number){
        if (position>=0&&position<menuItems.size()){
            menuItems.get(position).setUnreadNum(number);
        }
        return instance;
    }

    public NavMenuLayout setMessage(int position,String message){
        if (position>=0&&position<menuItems.size()){
            menuItems.get(position).setTvMessage(message);
        }
        return instance;
    }

    public OnItemReSelectionListener getOnItemReSelectionListener() {
        return onItemReSelectionListener;
    }

    public NavMenuLayout setOnItemReSelectionListener(OnItemReSelectionListener onItemReSelectionListener) {
        this.onItemReSelectionListener = onItemReSelectionListener;
        return instance;
    }

    public OnItemSelectionListener getOnItemSelectionListener() {
        return onItemSelectionListener;
    }

    public NavMenuLayout setOnItemSelectionListener(OnItemSelectionListener onItemSelectionListener) {
        this.onItemSelectionListener = onItemSelectionListener;
        return instance;
    }

    /***
     * 设置被选中的item
     * @param position 被选中的Item的信息
     * @return 返回NavMenuLayout实例
     */
    public NavMenuLayout setSelected(int position){
        if (position>=0&&position<menuCount){
            currentSelectPosition = position;
            refreshStatus();
        }else {
            throw new IllegalArgumentException("position is illegal");
        }
        return instance;
    }

    public NavMenuLayout setTextSize(int textSize){
        for (int i=0;i<menuItems.size();i++){
            menuItems.get(i).setmTextSize(textSize);
        }
        return instance;
    }



    public void clearPoint(int position){
        if (position>=0&&position<menuCount){
            menuItems.get(position).clearRedPoint();
        }
    }

    public void clearUnreadNumber(int position){
        if (position>=0&&position<menuCount){
            menuItems.get(position).clearUnreadNumber();
        }
    }

    public void clearMessage(int position){
        if (position>=0&&position<menuCount){
            menuItems.get(position).clearMessage();
        }
    }

}
