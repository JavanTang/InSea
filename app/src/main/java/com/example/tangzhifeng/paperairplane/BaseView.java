package com.example.tangzhifeng.paperairplane;

import android.view.View;

/**
 * Created by tangzhifeng on 2017/2/8.
 */

public interface BaseView<T> {
    /**
     * @param presenter
     * 将Presenter示例传入view，调用时机为Presenter实现类的构造方法中
     */
    void setPresenter(T presenter);

    /**
     * 用于初始化界面元素，调用时机为Fragment的onCreate()方法中。
     * @param view
     * null
     */
    void initViews(View view);
}
