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

public class DoubanHomePresenter implements DoubanHomeContract.Presenter {

    DoubanHomeContract.View mView;
    DoubanDateSource mDateSource;

    public DoubanHomePresenter(DoubanHomeContract.View view, DoubanDateSource dateSource) {
        mView = view;
        mDateSource = dateSource;
        mView.setPresenter(this);
    }

    public DoubanHomePresenter(DoubanHomeContract.View view, Context context) {
        mView = view;
        mDateSource = new DoubanDateRepository(context);
        mView.setPresenter(this);
    }


    @Override
    public void dropRefreshEvent(final List<Douban> doubanList) {
        mDateSource.getDoubanList(new DoubanDateSource.DoubanListCallback() {
            @Override
            public void onFailure() {
                mView.showNetwordNotAvailable();
            }

            @Override
            public void onSuccess(List<Douban> doubanOpenDailies) {
                if (doubanList.get(0).getDouban_id() != doubanList.get(0).getDouban_id()) {
                    mView.updateAdapter(doubanOpenDailies);
                }
            }
        });
    }

    @Override
    public void pullRefreshEvent(String date) {
        chooseDateEvent(date);
        mView.stopRefreshView();
    }

    @Override
    public void chooseDateEvent(String date) {
        mDateSource.getDoubanList(date, new DoubanDateSource.DoubanListCallback() {
            @Override
            public void onFailure() {
                mView.showDataObtainFailure();
            }

            @Override
            public void onSuccess(List<Douban> doubanOpenDailies) {
                mView.updateAdapter(doubanOpenDailies);
            }
        });
    }

    @Override
    public void start() {
        mDateSource.getDoubanList(new DoubanDateSource.DoubanListCallback() {
            @Override
            public void onFailure() {
                mView.showDataObtainFailure();
            }

            @Override
            public void onSuccess(List<Douban> doubanOpenDailies) {
                mView.updateAdapter(doubanOpenDailies);
            }
        });
    }
}
