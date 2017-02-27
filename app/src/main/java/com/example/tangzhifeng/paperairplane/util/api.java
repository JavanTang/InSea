package com.example.tangzhifeng.paperairplane.util;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class Api {

    //The latest news
    public static final String LATEST_NEWS="http://news-at.zhihu.com/api/4/news/latest";

    //后面加日期,例如:http://news-at.zhihu.com/api/4/news/before/20131119
    public static final String PREVIOUS_MESSAGE="http://news-at.zhihu.com/api/4/news/before/";

    //The detailed content
    //详细内容
    public static final String DETAILED_CONTENT="http://news-at.zhihu.com/api/4/news/";
    /**
     * 根据日期查询文章
     * https://moment.douban.com/api/stream/date/2015-09-24
     */
    public static String GetContentByDate = "https://moment.douban.com/api/stream/date/";

}
