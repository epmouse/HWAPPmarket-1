package com.example.apple.hwappmarket;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.example.apple.hwappmarket.utils.DialogUtils;
import com.example.apple.hwappmarket.utils.ScreenUtil;
import com.example.apple.hwappmarket.utils.ToastUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    public ViewGroup status_bar;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inflateView());
        ButterKnife.bind(this);
        mContext = this.getApplicationContext();
        ScreenUtil.steepStatusBar(this);//设置透明状态栏和导航栏
        setStatusBarHeight();//设置状态栏高度
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置横屏
        initView();
    }
    /**
     * 初始化方法
     */
    protected abstract void initView();

    /**
     * 设置页面布局
     */
    public abstract int inflateView();

    /**
     * 标题栏高度加入状态栏高度
     */
    public void setStatusBarHeight() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            final int statusHeight = ScreenUtil.getStatusBarHeightByReflect(this);//用反射获取状态栏高度
            status_bar = (ViewGroup) findViewById(R.id.ll_status);
            if (status_bar != null) {

                status_bar.post(() -> {
                    int height = status_bar.getHeight();
                    ViewGroup.LayoutParams layoutParams = status_bar.getLayoutParams();
                    layoutParams.height = statusHeight + height;
                    status_bar.setBackgroundColor(setStatusColor());
                    status_bar.setLayoutParams(layoutParams);
                });
            }
        }
    }

    /**
     * 当页面中的标题颜色不是默认的颜色时重写此方法返回对应的颜色。
     * 该默认值应该跟通用标题颜色一致。
     * @return
     */
    protected  int setStatusColor(){
        return getResources().getColor(R.color.colorPrimary);
    }



    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //7.0默认是从右侧平入 即以下效果，为了适配7.0以下版本，添加
        overridePendingTransition(R.anim.anim_slide_in_tran_start,R.anim.anim_slide_out_tran_start);//如需activity的进入动画，可使用此方法设置，只需要传入两个xml动画即可
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        //7.0默认是从右侧平入 即以下效果，为了适配7.0以下版本，添加
        overridePendingTransition(R.anim.anim_slide_in_tran_start,R.anim.anim_slide_out_tran_start);//如需activity的进入动画，可使用此方法设置，只需要传入两个xml动画即可

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in_tran_finish,R.anim.anim_slide_out_tran_finish);//退出动画
    }
    protected void openActivity(Class clazz){
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
    }

    public void showShortToast(String msg) {
        ToastUtils.showToast(this.getApplicationContext(), msg);
    }

    public void showLongToast(String msg) {
        ToastUtils.showLongToast(this.getApplicationContext(), msg);
    }

    public void showProgressDialog(String msg) {
        new DialogUtils.ProgressDiaBuilder(this).setMsg(msg).build();
    }

}
