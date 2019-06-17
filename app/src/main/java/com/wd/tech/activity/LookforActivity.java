package com.wd.tech.activity;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.fragment.AddFriendFragment;
import com.wd.tech.fragment.AddGroupFragment;

import java.util.ArrayList;
import java.util.List;

public class LookforActivity extends BaseActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> aflist=new ArrayList<>();
    List<String> titlelist=new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_lookfor;
    }

    @Override
    protected void findView() {
        tabLayout=findViewById(R.id.add_tab_id);
        viewPager=findViewById(R.id.add_pager_id);
    }

    @Override
    protected void initData() {
        aflist.add(new AddFriendFragment());
        aflist.add(new AddGroupFragment());

        titlelist.add("找人");
        titlelist.add("找群");

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return aflist.get(i);
            }

            @Override
            public int getCount() {
                return aflist.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titlelist.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
