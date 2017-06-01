package com.deity.navigationlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.deity.navigation.NavMenuLayout;

public class MainActivity extends AppCompatActivity implements NavMenuLayout.OnItemReSelectionListener,NavMenuLayout.OnItemSelectionListener {
    private NavMenuLayout navMenuLayout;

    private int[] iconRes = {R.mipmap.ic_home_normal, R.mipmap.ic_managemoney_normal, R.mipmap.ic_me_normal};
    private int[] iconResSelected = {R.mipmap.ic_home_selected, R.mipmap.ic_managemoney_select, R.mipmap.ic_me_select};
    private String[] textRes = {"首页", "理财", "个人中心"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navMenuLayout = (NavMenuLayout) this.findViewById(R.id.nav_layout);

        navMenuLayout.setIconResNormal(iconRes)
                .setIconResSelected(iconResSelected)
                .setTextRes(textRes)
                .setTextColorStatus(android.R.color.black,R.color.colorAccent)
                .setOnItemReSelectionListener(this)
                .setOnItemSelectionListener(this)
                .setSelected(0);

    }

    @Override
    public void onItemReSelect(int postion) {
        Toast.makeText(MainActivity.this,"被重复点击"+postion,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelect(int postion) {
        Toast.makeText(MainActivity.this,"被点击"+postion,Toast.LENGTH_SHORT).show();
    }
}
