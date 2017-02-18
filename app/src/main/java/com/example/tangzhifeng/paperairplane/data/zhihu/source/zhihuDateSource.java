package com.example.tangzhifeng.paperairplane.data.zhihu.source;

import android.support.annotation.NonNull;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public interface ZhihuDateSource {


    /**
     * 回调加载ZhihuList的结果
     */
    interface LoadZhiHuListCallback {
        /**
         * @param zhiHuLists 返回ZhihuList列表数据
         */
        void onZhiHuListLoaded(List<ZhiHuList> zhiHuLists);

        /**
         * ZhihuList列表数据返回失败
         */
        void onZhiHuListNotAvailable();
    }

    interface GetZhiHuCallback {
        /**
         * @param zhiHu 返回Zhihu数据
         */
        void onZhiHuLoaded(ZhiHu zhiHu);

        /**
         * 返回Zhihu数据失败
         */
        void onZhiHuObtainFailure();
    }

    interface CheckZhihuListUpdateCallBack {
        /**
         * @param zhiHuList 返回更新过后的数据
         */
        void onZHihuListUpdate(ZhiHuList zhiHuList);

        /**
         * 数据已经不用更新了
         */
        void onZhihuListNotUpdate();
    }

    /**
     * 检查知乎日报是否更新
     *
     * @param zhiHuList                    需要验证的zhihulist
     * @param checkZhihuListUpdateCallBack 回调函数
     */
    //
    void isZhihuListUpdate(ZhiHuList zhiHuList, CheckZhihuListUpdateCallBack checkZhihuListUpdateCallBack);

    /**
     * 获取最新知乎日报列表集合,通过LoadZhiHuListCallback来接收
     * 获取
     *
     * @param loadZhiHuListCallback 回调函数
     */
    //
    void getZhiHuList(@NonNull LoadZhiHuListCallback loadZhiHuListCallback);

    /**
     * 根据date来返回知乎日报的集合,通过LoadZhiHuListCallback来接收
     *
     * @param date                  时间,例如20170215
     * @param loadZhiHuListCallback 回调函数
     */
    void getZHihuList(String date, LoadZhiHuListCallback loadZhiHuListCallback);


    //保存知乎日报列表
    void saveZhiHuList(List<ZhiHuList> zhiHuLists);

    //获取知乎详细信息
    void getZhihu(String id, GetZhiHuCallback getZhiHuCallback);

    //保存知乎详细信息
    void saveZhihu(ZhiHu zhiHu);

    //删除知乎详细信息
    void deleteZhiHu(ZhiHu zhiHu);

    void deleteZhiHu(String id);


}
