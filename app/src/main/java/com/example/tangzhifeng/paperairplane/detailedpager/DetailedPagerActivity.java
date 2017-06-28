package com.example.tangzhifeng.paperairplane.detailedpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZHihuDataRepository;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.local.ZHihuLocalDataSource;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.remote.ZhihuRemoteDataSource;
import com.example.tangzhifeng.paperairplane.detailedpager.zhihu.ZhihuDetailedFragment;
import com.example.tangzhifeng.paperairplane.detailedpager.zhihu.ZhihuDetailedPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者: tangzhifeng on 2017/2/16.
 * 邮箱: tzfjobmail@gmail.com
 */

public class DetailedPagerActivity extends AppCompatActivity {

    @InjectView(R.id.detailsViewPager)
    ViewPager mDetailsViewPager;
    ViewPagerAdapter viewPagerAdapter;

    int currentId;
    int currentMode;

    public static final int MODE_ZHIHU=1;
    public static final int MODE_GUOKE=2;
    public static final int MODE_DOUBAN=3;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailspageractivity);
//        取消全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.inject(this);
        currentMode=getIntent().getIntExtra("mode",0);
        currentId=getIntent().getIntExtra("id",-1);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mDetailsViewPager.setAdapter(viewPagerAdapter);



        switch (currentMode) {
            case MODE_ZHIHU:
                ZhihuDetailedFragment zhihuDetailedFragment=new ZhihuDetailedFragment(currentId);
                new ZhihuDetailedPresenter(zhihuDetailedFragment,
                        new ZHihuDataRepository(ZhihuRemoteDataSource.getInstance(getApplicationContext()),
                                ZHihuLocalDataSource.getInstance(getApplicationContext())),getApplicationContext());
                viewPagerAdapter.addFragmentAndTitle(zhihuDetailedFragment);
                viewPagerAdapter.notifyDataSetChanged();
                break;
            case MODE_GUOKE:
                break;
            case MODE_DOUBAN:
                break;
        }
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList = new ArrayList<>();



        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }


        public void addFragmentAndTitle(ZhihuDetailedFragment zhihuHomeFagment) {
            mFragmentList.add(zhihuHomeFagment);
        }
    }
}
