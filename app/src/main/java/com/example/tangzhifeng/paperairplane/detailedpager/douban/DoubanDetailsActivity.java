package com.example.tangzhifeng.paperairplane.detailedpager.douban;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.tangzhifeng.paperairplane.R;

public class DoubanDetailsActivity extends AppCompatActivity {
    DoubanDetailSFragment mDoubanDetailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.douban_detail_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_douban,mDoubanDetailFragment).commit();
        }
    }

    private void initView() {
        mDoubanDetailFragment = new DoubanDetailSFragment();
    }
}
