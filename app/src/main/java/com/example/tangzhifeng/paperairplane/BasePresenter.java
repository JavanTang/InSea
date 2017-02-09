package com.example.tangzhifeng.paperairplane;

/**
 * Created by tangzhifeng on 2017/2/8.
 */

public interface BasePresenter {

    /**
     * 作用是Presenter开始获取数据并改变界面显示，调用时机为Fragment的onResume()方法中。
     */
    void start();
}
