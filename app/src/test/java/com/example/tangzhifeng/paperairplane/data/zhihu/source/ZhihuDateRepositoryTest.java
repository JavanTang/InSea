package com.example.tangzhifeng.paperairplane.data.zhihu.source;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.remote.ZhihuRemoteDataSource;

import org.junit.Before;
import org.junit.Test;

/**
 * 作者: tangzhifeng on 2017/2/16.
 * 邮箱: tzfjobmail@gmail.com
 */
public class ZhihuDateRepositoryTest {



    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getZhihu() throws Exception {
        ZhihuRemoteDataSource zhihuRemoteDataSource=new ZhihuRemoteDataSource();
        zhihuRemoteDataSource.getZhihu("3892357", new ZhihuDateSource.GetZhiHuCallback() {
            @Override
            public void onZhiHuLoaded(ZhiHu zhiHu) {
                System.out.printf(""+zhiHu.toString());
            }

            @Override
            public void onZhiHuNotAvailable() {

            }
        });
    }

}