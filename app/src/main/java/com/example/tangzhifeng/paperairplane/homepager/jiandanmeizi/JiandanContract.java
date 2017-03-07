package com.example.tangzhifeng.paperairplane.homepager.jiandanmeizi;

import com.example.tangzhifeng.paperairplane.BasePresenter;
import com.example.tangzhifeng.paperairplane.BaseView;
import com.example.tangzhifeng.paperairplane.data.jiandan.Jiandan;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/3/6.
 * 邮箱: tzfjobmail@gmail.com
 */

public interface JiandanContract {

    interface Presenter extends BasePresenter{
        /**
         * 下拉刷新的事件
         */
        void dropRefreshEvent();

        /**
         * 上拉刷新事件
         */
        void pullRefreshEvent();

    }

    interface View extends BaseView<Presenter>{
        /**
         * 显示网络不可用
         */
        void showNetwordNotAvailable();

        /**
         * 显示数据获取失败
         */
        void showDataObtainFailure();

        /**
         * 更新适配器 这个方法是唯一Presenter向View提供数据的方法.
         */
        void updateAdapter(List<Jiandan> doubanList);

        /**
         * 让刷新View stop
         */
        void stopRefreshView();
    }
}
