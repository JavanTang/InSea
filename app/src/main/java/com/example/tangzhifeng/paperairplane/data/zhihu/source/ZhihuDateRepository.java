package com.example.tangzhifeng.paperairplane.data.zhihu.source;

import android.support.annotation.NonNull;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuDateRepository implements ZhihuDateSource {

    // TODO: 2017/2/15 先完成remote部分的操作



    private static ZhihuDateRepository sZhihuDateRepository;
    ZhihuDateSource localData;
    ZhihuDateSource remoteData;

    public ZhihuDateRepository(ZhihuDateSource remoteData, ZhihuDateSource localData) {
        this.remoteData = remoteData;
        this.localData = localData;
    }

    public static ZhihuDateRepository getInstance(ZhihuDateSource remoteData, ZhihuDateSource localData) {
        if (sZhihuDateRepository==null){
            sZhihuDateRepository=new ZhihuDateRepository(remoteData,localData);
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
