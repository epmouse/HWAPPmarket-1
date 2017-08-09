package com.example.breadcrumbsnavigationlibrary.bic_stucture;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/2/15.
 */

public class BaseVH  {
    private SparseArray<View> views;
    private Context context;
    public View itemView;
    public BaseVH(Context context, View itemView) {
        this.itemView=itemView;
        this.context = context;
        views = new SparseArray<>();
    }

    protected <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public BaseVH setText(int viewId, CharSequence value) {
        TextView tv = getView(viewId);
        tv.setText(value);
        return this;
    }

    public BaseVH setTextColor(int textViewId,int clickableColor) {
        TextView viewById = (TextView) getView(textViewId);
        viewById.setTextColor(clickableColor);
        return this;
    }


    /**
     * 通过图片地址加载图片
     *
     * @param viewId
     * @param imgUrl
     * @return
     */
    public BaseVH setImgUrl(int viewId, String imgUrl) {
        ImageView img = getView(viewId);
        Glide.with(context).load(imgUrl).into(img);
        return this;
    }

    /**
     * 通过项目drawable中加载图片
     *
     * @param viewId
     * @param imgResourceId
     * @return
     */
    public BaseVH setImgUrl(int viewId, int imgResourceId) {
        ImageView img = getView(viewId);
//        Glide.with(context).load(imgResourceId).into(img);
        img.setImageResource(imgResourceId);
        return this;
    }
    public BaseVH setOther(int viewId) {
        getView(viewId);
        return this;
    }
    /**
     * 设置控件的隐藏与显示
     *
     * @param viewId
     * @param visible
     * @return
     */
    public BaseVH setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 点击事件
     * @param viewId
     * @param clickListener
     */
    public void setOnclick(int viewId, View.OnClickListener clickListener){
        getView(viewId).setOnClickListener(clickListener);
    }
}
