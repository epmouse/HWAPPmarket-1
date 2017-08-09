package com.example.apple.hwappmarket.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
public class ToastUtils {
    private static Toast toast;

    public static void showToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    public static void showLongToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

}
