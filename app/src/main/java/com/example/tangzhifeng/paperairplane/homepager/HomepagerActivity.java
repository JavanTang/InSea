package com.example.tangzhifeng.paperairplane.homepager;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.tangzhifeng.paperairplane.R;
import com.example.tangzhifeng.paperairplane.about.AboutActivity;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZHihuDataRepository;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.local.ZHihuLocalDataSource;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.remote.ZhihuRemoteDataSource;
import com.example.tangzhifeng.paperairplane.homepager.douban.DoubanHomePresenter;
import com.example.tangzhifeng.paperairplane.homepager.douban.DoubouHomeFragment;
import com.example.tangzhifeng.paperairplane.homepager.guoke.GuokeFragment;
import com.example.tangzhifeng.paperairplane.homepager.jiandanmeizi.JiandanFragment;
import com.example.tangzhifeng.paperairplane.homepager.jiandanmeizi.JiandanPresenter;
import com.example.tangzhifeng.paperairplane.homepager.zhihu.ZhiHuHomePresenter;
import com.example.tangzhifeng.paperairplane.homepager.zhihu.ZhihuHomeFagment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class HomepagerActivity extends AppCompatActivity {


    ViewPagerAdapter viewPagerAdapter;
    ZhihuHomeFagment mZhihuHomeFagment;
    GuokeFragment mGuokeFragment;
    DoubouHomeFragment mDoubouHomeFragment;
    JiandanFragment mJiandanFragment;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.tabs)
    TabLayout mTabs;
    @InjectView(R.id.viewpager)
    ViewPager mViewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepageractivity);
        initFragment();
        initView();

        //加载Toobar
        setSupportActionBar(mToolbar);
        setupViewPager(mViewpager);
        mTabs.setupWithViewPager(mViewpager);

        //这段代码有预加载的功能但是会受到果壳的影响
        mViewpager.setOffscreenPageLimit(3);
        mViewpager.setCurrentItem(0);
        mTabs.setId(0);
    }

    private void initView() {
        //取消标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.inject(this);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_about:
                        Intent intent = new Intent(HomepagerActivity.this, AboutActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        mZhihuHomeFagment = new ZhihuHomeFagment();
        mGuokeFragment = new GuokeFragment();
        mDoubouHomeFragment = new DoubouHomeFragment();
        mJiandanFragment = new JiandanFragment();

        new ZhiHuHomePresenter(mZhihuHomeFagment,
                new ZHihuDataRepository(ZhihuRemoteDataSource.getInstance(getApplicationContext()),
                        ZHihuLocalDataSource.getInstance(getApplicationContext())));
        new DoubanHomePresenter(mDoubouHomeFragment, HomepagerActivity.this);
        new JiandanPresenter(mJiandanFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }


    public void setupViewPager(ViewPager upViewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragmentAndTitle(mZhihuHomeFagment, "知乎日报");
        viewPagerAdapter.addFragmentAndTitle(mDoubouHomeFragment, "豆瓣时刻");
        viewPagerAdapter.addFragmentAndTitle(mGuokeFragment, "果壳分钟");
        // TODO: 2017/6/23 本来这个还有一个页面是显示煎蛋的妹子图
//        viewPagerAdapter.addFragmentAndTitle(mJiandanFragment, "煎蛋妹子图");
        mViewpager.setAdapter(viewPagerAdapter);

    }


    int backSize = 0;
    Date beforeTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Date currentTime = new Date();
        if (keyCode == event.KEYCODE_BACK) {
            if (backSize == 0) {
                beforeTime = currentTime;
                backSize = 1;
            } else if (backSize == 1) {
                if (beforeTime != null) {
                    if (currentTime.getTime() - beforeTime.getTime() <= 2000) {
                        finish();
                    } else {
                        backSize = 0;
                    }
                } else {
                    backSize = 0;
                }
            }
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            backSize = 0;
        }
        return super.onKeyDown(keyCode, event);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mTitle = new ArrayList<>();


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

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }

        public void addFragmentAndTitle(Fragment zhihuHomeFagment, String mTitle) {
            mFragmentList.add(zhihuHomeFagment);
            this.mTitle.add(mTitle);
        }


    }

}
