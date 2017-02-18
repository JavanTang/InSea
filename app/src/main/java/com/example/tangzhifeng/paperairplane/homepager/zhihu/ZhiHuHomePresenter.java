package com.example.tangzhifeng.paperairplane.homepager.zhihu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tangzhifeng.paperairplane.adapter.ZhihuRecycleAdapter;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZHihuDataRepository;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateSource;
import com.example.tangzhifeng.paperairplane.detailedpager.DetailedPagerActivity;
import com.example.tangzhifeng.paperairplane.util.ZhihuUtil;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhiHuHomePresenter implements ZhiHuHomepagerContract.Presenter {

    ZhiHuHomepagerContract.View mView;
    ZHihuDataRepository mZhihuDateRepository;

    public ZhiHuHomePresenter(ZhiHuHomepagerContract.View view, ZHihuDataRepository zhihuDateRepository) {
        mView = view;
        mZhihuDateRepository = zhihuDateRepository;
        mView.setPresenter(this);
    }


    /**
     * 查看是否有缓存,如果有缓存就先显示缓存,
     */
    @Override
    public void start() {
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
//        Log.e("tzf", "dropDownRefresh: ",new Exception() );
        mZhihuDateRepository.isZhihuListUpdate(lists.get(0), new ZhihuDateSource.CheckZhihuListUpdateCallBack() {
            @Override
            public void onZHihuListUpdate(ZhiHuList zhiHuList) {
                zhihuRecycleAdapter.getZhiHuLists().remove(0);
                zhihuRecycleAdapter.getZhiHuLists().add(0, zhiHuList);
                mView.stopDropToRefresh();
                mView.stopPullToRefresh();


                for (ZhiHuList.StoriesBean storiesBean : zhiHuList.getStories()) {
                    mZhihuDateRepository.getZhihu(storiesBean.getId() + "", new ZhihuDateSource.GetZhiHuCallback() {
                        @Override
                        public void onZhiHuLoaded(ZhiHu zhiHu) {
                            mZhihuDateRepository.saveZhihu(zhiHu);
                        }

                        @Override
                        public void onZhiHuObtainFailure() {
                            Log.i("tzf", "onZhiHuObtainFailure: ---------------TAG---------------");
                        }
                    });

                }


            }

            @Override
            public void onZhihuListNotUpdate() {
                mView.showZhihuListNotUpdate();
            }
        });
    }

    @Override
    public void pullToRefresh(List<ZhiHuList> lists, final ZhihuRecycleAdapter zhihuRecycleAdapter) {
        mZhihuDateRepository.getZHihuList(ZhihuUtil.getSpecifiedDayBefore(lists.get(lists.size() - 1).getDate()), new ZhihuDateSource.LoadZhiHuListCallback() {
            @Override
            public void onZhiHuListLoaded(List<ZhiHuList> zhiHuLists) {
                zhihuRecycleAdapter.getZhiHuLists().add(zhiHuLists.size(), zhiHuLists.get(0));
                mView.stopDropToRefresh();
                mView.stopPullToRefresh();

                for (ZhiHuList zhiHuList : zhiHuLists)
                for (ZhiHuList.StoriesBean storiesBean : zhiHuList.getStories()) {
                    mZhihuDateRepository.getZhihu(storiesBean.getId() + "", new ZhihuDateSource.GetZhiHuCallback() {
                        @Override
                        public void onZhiHuLoaded(ZhiHu zhiHu) {
                            mZhihuDateRepository.saveZhihu(zhiHu);
                        }

                        @Override
                        public void onZhiHuObtainFailure() {
                            Log.i("tzf", "onZhiHuObtainFailure: ---------------TAG---------------");
                        }
                    });

                }
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


    @Override
    public void ClickZhihuItem(String s, Context context) {
        Intent intent = new Intent(context, DetailedPagerActivity.class);
        intent.putExtra("mode", DetailedPagerActivity.MODE_ZHIHU);
        intent.putExtra("id", Integer.valueOf(s));

        context.startActivity(intent);
    }
}
