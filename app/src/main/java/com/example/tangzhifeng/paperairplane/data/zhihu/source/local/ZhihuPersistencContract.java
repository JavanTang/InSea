package com.example.tangzhifeng.paperairplane.data.zhihu.source.local;

import android.provider.BaseColumns;

/**
 * 作者: tangzhifeng on 2017/2/17.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZhihuPersistencContract {

    /**
     * 这个是知乎创建表的一些数据
     */
    public static class ZhihuEntry implements BaseColumns{
        public static final String ZHIHU_ID="zhihu_id";
        public static final String TABLE_NAME="zhihu";
        public static final String ZHIHU_TITLE="title";
        public static final String ZHIHU_TITLE_IMG="titleImg";
        public static final String ZHIHU_BODY="body";
        public static final String ZHIHU_SMALL_IMG="smallImg";
        public static final String ZHIHU_DATE="date";
    }


}