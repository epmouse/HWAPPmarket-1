package com.example.apple.hwappmarket.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by stars on 17/8/4.
 */

public class MainViewpagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public MainViewpagerAdapter(FragmentManager fm, List<Fragment> fragments){
        super(fm);
        this.fragments=fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
}
