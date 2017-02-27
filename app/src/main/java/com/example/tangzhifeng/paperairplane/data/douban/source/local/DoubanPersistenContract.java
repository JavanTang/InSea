package com.example.tangzhifeng.paperairplane.data.douban.source.local;

import android.provider.BaseColumns;

/**
 * 作者: tangzhifeng on 2017/2/27.
 * 邮箱: tzfjobmail@gmail.com
 */

public class DoubanPersistenContract {

    public static class DoubanEntry implements BaseColumns {
        /**
         * 表名
         */
        public static final String TABLE_NAME = "douban";
        /**
         * 豆瓣ID
         */
        public static final String DOUBAN_ID = "douban_id";
        /**
         * 豆瓣详细页的HTTP请求返回的HTML
         */
        public static final String DOUBAN_HTML = "douban_html";
        /**
         * 这篇文章是多久分享到豆瓣一刻
         */
        public static final String DOUBAN_DATE = "douban_date";
        /**
         * 这篇文章的缩略图
         */
        public static final String DOUBAN_ICON = "douban_icon";
        /**
         * 作者的名字
         */
        public static final String DOUBAN_AUTHOR_NAME = "douban_author_name";
        /**
         * 作者的头像
         */
        public static final String DOUBAN_AUTHOR_AVATAR = "douban_author_avatar";
        /**
         * 这篇文章的创建时间
         */
        public static final String DOUBAN_CREATED_TIME = "douban_create_time";
        /**
         * 豆瓣评论数
         */
        public static final String DOUBAN_COMMENTS_COUNT = "douban_comments_count";

        /**
         * 豆瓣点赞数
         */
        public static final String DOUBAN_LIKE_COUNT = "douban_like_count";
        /**
         * 豆瓣标题
         */
        public static final String DOUBAN_TITLE = "douban_title";

        public static final String DOUBAN_URI="douban_uri";

    }
}
