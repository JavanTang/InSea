package com.example.tangzhifeng.paperairplane.homepager.zhihu;

import android.content.Context;

import com.example.tangzhifeng.paperairplane.BasePresenter;
import com.example.tangzhifeng.paperairplane.BaseView;
import com.example.tangzhifeng.paperairplane.adapter.ZhihuRecycleAdapter;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public interface ZhiHuHomepagerContract {

    interface Presenter extends BasePresenter {


        //下拉刷新
        void dropDownRefresh(List<ZhiHuList> lists, ZhihuRecycleAdapter zhihuRecycleAdapter);
        //上拉刷新
        void pullToRefresh(List<ZhiHuList> lists,ZhihuRecycleAdapter zhihuRecycleAdapter);
        //点击知乎Item
        void ClickZhihuItem(String id);


        void ClickZhihuItem(String s, Context context);
    }
    interface View extends BaseView<Presenter>{
        //显示下拉刷新
        void showDropDownRefresh();
        //显示Recycle知乎列表
        void showZhiHuList(List<ZhiHuList> zhiHuListArrayList);
        //显示网络不可用
        void showNetwordNotAvailable();
        //更新RecycleView
        void refreshUI();
        //停止上拉刷新
        void stopPullToRefresh();
        //停止下拉刷新
        void stopDropToRefresh();
        //显示知乎列表没有更新
        void showZhihuListNotUpdate();
        //显示获取失败
        void showDPROP();
    }

}
