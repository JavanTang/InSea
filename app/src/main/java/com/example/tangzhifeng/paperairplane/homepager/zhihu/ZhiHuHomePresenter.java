package com.example.tangzhifeng.paperairplane.homepager.zhihu;

import com.example.tangzhifeng.paperairplane.adapter.ZhihuRecycleAdapter;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateRepository;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateSource;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.local.ZHihuLocalDataSource;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.remote.ZhihuRemoteDataSource;
import com.example.tangzhifeng.paperairplane.util.ZhihuListHttpUtil;

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


    @Override
    public void dropDownRefresh(List<ZhiHuList> lists, final ZhihuRecycleAdapter zhihuRecycleAdapter) {
        mZhihuDateRepository.isZhihuListUpdate(lists.get(0), new ZhihuDateSource.CheckZhihuListUpdateCallBack() {
            @Override
            public void onZHihuListUpdate(ZhiHuList zhiHuList) {
                zhihuRecycleAdapter.getZhiHuLists().remove(0);
                zhihuRecycleAdapter.getZhiHuLists().add(0,zhiHuList);
                mView.stopDropToRefresh();
                mView.stopPullToRefresh();
            }

            @Override
            public void onZhihuListNotUpdate() {
                mView.showZhihuListNotUpdate();
            }
        });
    }

    @Override
    public void pullToRefresh(List<ZhiHuList> lists, final ZhihuRecycleAdapter zhihuRecycleAdapter) {
        mZhihuDateRepository.getZHihuList(ZhihuListHttpUtil.getSpecifiedDayBefore(lists.get(lists.size() - 1).getDate()), new ZhihuDateSource.LoadZhiHuListCallback() {
            @Override
            public void onZhiHuListLoaded(List<ZhiHuList> zhiHuLists) {
                zhihuRecycleAdapter.getZhiHuLists().add(zhiHuLists.size(),zhiHuLists.get(0));
                mView.stopDropToRefresh();
                mView.stopPullToRefresh();
            }

            @Override
            public void onZhiHuListNotAvailable() {
                mView.showNetwordNotAvailable();
            }
        });
    }

    @Override
    public void ClickZhihuItem(String id) {

    }
}
