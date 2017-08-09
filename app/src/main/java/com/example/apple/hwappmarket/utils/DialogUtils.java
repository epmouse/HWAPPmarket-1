package com.example.apple.hwappmarket.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import java.util.Calendar;

/**
 * Created by star on 2016/6/29 0029.
 * email：18838265776@163.com
 */
public class DialogUtils {
    private Activity activity;
    private String title;
    private String msg;
    private int iconId;
    private int checkedItem;
    private String[] items;
    private View view;
    private int layoutId;
    private DialogInterface.OnClickListener listener;
    private DialogInterface.OnClickListener okListener;
    private DialogInterface.OnClickListener cancelListener;
    private DialogInterface.OnCancelListener onCancelListener;
    private boolean canCancel;

    private DialogUtils(DiaBuider buider) {
        this.activity = buider.activity;
        this.title = buider.title == null ? "" : buider.title;
        this.msg = buider.msg == null ? "" : buider.msg;
        this.iconId = buider.iconId == 0 ? -1 : buider.iconId;
        this.checkedItem = buider.checkedItem;
        this.items = buider.items;
        this.view = buider.view;
        this.layoutId = buider.layoutId == 0 ? -1 : buider.layoutId;
        this.listener = buider.listener;
        this.okListener = buider.okListener;
        this.cancelListener = buider.cancelListener;
        this.cancelListener = buider.cancelListener;
        createDialog();


    }

    private DialogUtils(ProgressDiaBuilder buider) {
        this.activity = buider.activity;
        this.title = buider.title == null ? "" : buider.title;
        this.msg = buider.msg == null ? "" : buider.msg;
        this.onCancelListener = buider.onCancelListener;
        createProgressDia();
    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(msg)) {
            builder.setMessage(msg);
        }
        if (view != null) {
            builder.setView(view);
        }
        if (layoutId != -1) {
            builder.setView(layoutId);
        }
        if (iconId != -1) {
            builder.setIcon(iconId);
        }
        if (listener != null)
            builder.setSingleChoiceItems(items, checkedItem, listener);
        if (okListener != null)
            builder.setPositiveButton("确定", okListener);
        if (cancelListener != null)
            builder.setNegativeButton("取消", okListener);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(canCancel);
        dialog.show();
    }

    public static class DiaBuider {
        private Activity activity;
        private String title;
        private String msg;
        private int iconId;
        private int checkedItem;
        private String[] items;
        private View view;
        private int layoutId;
        private DialogInterface.OnClickListener listener;
        private DialogInterface.OnClickListener okListener;
        private DialogInterface.OnClickListener cancelListener;
        private boolean canCancel;

        public DiaBuider(Activity activity) {
            this.activity = activity;
        }

        public DiaBuider setTitle(String title) {
            this.title = title;
            return this;
        }

        public DiaBuider setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public DiaBuider setIconId(int iconId) {
            this.iconId = iconId;
            return this;
        }

        /**
         * 默认选中项
         *
         * @param checkedItem
         * @return
         */
        public DiaBuider setCheckedItem(int checkedItem) {
            this.checkedItem = checkedItem;
            return this;
        }

        /**
         * 单选对话框的items 非单选对话框可以不调用
         *
         * @param items
         * @return
         */
        public DiaBuider setItems(String[] items) {
            this.items = items;
            return this;
        }

        public DiaBuider setView(View view) {
            this.view = view;
            return this;
        }

        public DiaBuider setLayoutId(int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public DiaBuider setSingleSelectListener(DialogInterface.OnClickListener listener) {
            this.listener = listener;
            return this;
        }

        public DiaBuider setOkListener(DialogInterface.OnClickListener okListener) {
            this.okListener = okListener;
            return this;
        }

        public DiaBuider setCancelListener(DialogInterface.OnClickListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        public DiaBuider setCanCancel(boolean canCancel) {
            this.canCancel = canCancel;
            return this;
        }

        public DialogUtils build() {
            return new DialogUtils(this);
        }
    }


    /**
     * 日期选择对话框
     *
     * @param activity
     * @param dateSetListener
     * @return
     */
    public static DatePickerDialog createDateSelectorDialog(Activity activity, DatePickerDialog.OnDateSetListener dateSetListener) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int monthOfYear = calendar.get(calendar.MONTH);
        int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(activity, dateSetListener, year, monthOfYear, dayOfMonth);
        dialog.show();
        return dialog;
    }

    public static class ProgressDiaBuilder {
        private Activity activity;
        private String title;
        private String msg;
        private boolean canCancel;
        private DialogInterface.OnCancelListener onCancelListener;

        public ProgressDiaBuilder(Activity activity) {
            this.activity = activity;
        }

        public ProgressDiaBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ProgressDiaBuilder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public ProgressDiaBuilder setCanCancel(boolean canCancel) {
            this.canCancel = canCancel;
            return this;
        }

        public ProgressDiaBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.onCancelListener = onCancelListener;
            return this;
        }

        public DialogUtils build() {
            return new DialogUtils(this);
        }
    }
    private void createProgressDia() {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setCancelable(canCancel);
        dialog.setOnCancelListener(onCancelListener);
        dialog.show();
    }
}
