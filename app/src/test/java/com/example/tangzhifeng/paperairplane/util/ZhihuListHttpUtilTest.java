package com.example.tangzhifeng.paperairplane.util;

import org.junit.Before;
import org.junit.Test;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */
public class ZhihuListHttpUtilTest {
    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void getCurrentDate() throws Exception {
        System.out.println(ZhihuUtil.getCurrentDate());
        System.out.println(ZhihuUtil.getSpecifiedDayBefore("20170201"));
    }

}