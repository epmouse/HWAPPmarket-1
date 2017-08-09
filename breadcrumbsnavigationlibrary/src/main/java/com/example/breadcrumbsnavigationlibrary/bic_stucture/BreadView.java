package com.example.breadcrumbsnavigationlibrary.bic_stucture;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.Stack;

/**
 * Created by stars on 17/7/31.
 */

public class BreadView extends HorizontalScrollView {
    LinearLayout linearLayout;
    Handler handler = new Handler();
    Stack<BaseBreadItem> viewStack = new Stack<>();
    Stack<BaseVH> vhStack = new Stack<>();
    private int clickableColor;
    private int unClickableColor;
    public Context context;

    public BreadView(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public BreadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);

    }

    public BreadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
    }

    @Override//设置不显示滚动条
    public void setHorizontalScrollBarEnabled(boolean horizontalScrollBarEnabled) {
        super.setHorizontalScrollBarEnabled(false);
    }

    private void initView(Context context) {
        if (linearLayout == null) {

            linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.CENTER_VERTICAL);
            addView(linearLayout);
        }
    }

    /**
     * 设置可点和不可点的颜色
     *
     * @param clickableColor
     * @param unClickableColor
     */
    public void setClickableColor(int clickableColor, int unClickableColor) {
        this.clickableColor = clickableColor;
        this.unClickableColor = unClickableColor;
    }

    public <T extends BaseBreadItem> void addView(T item, OnBreadItemClickListener<T> callback) {
        if (vhStack.size() > 0){
            item.setViewHight(vhStack.get(vhStack.size() - 1), viewStack.get(viewStack.size() - 1));
        }
        View view = item.getView();
        BaseVH vh = new BaseVH(context, view);
        vhStack.push(vh);
        item.setNoViewHight(vh, item);
        linearLayout.addView(view);
        viewStack.push(item);
        toRight();
        view.setOnClickListener(v -> {
            int search = viewStack.search(item);
            while (search > 1) {
                search--;
                BaseBreadItem item1 = viewStack.pop();
                vhStack.pop();
                linearLayout.removeView(item1.getView());
            }
            T peek = (T) viewStack.peek();
            BaseVH peek1 = vhStack.peek();
            callback.onItemClickListener(peek);
            peek.setNoViewHight(peek1, peek);

        });
    }

    /**
     * 删除最后的条目
     *
     * @param topItem
     * @param <T>
     */
    public <T extends BaseBreadItem> void removeTopView(CurrentItemListener<T> topItem) {
        if (viewStack.size() > 1) {
            BaseBreadItem pop = viewStack.pop();
            vhStack.pop();
            linearLayout.removeView(pop.getView());
            T peek = (T) viewStack.peek();
            BaseVH peek1 = vhStack.peek();
            peek.setNoViewHight(peek1, peek);
            topItem.showCurrentItem(peek);
        }
    }


    private void toRight() {//滚动到最后一个条目，由于是横向，所以是右边
        handler.post(() -> BreadView.this.fullScroll(ScrollView.FOCUS_RIGHT));
    }

    public interface CurrentItemListener<T extends BaseBreadItem> {
        void showCurrentItem(T item);
    }

    public interface OnBreadItemClickListener<T extends BaseBreadItem> {
        void onItemClickListener(T item);
    }

    static abstract class BaseBreadItem {
        protected View view;
        protected Context context;

        public BaseBreadItem(Context context) {
            this.context = context;
            setView();
        }
        public abstract int setLayout();

        public View getView() {
            return view;
        }

        private void setView() {
            View view = View.inflate(context, setLayout(), null);
            this.view = view;
        }

        public abstract void setViewHight(BaseVH view, BaseBreadItem item);

        public abstract void setNoViewHight(BaseVH view, BaseBreadItem item);

    }


}
