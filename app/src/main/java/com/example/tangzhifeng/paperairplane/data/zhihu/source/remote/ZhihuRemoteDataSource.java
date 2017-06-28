package com.example.tangzhifeng.paperairplane.data.zhihu.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuListNews;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZHihuDataRepository;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.local.ZHihuLocalDataSource;
import com.example.tangzhifeng.paperairplane.util.Api;
import com.example.tangzhifeng.paperairplane.util.HttpUtil;
import com.example.tangzhifeng.paperairplane.util.MyOkHttpClient;
import com.example.tangzhifeng.paperairplane.util.ZhihuUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuRemoteDataSource implements IZhihuRemoteSource {

    private static ZhihuRemoteDataSource sZhihuRemoteDataSource;
    private static ZHihuLocalDataSource sZHihuLocalDataSource;
    private static ZHihuDataRepository sDataRepository;

    private ZhihuRemoteDataSource() {

    }

    public static ZhihuRemoteDataSource getInstance(Context context) {
        if (sZhihuRemoteDataSource == null) {
            sZhihuRemoteDataSource = new ZhihuRemoteDataSource();
        }
        sZHihuLocalDataSource = ZHihuLocalDataSource.getInstance(context);
        sDataRepository = new ZHihuDataRepository(sZhihuRemoteDataSource, sZHihuLocalDataSource);
        return sZhihuRemoteDataSource;
    }


    @Override
    public void isZhihuListUpdate(final ZhiHuList zhiHuList, final CheckZhihuListUpdateCallBack checkZhihuListUpdateCallBack) {

        MyOkHttpClient.getInstance().asyncGet(Api.LATEST_NEWS, new MyOkHttpClient.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                checkZhihuListUpdateCallBack.onZhihuListNotUpdate();
            }

            @Override
            public void onSuccess(Request request, String result) {
                boolean checkUpadate = false;
                ZhiHuListNews zhiHuListNews = new ZhiHuListNews();
                Gson gson = new Gson();
                zhiHuListNews = gson.fromJson(result, ZhiHuListNews.class);
                zhiHuList.setDate(ZhihuUtil.getCurrentDate());
                //如果zhiHuList为空,则直接导入数据
                if (zhiHuList.getStories() == null) {
                    zhiHuList.setStories(zhiHuListNews.getStories());
                    checkZhihuListUpdateCallBack.onZHihuListUpdate(zhiHuList);
                    return;
                }

                //如果ZhiHuListNews比zhiHuList有改动,则直接替换
                List<ZhiHuList.StoriesBean> storiesBeanList = new ArrayList<ZhiHuList.StoriesBean>();
                for (ZhiHuList.StoriesBean storiesBean : zhiHuListNews.getStories()) {
                    if (!zhiHuList.getStories().contains(storiesBean)) {
                        checkUpadate = true;
                        zhiHuList.setStories(zhiHuListNews.getStories());
                        checkZhihuListUpdateCallBack.onZHihuListUpdate(zhiHuList);
                        return;
                    }
                }

                checkZhihuListUpdateCallBack.onZhihuListNotUpdate();
            }
        });

    }

    @Override
    public void getZhiHuList(@NonNull LoadZhiHuListCallback loadZhiHuListCallback) {
        getZHihuList(ZhihuUtil.getCurrentDate(), loadZhiHuListCallback);
    }


    @Override
    public void getZHihuList(final String date, final LoadZhiHuListCallback loadZhiHuListCallback) {

        HttpUtil.sendHttpRequest(Api.PREVIOUS_MESSAGE + date, new HttpUtil.IHttpCallbackListenet() {
            @Override
            public void onFinish(String response) {
                Log.i("tzf", "网络获取知乎数据网站为: " + Api.PREVIOUS_MESSAGE + date);
                ZhiHuList zhiHuList = new ZhiHuList();
                Gson gson = new Gson();
                try {
                    zhiHuList = gson.fromJson(response, ZhiHuList.class);
                } catch (Exception e) {
                    Log.e("tzf", "onFinish: " + response);
                    e.printStackTrace();
                }
                if (zhiHuList != null) {
                    List<ZhiHuList> zhiHuLists = new ArrayList<ZhiHuList>();
                    for (ZhiHuList.StoriesBean storiesBean : zhiHuList.getStories()) {
                        storiesBean.setDate(zhiHuList.getDate());
                    }
                    zhiHuLists.add(zhiHuList);
                    loadZhiHuListCallback.onZhiHuListLoaded(zhiHuLists);
                } else {
                    loadZhiHuListCallback.onZhiHuListNotAvailable();
                }
            }

            @Override
            public void onError(Exception e) {
                loadZhiHuListCallback.onZhiHuListNotAvailable();
            }
        });

    }

    @Override
    public void getZhihu(String id, final GetZhiHuCallback getZhiHuCallback) {
        HttpUtil.sendHttpRequest(Api.DETAILED_CONTENT + id, new HttpUtil.IHttpCallbackListenet() {
            @Override
            public void onFinish(String response) {
                ZhiHu zhiHu = new ZhiHu();
                Gson gson = new Gson();
                zhiHu = gson.fromJson(response, ZhiHu.class);
                if (zhiHu == null) getZhiHuCallback.onZhiHuObtainFailure();
                else getZhiHuCallback.onZhiHuLoaded(zhiHu);
            }

            @Override
            public void onError(Exception e) {
                getZhiHuCallback.onZhiHuObtainFailure();
            }
        });
    }


}
