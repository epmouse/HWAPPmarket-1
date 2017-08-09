package com.example.breadcrumbsnavigationlibrary.bic_stucture.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.breadcrumbsnavigationlibrary.R;
import com.example.breadcrumbsnavigationlibrary.bic_stucture.BreadView;
import com.example.breadcrumbsnavigationlibrary.bic_stucture.MyBread_1;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StructureActivity extends AppCompatActivity {

    @BindView(R.id.ll_bread)
    BreadView llBread;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn_back)
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structure);
        ButterKnife.bind(this);

    }

    int i = 0;

    @OnClick({R.id.btn, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                i++;
//                MyItem item = new MyItem(this);
//                item.setDepartmentId("aaaa" + i);

                MyBread_1 myBread_1 = new MyBread_1(this);

                llBread.addView(myBread_1, item1 -> {
                });
                break;
            case R.id.btn_back:
                llBread.removeTopView((BreadView.CurrentItemListener<MyBread_1>) item12 -> {

                });
                break;
        }
    }
}
