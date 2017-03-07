package com.example.tangzhifeng.paperairplane.detailedpager.jiandan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tangzhifeng.paperairplane.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者: tangzhifeng on 2017/3/6.
 * 邮箱: tzfjobmail@gmail.com
 */

public class JiandanDetailActivity extends AppCompatActivity {
    @InjectView(R.id.jiandan_detail_img)
    SimpleDraweeView mJiandanDetailImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiandan_detail_activity);
        ButterKnife.inject(this);
        String uri=getIntent().getExtras().getString("uri");
        mJiandanDetailImg.setImageURI(uri);
    }
}
