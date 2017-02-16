package com.example.tangzhifeng.paperairplane.detailedpager.zhihu;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateRepository;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateSource;

/**
 * 作者: tangzhifeng on 2017/2/16.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuDetailedPresenter implements ZhihuDetailedContract.Presenter {

    ZhihuDetailedContract.View mView;

    ZhihuDateRepository mZhihuDateRepository;

    public ZhihuDetailedPresenter(ZhihuDetailedContract.View view, ZhihuDateRepository zhihuDateRepository) {
        mView = view;
        mZhihuDateRepository = zhihuDateRepository;
        mView.setPresenter(this);
    }

    @Override
    public void backZhihuListFragment() {

    }

    @Override
    public void share(String info) {

    }

    @Override
    public void loadZhihu(String id) {
        mZhihuDateRepository.getZhihu(id, new ZhihuDateSource.GetZhiHuCallback() {
            @Override
            public void onZhiHuLoaded(ZhiHu zhiHu) {
                mView.showContent(zhiHu);
            }

            @Override
            public void onZhiHuNotAvailable() {

            }
        });
    }

    @Override
    public void start() {

    }
}
