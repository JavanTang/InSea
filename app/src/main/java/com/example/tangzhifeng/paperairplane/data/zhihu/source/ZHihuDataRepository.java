package com.example.tangzhifeng.paperairplane.data.zhihu.source;

import android.support.annotation.NonNull;

import com.example.tangzhifeng.paperairplane.MyApplication;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.local.IZhihuLocalDataSource;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.remote.IZhihuRemoteSource;
import com.example.tangzhifeng.paperairplane.util.HttpUtil;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZHihuDataRepository implements IZhihuLocalDataSource,IZhihuRemoteSource {

    // TODO: 2017/2/15 先完成remote部分的操作



    private static ZHihuDataRepository sZhihuDateRepository;
    public IZhihuLocalDataSource localData;
    public IZhihuRemoteSource remoteData;

    public ZHihuDataRepository(IZhihuRemoteSource remoteData, IZhihuLocalDataSource localData) {
        this.remoteData = remoteData;
        this.localData = localData;
    }

    public static ZHihuDataRepository getInstance(IZhihuRemoteSource remoteData, IZhihuLocalDataSource localData) {
        if (sZhihuDateRepository==null){
            sZhihuDateRepository=new ZHihuDataRepository(remoteData,localData);
        }
        return sZhihuDateRepository;
    }

    @Override
    public void isZhihuListUpdate(ZhiHuList zhiHuList, CheckZhihuListUpdateCallBack checkZhihuListUpdateCallBack) {
        if(HttpUtil.isNetworkAvailable(MyApplication.getContext())){
            remoteData.isZhihuListUpdate(zhiHuList,checkZhihuListUpdateCallBack);
        }
    }

    @Override
    public void getZhiHuList(@NonNull LoadZhiHuListCallback loadZhiHuListCallback) {

        remoteData.getZhiHuList(loadZhiHuListCallback);
    }

    @Override
    public void getZHihuList(String date, LoadZhiHuListCallback loadZhiHuListCallback) {
        remoteData.getZHihuList(date,loadZhiHuListCallback);
    }



    @Override
    public void saveZhiHuList(List<ZhiHuList> zhiHuLists) {

    }


    @Override
    public void getZhihu(final String id, final GetZhiHuCallback getZhiHuCallback) {
        localData.getZhihu(id, new GetZhiHuCallback() {
            @Override
            public void onZhiHuLoaded(@NonNull ZhiHu zhiHu) {
                if(getZhiHuCallback!=null)
                getZhiHuCallback.onZhiHuLoaded(zhiHu);
            }

            @Override
            public void onZhiHuObtainFailure() {
                remoteData.getZhihu(id, new GetZhiHuCallback() {
                    @Override
                    public void onZhiHuLoaded(ZhiHu zhiHu) {
                        saveZhihu(zhiHu);
                        if(getZhiHuCallback!=null)
                        getZhiHuCallback.onZhiHuLoaded(zhiHu);
                    }

                    @Override
                    public void onZhiHuObtainFailure() {

                    }
                });
            }
        });

    }

    @Override
    public void saveZhihu(ZhiHu zhiHu) {
        if (!localData.isLocalCheckId(zhiHu.getId()+"")) {
            localData.saveZhihu(zhiHu);
        }

    }



    @Override
    public boolean isLocalCheckId(String id) {
        return false;
    }
}
