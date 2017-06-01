package com.deity.navigationlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 测试用Fragment
 * Created by fengwenhua on 2017/6/1.
 */

public class MyFragment extends Fragment {
    private TextView textView;
    private String name;

    public static MyFragment instance(String name){
        MyFragment fragment = new MyFragment();
        fragment.name = name;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test,container,false);
        textView = (TextView) view.findViewById(R.id.fragment_name);
        textView.setText(name);
        return view;
    }
}
