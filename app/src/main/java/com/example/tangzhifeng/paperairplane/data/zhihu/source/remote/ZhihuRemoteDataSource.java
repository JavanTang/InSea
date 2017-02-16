package com.example.tangzhifeng.paperairplane.data.zhihu.source.remote;

import android.support.annotation.NonNull;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuListNews;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateSource;
import com.example.tangzhifeng.paperairplane.util.Api;
import com.example.tangzhifeng.paperairplane.util.HttpUtil;
import com.example.tangzhifeng.paperairplane.util.ZhihuListHttpUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuRemoteDataSource implements ZhihuDateSource {

    private static ZhihuRemoteDataSource sZhihuRemoteDataSource;

    public static ZhihuRemoteDataSource getInstance() {
        if (sZhihuRemoteDataSource == null) {
            sZhihuRemoteDataSource = new ZhihuRemoteDataSource();
        }
        return sZhihuRemoteDataSource;
    }


    @Override
    public void isZhihuListUpdate(final ZhiHuList zhiHuList, final CheckZhihuListUpdateCallBack checkZhihuListUpdateCallBack) {
        HttpUtil.sendHttpRequest(Api.LATEST_NEWS, new HttpUtil.IHttpCallbackListenet() {
            @Override
            public void onFinish(String response) {
                boolean checkUpadate=false;
                ZhiHuListNews zhiHuListNews=new ZhiHuListNews();
                Gson gson=new Gson();
                zhiHuListNews=gson.fromJson(response,ZhiHuListNews.class);

                List<ZhiHuList.StoriesBean> storiesBeanList=new ArrayList<ZhiHuList.StoriesBean>();

                for (ZhiHuList.StoriesBean storiesBean : zhiHuListNews.getStories()) {
                    if(!zhiHuList.getStories().contains(storiesBean)){
                        checkUpadate=true;
                        zhiHuList.setStories(zhiHuListNews.getStories());
                        checkZhihuListUpdateCallBack.onZHihuListUpdate(zhiHuList);
                        return ;
                    }
                }

                    checkZhihuListUpdateCallBack.onZhihuListNotUpdate();

            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void getZhiHuList(@NonNull LoadZhiHuListCallback loadZhiHuListCallback) {
        getZHihuList(ZhihuListHttpUtil.getCurrentDate(), loadZhiHuListCallback);
    }




    @Override
    public void getZHihuList(String date, final LoadZhiHuListCallback loadZhiHuListCallback) {

        HttpUtil.sendHttpRequest(Api.PREVIOUS_MESSAGE + date, new HttpUtil.IHttpCallbackListenet() {
            @Override
            public void onFinish(String response) {
                ZhiHuList zhiHuList = new ZhiHuList();
                Gson gson = new Gson();
                zhiHuList=gson.fromJson(response,ZhiHuList.class);

                if (zhiHuList != null) {

                    loadZhiHuListCallback.onZhiHuListLoaded(Arrays.asList(zhiHuList));
                } else {
                    loadZhiHuListCallback.onZhiHuListNotAvailable();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }


    @Override
    public void saveZhiHuList(List<ZhiHuList> zhiHuLists) {

    }



    @Override
    public void getZhihu(String id, GetZhiHuCallback getZhiHuCallback) {

    }

    @Override
    public void saveZhihu(ZhiHu zhiHu) {

    }

    @Override
    public void deleteZhiHu(ZhiHu zhiHu) {

    }

    @Override
    public void deleteZhiHu(String id) {

    }
}
