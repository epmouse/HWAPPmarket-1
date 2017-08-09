package com.example.apple.hwappmarket.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import com.example.apple.hwappmarket.BaseActivity;
import com.example.apple.hwappmarket.R;
import com.example.apple.hwappmarket.utils.SpUtils;

public class SplashActivity extends BaseActivity {
    private String[] SDCARD_READANDWRITERPERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static final int PERMISSION_REQUEST_CODE = 0x001;


    @Override
    protected void initView() {
        verifyStoragePermission(this);
        if (SpUtils.getInstance(mContext).getBoolData("isFirst")) {
            //歓迎のページに入る
        } else {
            //ホームページに入る
            try {
                Thread.sleep(3000);
                toMain();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public int inflateView() {
        return R.layout.activity_splash;
    }

    private void verifyStoragePermission(Activity act) {
        int permission = ContextCompat.checkSelfPermission(act, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(act, SDCARD_READANDWRITERPERMISSION, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
            //申し込み成功
            showShortToast("申请成功");
        } else {
            showShortToast("申请失败");
        }

    }

    public void toMain() {
        SpUtils.getInstance(mContext).saveData("isFirst", false);
        openActivity(MainActivity.class);
    }

}
