package com.deity.navigationlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.deity.navigation.NavMenuLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavMenuLayout.OnItemReSelectionListener,NavMenuLayout.OnItemSelectionListener {
    private NavMenuLayout navMenuLayout;
    private ViewPager viewPager;
    private List<Fragment> listFragment;

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

        listFragment = new ArrayList<>();
        listFragment.add(MyFragment.instance(textRes[0]));
        listFragment.add(MyFragment.instance(textRes[1]));
        listFragment.add(MyFragment.instance(textRes[2]));
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navMenuLayout.setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onItemReSelect(int postion) {
        Toast.makeText(MainActivity.this,"被重复点击"+postion,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelect(int postion) {
//        Toast.makeText(MainActivity.this,"被点击"+postion,Toast.LENGTH_SHORT).show();
        viewPager.setCurrentItem(postion);
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return listFragment.size();
        }
    }
}
