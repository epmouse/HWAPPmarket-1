package com.example.apple.hwappmarket.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stars on 17/8/4.
 */

public class FragmentFactory {
    public static final int FM_HOME = 0x001;
    public static final int FM_CATEGORY = 0x002;
    public static final int FM_TOP = 0x003;
    public static final int FM_MANAGER = 0x004;
    public static final int FM_ME = 0x005;

    static Map<Integer, Fragment> fragmentCacheMap = new HashMap<>();


    public static List<Fragment> createFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(createFragment(FM_HOME));
        fragments.add(createFragment(FM_CATEGORY));
        fragments.add(createFragment(FM_TOP));
        fragments.add(createFragment(FM_MANAGER));
        fragments.add(createFragment(FM_ME));
        return fragments;

    }

    public static Fragment createFragment(int fragmentKey) {
        Fragment fragment = fragmentCacheMap.get(fragmentKey);
        switch (fragmentKey) {
            case FM_HOME:
                if (fragment == null)
                    fragment = new HomeFragment();
                break;
            case FM_CATEGORY:
                if (fragment == null)
                    fragment = new CategoryFragment();
                break;
            case FM_TOP:
                if (fragment == null)
                    fragment = new TopFragment();
                break;
            case FM_MANAGER:
                if (fragment == null)
                    fragment = new ManagerFragment();
                break;
            case FM_ME:
                if (fragment == null)
                    fragment = new MeFragment();
                break;
        }
        return fragment;
    }
}
