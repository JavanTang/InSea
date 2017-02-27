package com.example.tangzhifeng.paperairplane.homepager.douban;

import com.example.tangzhifeng.paperairplane.BasePresenter;
import com.example.tangzhifeng.paperairplane.BaseView;
import com.example.tangzhifeng.paperairplane.data.douban.Douban;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/21.
 * 邮箱: tzfjobmail@gmail.com
 */

public interface DoubanHomeContract {

    interface Presenter extends BasePresenter{

        /**
         * 下拉刷新的事件
         * @param doubanList 返回给我所有的DoubanOpenDaily
         */
        void dropRefreshEvent(List<Douban> doubanList);

        /**
         * 上拉刷新事件
         * @param date 前一天的date
         */
        void pullRefreshEvent(String date);

        /**
         * 根据date来返回List<DoubanOpenDaily>
         * @param date 选择的日期
         */
        void chooseDateEvent(String date);

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
        void updateAdapter(List<Douban> doubanList);

        /**
         * 让刷新View stop
         */
        void stopRefreshView();

    }

}
