package com.example.apple.hwappmarket.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.example.apple.hwappmarket.BaseActivity;
import com.example.apple.hwappmarket.R;
import com.example.apple.hwappmarket.ui.adapter.MainViewpagerAdapter;
import com.example.apple.hwappmarket.ui.fragment.FragmentFactory;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private String[] tabs={"推荐","分类","排行","管理","我的"};
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void initView() {
          initTab();

    }

    private void initViewPager() {
        viewpager.setAdapter(new MainViewpagerAdapter(getSupportFragmentManager(),FragmentFactory.createFragments()));
    }

    private void initTab() {
        initViewPager();
        tab.setupWithViewPager(viewpager);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        for(int i=0;i<tabs.length;i++){
            tab.getTabAt(i).setText(tabs[i]);
        }
    }

    @Override
    public int inflateView() {
        return R.layout.activity_main;
    }
    @Override
    protected int setStatusColor() {
        return super.setStatusColor();
    }
}
