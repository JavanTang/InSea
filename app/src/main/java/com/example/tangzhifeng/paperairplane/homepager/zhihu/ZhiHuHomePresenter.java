package com.example.tangzhifeng.paperairplane.homepager.zhihu;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateRepository;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateSource;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.local.ZHihuLocalDataSource;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.remote.ZhihuRemoteDataSource;

import java.util.Date;
import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhiHuHomePresenter implements ZhiHuHomepagerContract.Presenter {

    ZhiHuHomepagerContract.View mView;
    ZhihuDateRepository mZhihuDateRepository;

    public ZhiHuHomePresenter(ZhiHuHomepagerContract.View view, ZhihuDateRepository zhihuDateRepository) {
        mView = view;
        mZhihuDateRepository = zhihuDateRepository;
        mView.setPresenter(this);
    }

    @Override
    public void loadDetailed(ZhiHu zhiHu) {


    }

    @Override
    public void obtainList(Date time) {

    }

    @Override
    public void addZHiHuList(List<ZhiHuList> zhiHuListArrayList, ZhiHuList zhiHuList) {

    }

    @Override
    public void addZHiHuList(ZhiHuList zhiHuList) {

    }


    /**
     * 查看是否有缓存,如果有缓存就先显示缓存,
     */
    @Override
    public void start() {
        mZhihuDateRepository=ZhihuDateRepository.getInstance(new ZhihuRemoteDataSource(),
                new ZHihuLocalDataSource());

        mZhihuDateRepository.getZhiHuList(new ZhihuDateSource.LoadZhiHuListCallback() {
            @Override
            public void onZhiHuListLoaded(List<ZhiHuList> zhiHuLists) {

                mView.showZhiHuList(zhiHuLists);
            }

            @Override
            public void onZhiHuListNotAvailable() {
                mView.showNetwordNotAvailable();
            }
        });

    }
}
