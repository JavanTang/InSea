package com.example.tangzhifeng.paperairplane.util;

/**
 * 作者: tangzhifeng on 2017/2/9.
 * 邮箱: tzfjobmail@gmail.com
 */

public class Api {
    public static final String ZHIHU_NEWS = "http://news-at.zhihu.com/api/4/news/";

    // 过往消息
    // 若要查询的11月18日的消息，before后面的数字应该为20161118
    // 知乎日报的生日为2013 年 5 月 19 日，如果before后面的数字小于20130520，那么只能获取到空消息
    public static final String ZHIHU_HISTORY = "http://news.at.zhihu.com/api/4/news/before/";



}
