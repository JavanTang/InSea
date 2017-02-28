package com.example.tangzhifeng.paperairplane.data.douban.source.remote;

import android.util.Log;

import com.example.tangzhifeng.paperairplane.data.douban.Douban;
import com.example.tangzhifeng.paperairplane.data.douban.source.DoubanDateSource;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/27.
 * 邮箱: tzfjobmail@gmail.com
 */
public class DoubanRemoteDateSourceTest {
    DoubanRemoteDateSource mDoubanRemoteDateSource;

    @Before
    public void setUp() throws Exception {
        mDoubanRemoteDateSource=DoubanRemoteDateSource.getInstance();
    }

    @Test
    public void getDoubanList() throws Exception {
        mDoubanRemoteDateSource.getDoubanList(new DoubanDateSource.DoubanListCallback() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(List<Douban> doubanOpenDailies) {
                for (Douban doubanOpenDaily : doubanOpenDailies) {
                    Log.i("tzf", "onSuccess: "+doubanOpenDailies.toString());
                }
            }
        });
    }



}