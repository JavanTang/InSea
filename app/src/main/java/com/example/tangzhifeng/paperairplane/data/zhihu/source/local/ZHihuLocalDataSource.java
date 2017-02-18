package com.example.tangzhifeng.paperairplane.data.zhihu.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateSource;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class ZHihuLocalDataSource implements ZhihuDateSource {
    private static ZHihuLocalDataSource sZHihuLocalDataSource;
    private InSeaDbHelper mInSeaDbHelper;

    public static final String TAG = "tzf";

    private ZHihuLocalDataSource(Context context) {
        mInSeaDbHelper = new InSeaDbHelper(context);
    }

    public static ZHihuLocalDataSource getInstance(Context context) {
        if (sZHihuLocalDataSource == null) {
            sZHihuLocalDataSource = new ZHihuLocalDataSource(context);
        }
        return sZHihuLocalDataSource;
    }


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
        SQLiteDatabase db = mInSeaDbHelper.getReadableDatabase();

        Cursor c = db.query(ZhihuPersistencContract.ZhihuEntry.TABLE_NAME, null
                , ZhihuPersistencContract.ZhihuEntry.ZHIHU_ID + "=?", new String[]{id}, null, null, null
        );

        if (c != null && c.getCount() > 0) {
            c.moveToNext();
            String ZHihu_id = id;
            String Zhihu_title = c.getString(c.getColumnIndexOrThrow(ZhihuPersistencContract.ZhihuEntry.ZHIHU_TITLE));
            String Zhihu_img = c.getString(c.getColumnIndexOrThrow(ZhihuPersistencContract.ZhihuEntry.ZHIHU_TITLE_IMG));
            String Zhihu_body = c.getString(c.getColumnIndexOrThrow(ZhihuPersistencContract.ZhihuEntry.ZHIHU_BODY));
            String Zhihu_smallImg = c.getString(c.getColumnIndexOrThrow(ZhihuPersistencContract.ZhihuEntry.ZHIHU_SMALL_IMG));

            ZhiHu zhiHu = new ZhiHu();
            zhiHu.setBody(Zhihu_body);
            zhiHu.setTitle(Zhihu_title);
            zhiHu.setImage(Zhihu_img);
            zhiHu.setId(Integer.valueOf(id));
            getZhiHuCallback.onZhiHuLoaded(zhiHu);

        } else {
            getZhiHuCallback.onZhiHuObtainFailure();
        }

        if (c != null) {
            c.close();
        }
        db.close();

    }

    @Override
    public void saveZhihu(ZhiHu zhiHu) {
        Log.i(TAG, "id=" + zhiHu.getId() + ",开始保存!", new Exception());
        SQLiteDatabase db = mInSeaDbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ZhihuPersistencContract.ZhihuEntry.ZHIHU_ID, zhiHu.getId());
        contentValues.put(ZhihuPersistencContract.ZhihuEntry.ZHIHU_BODY, zhiHu.getBody());
        contentValues.put(ZhihuPersistencContract.ZhihuEntry.ZHIHU_TITLE, zhiHu.getTitle());
        contentValues.put(ZhihuPersistencContract.ZhihuEntry.ZHIHU_TITLE_IMG, zhiHu.getImage());
        if (zhiHu.getImages() == null) {
            contentValues.put(ZhihuPersistencContract.ZhihuEntry.ZHIHU_SMALL_IMG, zhiHu.getImage());
        } else
            contentValues.put(ZhihuPersistencContract.ZhihuEntry.ZHIHU_SMALL_IMG, zhiHu.getImages().get(0));
        db.insert(ZhihuPersistencContract.ZhihuEntry.TABLE_NAME, null, contentValues);
        db.close();
        Log.i(TAG, "id=" + zhiHu.getId() + ",保存完毕!");
    }


    @Override
    public void deleteZhiHu(ZhiHu zhiHu) {

    }

    @Override
    public void deleteZhiHu(String id) {

    }
}
