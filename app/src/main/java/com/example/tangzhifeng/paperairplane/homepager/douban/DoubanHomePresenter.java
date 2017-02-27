package com.example.tangzhifeng.paperairplane.homepager.douban;

import android.content.Context;

import com.example.tangzhifeng.paperairplane.data.douban.Douban;
import com.example.tangzhifeng.paperairplane.data.douban.source.DoubanDateRepository;
import com.example.tangzhifeng.paperairplane.data.douban.source.DoubanDateSource;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/21.
 * 邮箱: tzfjobmail@gmail.com
 */

public class DoubanHomePresenter implements DoubanHomeContract.Presenter{

    DoubanHomeContract.View mView;
    DoubanDateSource mDateSource;

    public DoubanHomePresenter(DoubanHomeContract.View view, DoubanDateSource dateSource) {
        mView=view;
        mDateSource=dateSource;
    }

    public DoubanHomePresenter(DoubanHomeContract.View view,Context context){
        mView=view;
        mDateSource=new DoubanDateRepository(context);
    }


    @Override
    public void dropRefreshEvent(List<Douban> doubanList) {

    }

    @Override
    public void pullRefreshEvent(String date) {

    }

    @Override
    public void chooseDateEvent(String date) {

    }

    @Override
    public void start() {

    }
}
