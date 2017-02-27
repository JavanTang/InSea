package com.example.tangzhifeng.paperairplane.detailedpager.douban;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tangzhifeng.paperairplane.R;

public class DoubanDetailsActivity extends AppCompatActivity {
    DoubanDetailSFragment mDoubanDetailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban);
        initView();
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_douban,mDoubanDetailFragment).addToBackStack(null).commit();
        }
    }

    private void initView() {
        mDoubanDetailFragment = new DoubanDetailSFragment();
    }
}
