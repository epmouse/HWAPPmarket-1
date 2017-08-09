package com.example.apple.hwappmarket.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by stars on 2016/3/8 0008.
 */
public class ScreenUtil {

    public static int getScreenHight(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }


    /**
     * 获取顶部状态栏高度
     *
     * @param act
     * @return
     */
    public static int getStatusBarHeight(Activity act) {
        Resources resources = act.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }

    /**
     * 获取顶部状态栏高度  通过 R类的反射
     *
     * @param act
     * @return
     */
    public static int getStatusBarHeightByReflect(Activity act) {
        try {
            //reflectによって、classを取る　
            Class<?> aClass = Class.forName("com.android.internal.R$dimen");

            Object o = aClass.newInstance();
            Field status_bar_height = aClass.getField("status_bar_height");
            int height = Integer.parseInt(aClass.getField("status_bar_height")
                    .get(o).toString());
            return act.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取底部导航栏高度
     *
     * @param act
     * @return
     */
    public static int getNavigationBarHeight(Activity act) {
        Resources resources = act.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }

    /**
     * [沉浸状态栏]
     */
    public static void steepStatusBar(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            act.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            act.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
