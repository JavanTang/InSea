package com.example.tangzhifeng.paperairplane.data.douban.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tangzhifeng.paperairplane.data.InSeaDbHelper;
import com.example.tangzhifeng.paperairplane.data.douban.Douban;
import com.example.tangzhifeng.paperairplane.data.douban.source.DoubanDateSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/27.
 * 邮箱: tzfjobmail@gmail.com
 */

public class DoubanLocalDateSource implements DoubanDateSource {

    static DoubanLocalDateSource sLocalDateSource;

    private InSeaDbHelper mInSeaDbHelper;

    private DoubanLocalDateSource(Context context) {
        mInSeaDbHelper = new InSeaDbHelper(context);
    }

    public static DoubanLocalDateSource getInstance(Context context) {
        if (sLocalDateSource == null) {
            sLocalDateSource = new DoubanLocalDateSource(context);
        }
        return sLocalDateSource;
    }

    @Override
    public synchronized void getDouban(String id, DoubanHtmlCallback loadDoubanHtmlCallback) {
        String html = null;
        SQLiteDatabase db = mInSeaDbHelper.getReadableDatabase();
        Cursor cursor = db.query(DoubanPersistenContract.DoubanEntry.TABLE_NAME, null
                , DoubanPersistenContract.DoubanEntry.DOUBAN_ID + "=?", new String[]{id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            html = cursor.getString(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_HTML));
        }
        cursor.close();
        db.close();
        if (html != null) {
            loadDoubanHtmlCallback.onSuccess(html);
        } else {
            loadDoubanHtmlCallback.onFailure();
        }
    }

    @Override
    public synchronized void getDoubanList(DoubanListCallback loadDoubanListCallback) {
        getDoubanList(null,loadDoubanListCallback);
    }


    @Override
    public synchronized void getDoubanList(String date, DoubanListCallback remoteDoubanListCallback) {
        SQLiteDatabase db = mInSeaDbHelper.getReadableDatabase();

        Cursor cursor;
        if (date != null) {
            cursor = db.query(DoubanPersistenContract.DoubanEntry.TABLE_NAME,
                    null, DoubanPersistenContract.DoubanEntry.DOUBAN_DATE,
                    new String[]{date}, null, null, null);

        } else {
            cursor = db.query(DoubanPersistenContract.DoubanEntry.TABLE_NAME,
                    null, null, null, null, null, null);

        }
        List<Douban> doubanList = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Douban douban = new Douban();
                int douban_id = cursor.getInt(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_ID));
                String douban_author_avatar = cursor.getString(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_AUTHOR_AVATAR));
                String douban_author_name = cursor.getString(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_AUTHOR_NAME));
                int douban_comments_count = cursor.getInt(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_COMMENTS_COUNT));
                String douban_created_time = cursor.getString(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_CREATED_TIME));
                String douban_date = cursor.getString(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_DATE));
                String douban_html = cursor.getString(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_HTML));
                String douban_icon = cursor.getString(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_ICON));
                int douban_like_count = cursor.getInt(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_LIKE_COUNT));
                String douban_title = cursor.getString(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_TITLE));
                String douban_uri = cursor.getString(cursor.getColumnIndexOrThrow(DoubanPersistenContract.DoubanEntry.DOUBAN_URI));
                douban.setDouban_html(douban_html);
                douban.setDouban_uri(douban_uri);
                douban.setDouban_title(douban_title);
                douban.setDouban_like_count(douban_like_count);
                douban.setDouban_author_avatar(douban_author_avatar);
                douban.setDouban_author_name(douban_author_name);
                douban.setDouban_comments_count(douban_comments_count);
                douban.setDouban_created_time(douban_created_time);
                douban.setDouban_icon(douban_icon);
                douban.setDouban_id(douban_id);
                douban.setDouban_date(douban_date);
                doubanList.add(douban);
            }
        }
        if (doubanList.size() > 0) {
            remoteDoubanListCallback.onSuccess(doubanList);
        } else {
            remoteDoubanListCallback.onFailure();
        }
        cursor.close();
        db.close();
    }

    @Override
    public synchronized void saveDouban(Douban douban) {
        if(isExist(douban.getDouban_id()+"")) return ;
        SQLiteDatabase db = mInSeaDbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_ID, douban.getDouban_id());
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_AUTHOR_AVATAR, douban.getDouban_author_avatar());
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_AUTHOR_NAME, douban.getDouban_author_name());
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_COMMENTS_COUNT, douban.getDouban_comments_count());
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_CREATED_TIME, douban.getDouban_created_time());
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_DATE, douban.getDouban_date());
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_HTML, douban.getDouban_html());
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_ICON, douban.getDouban_icon());
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_LIKE_COUNT, douban.getDouban_like_count());
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_TITLE, douban.getDouban_title());
        contentValues.put(DoubanPersistenContract.DoubanEntry.DOUBAN_URI, douban.getDouban_uri());

        db.insert(DoubanPersistenContract.DoubanEntry.TABLE_NAME, null, contentValues);
        db.close();
    }

    @Override
    public synchronized void saveDouban(List<Douban> doubanList) {
        for (Douban douban : doubanList) {
            saveDouban(douban);
        }
    }

    public synchronized boolean isExist(String id){
        boolean make=true;
        SQLiteDatabase db = mInSeaDbHelper.getReadableDatabase();
        Cursor cursor = db.query(DoubanPersistenContract.DoubanEntry.TABLE_NAME, null
                , DoubanPersistenContract.DoubanEntry.DOUBAN_ID + "=?", new String[]{id}, null, null, null);
        if(cursor==null||cursor.getCount()==0){
            make=false;
        }
        cursor.close();
        db.close();
        return make;
    }
}
