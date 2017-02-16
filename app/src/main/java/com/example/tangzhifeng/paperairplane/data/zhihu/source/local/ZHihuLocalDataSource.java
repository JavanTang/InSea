package com.example.tangzhifeng.paperairplane.data.zhihu.source.local;

import android.support.annotation.NonNull;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateSource;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZHihuLocalDataSource implements ZhihuDateSource {
    @Override
    public void isZhihuListUpdate(ZhiHuList zhiHuList, CheckZhihuListUpdateCallBack checkZhihuListUpdateCallBack) {

    }

    @Override
    public void getZhiHuList(@NonNull LoadZhiHuListCallback loadZhiHuListCallback) {

    }

    @Override
    public void getZHihuList(String date, LoadZhiHuListCallback loadZhiHuListCallback) {

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
