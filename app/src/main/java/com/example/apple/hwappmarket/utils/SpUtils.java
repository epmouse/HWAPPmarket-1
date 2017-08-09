package com.example.apple.hwappmarket.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;
/**
 * Created by star on 2016/6/29 0029.
 * email：18838265776@163.com
 */
public class SpUtils {
    private static final String SP_NAME = "hwappstore_sp";
    private Context context;
    SharedPreferences sp;

    private SpUtils(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    private static volatile SpUtils instance;

    public static SpUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SpUtils.class) {
                if (instance == null) {
                    instance = new SpUtils(context);
                }
            }
        }
        return instance;
    }
    public void saveData(String key, String value) {
        sp.edit().putString(key, value).commit();
    }
    public void saveData(String key, Set<String> value) {
        sp.edit().putStringSet(key, value).commit();
    }
    public void saveData(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public boolean getBoolData(String key) {
        return sp.getBoolean(key, false);
    }
    public String getStringData(String key) {
        return sp.getString(key, null);
    }
    public Set<String> getSetData(String key) {
        return sp.getStringSet(key, null);
    }

}
