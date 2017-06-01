package com.deity.navigation.utils;

import com.deity.navigation.data.AppApplication;

/**
 * 国际化
 * Created by fengwenhua on 2017/6/1.
 */

public class I18NData {

    public static int obtain18NColor(int resId){
        return AppApplication.instance.getResources().getColor(resId);
    }
}
