package com.example.tangzhifeng.paperairplane.data.zhihu.source;

import android.support.annotation.NonNull;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZHihuDataRepository implements ZhihuDateSource {

    // TODO: 2017/2/15 先完成remote部分的操作



    private static ZHihuDataRepository sZhihuDateRepository;
    public ZhihuDateSource localData;
    public ZhihuDateSource remoteData;

    public ZHihuDataRepository(ZhihuDateSource remoteData, ZhihuDateSource localData) {
        this.remoteData = remoteData;
        this.localData = localData;
    }

    public static ZHihuDataRepository getInstance(ZhihuDateSource remoteData, ZhihuDateSource localData) {
        if (sZhihuDateRepository==null){
            sZhihuDateRepository=new ZHihuDataRepository(remoteData,localData);
        }
        return sZhihuDateRepository;
    }

    @Override
    public void isZhihuListUpdate(ZhiHuList zhiHuList, CheckZhihuListUpdateCallBack checkZhihuListUpdateCallBack) {
        remoteData.isZhihuListUpdate(zhiHuList,checkZhihuListUpdateCallBack);
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

            //9230038
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
        if (!localData.isCheckId(zhiHu.getId()+"")) {
            localData.saveZhihu(zhiHu);
        }

    }

    @Override
    public void deleteZhiHu(ZhiHu zhiHu) {

    }

    @Override
    public void deleteZhiHu(String id) {

    }

    @Override
    public boolean isCheckId(String id) {
        return false;
    }
}
