package com.example.tangzhifeng.paperairplane.interfaze;

import com.example.tangzhifeng.paperairplane.BasePresenter;
import com.example.tangzhifeng.paperairplane.BaseView;
import com.example.tangzhifeng.paperairplane.been.ZhihuDailyNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangzhifeng on 2017/2/8.
 */

public interface ZhihuDailyContract {
    interface View extends BaseView<Presenter>{
        void showError();
        void showLoading();
        void stopLoading();
        void showResults(List<ZhihuDailyNews.StoriesBean> list);
        void showPickDialog();
    }
    interface Presenter extends BasePresenter{
        void loadPosts(long date,boolean clearing);
        void refresh();
        void loadMore();
        void feelLucky();
    }



}
