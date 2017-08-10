package com.example.breadcrumbsnavigationlibrary.bic_stucture;

import android.content.Context;
import android.graphics.Color;

import com.example.breadcrumbsnavigationlibrary.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/31.
 */

public class MyItem extends BreadView.BaseBreadItem {
    public MyItem(Context context) {
        super(context);
    }
    @Override
    public int setLayout() {
        return R.layout.bread_item_layout;
    }
    private String departmentId;
    private ArrayList<MyContact> children;
    private String departmentName;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public ArrayList<MyContact> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<MyContact> children) {
        this.children = children;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    @Override
    public void setViewHight(BaseVH vh, BreadView.BaseBreadItem item) {
        MyItem item1= (MyItem) item;
        vh.setText(R.id.tv,item1.getDepartmentId())
                .setTextColor(R.id.tv, Color.RED)
                .setImgUrl(R.id.iv, R.drawable.photochoice_sle);
    }

    @Override
    public void setNoViewHight(BaseVH vh,BreadView.BaseBreadItem item) {
        MyItem item1= (MyItem) item;
        vh.setText(R.id.tv,item1.getDepartmentId())
                .setTextColor(R.id.tv, Color.GRAY)
                .setImgUrl(R.id.iv, R.drawable.photochoice_nor);
    }
}
