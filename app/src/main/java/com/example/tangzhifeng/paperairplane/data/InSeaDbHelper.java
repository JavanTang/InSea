package com.example.tangzhifeng.paperairplane.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tangzhifeng.paperairplane.data.douban.source.local.DoubanPersistenContract;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.local.ZhihuPersistencContract;

/**
 * 作者: tangzhifeng on 2017/2/17.
 * 邮箱: tzfjobmail@gmail.com
 */

public class InSeaDbHelper extends SQLiteOpenHelper {

    //数据库的名字
    public static final String DB_NAME = "InSea";

    //基本的SQLite的数据类型
    private static final String TEXT_TYPE = " TEXT";
    private static final String BOOLEAN_TYPE = " INTEGER";
    private static final String COMMA = ",";
    //数据库版本号
    public static final int INSEA_DB_VERSION = 3;
    //创建知乎表
    public static final String ZHIHU_CREATER = "create table " + ZhihuPersistencContract.ZhihuEntry.TABLE_NAME + "("
            + ZhihuPersistencContract.ZhihuEntry.ZHIHU_ID + TEXT_TYPE + " primary key" + COMMA
            + ZhihuPersistencContract.ZhihuEntry.ZHIHU_TITLE + TEXT_TYPE + COMMA
            + ZhihuPersistencContract.ZhihuEntry.ZHIHU_BODY + TEXT_TYPE + COMMA
            + ZhihuPersistencContract.ZhihuEntry.ZHIHU_TITLE_IMG + TEXT_TYPE + COMMA
            + ZhihuPersistencContract.ZhihuEntry.ZHIHU_SMALL_IMG + TEXT_TYPE + COMMA
            + ZhihuPersistencContract.ZhihuEntry.ZHIHU_DATE + TEXT_TYPE
            + ")";

    public static final String DOUBAN_CREATE = "create table " + DoubanPersistenContract.DoubanEntry.TABLE_NAME + "("
            + DoubanPersistenContract.DoubanEntry.DOUBAN_ID + TEXT_TYPE + " primary key " + COMMA
            + DoubanPersistenContract.DoubanEntry.DOUBAN_TITLE + TEXT_TYPE + COMMA
            + DoubanPersistenContract.DoubanEntry.DOUBAN_DATE + TEXT_TYPE + COMMA
            + DoubanPersistenContract.DoubanEntry.DOUBAN_AUTHOR_AVATAR + COMMA
            + DoubanPersistenContract.DoubanEntry.DOUBAN_AUTHOR_NAME + COMMA
            + DoubanPersistenContract.DoubanEntry.DOUBAN_COMMENTS_COUNT + COMMA
            + DoubanPersistenContract.DoubanEntry.DOUBAN_LIKE_COUNT + COMMA
            + DoubanPersistenContract.DoubanEntry.DOUBAN_ICON + COMMA
            + DoubanPersistenContract.DoubanEntry.DOUBAN_HTML + COMMA
            + DoubanPersistenContract.DoubanEntry.DOUBAN_CREATED_TIME + COMMA
            + ")";

    public InSeaDbHelper(Context context) {
        super(context, DB_NAME, null, INSEA_DB_VERSION);
    }


    public InSeaDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public InSeaDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    //放置创建表语句
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ZHIHU_CREATER);
        db.execSQL(DOUBAN_CREATE);
    }

    //放置更新表的语句
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (newVersion) {
            case 1:
                db.execSQL("ALTER TABLE " + ZhihuPersistencContract.ZhihuEntry.TABLE_NAME + " ADD COLUMN " + ZhihuPersistencContract.ZhihuEntry.ZHIHU_DATE);
            case 2:
                db.execSQL(DOUBAN_CREATE);
            case 3:

        }
    }
}
