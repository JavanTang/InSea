package com.example.tangzhifeng.paperairplane.data.douban.source;

import com.example.tangzhifeng.paperairplane.data.douban.Douban;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/27.
 * 邮箱: tzfjobmail@gmail.com
 */

public interface DoubanDateSource {



    /**
     * 获取DoubanList回调接口
     */
    interface DoubanListCallback  {
        void onFailure();

        void onSuccess(List<Douban> doubanOpenDailies);
    }

    /**
     * 获取Douban回调接口
     */
    interface DoubanHtmlCallback {
        void onFailure();

        void onSuccess(String html);
    }


    /**
     * 返回Douban的数据
     *
     * @param id                 豆瓣的id
     * @param loadDoubanHtmlCallback
     */
    void getDouban(String id, DoubanHtmlCallback loadDoubanHtmlCallback);


    /**
     * 这个返回豆瓣的List
     *
     * @param loadDoubanListCallback
     */
    void getDoubanList(DoubanListCallback loadDoubanListCallback);


    /**
     * 返回豆瓣的List
     *
     * @param date                     通过设定的date来返回数据
     * @param remoteDoubanListCallback
     */
    void getDoubanList(String date, DoubanListCallback remoteDoubanListCallback);

    void saveDouban(Douban douban);

    void saveDouban(List<Douban> doubanList);
}
