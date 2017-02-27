package com.example.tangzhifeng.paperairplane.data.guoke.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tangzhifeng.paperairplane.data.guoke.GuoKe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */

public class GuokeSQLDataHandle {
    List<GuoKe> mGuokeListSQL;
    GuokeSQLite mGuokeSQLite;
    GuoKe guoke;
    public GuokeSQLDataHandle(Context context){
        mGuokeSQLite = new GuokeSQLite(context,"guoke.db",null,1);
        mGuokeListSQL = new ArrayList<>();
        guoke = new GuoKe();
    }
    public List<GuoKe> getSQLiteGuoList(){
        return mGuokeListSQL;
    }
    public void CreateDataBase(){
        mGuokeSQLite.getWritableDatabase();
    }
    public void UpgradeData(){

    }
    public void addSQLiteData(GuoKe guoKe){
//        guoke = guoKe;
        SQLiteDatabase sqLiteDatabase = mGuokeSQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(GuokeSQLData.GUOKE_HEAD_IMG,guoKe.getResult().get(0).getImage());
        contentValues.put(GuokeSQLData.GUOKE_GROUP_NAME,guoKe.getResult().get(0).getGroup_name());
        contentValues.put(GuokeSQLData.GUOKE_HEAD_IMG,guoKe.getResult().get(0).getHeadline_img());
        contentValues.put(GuokeSQLData.GUOKE_ITEM_TITLE,guoKe.getResult().get(0).getItemtitle());
        contentValues.put(GuokeSQLData.GUOKE_AUTHOR,guoKe.getResult().get(0).getAuthor());
        contentValues.put(GuokeSQLData.GUOKE_LIKING_COUNT,guoKe.getResult().get(0).getLikings_count());
        contentValues.put(GuokeSQLData.GUOKE_GROUP_NAME, guoKe.getResult().get(0).getReplies_count());
        sqLiteDatabase.insert("guoke",null,contentValues);
        sqLiteDatabase.close();
    }

    public boolean selectData(GuoKe guoke){
        mGuokeListSQL.clear();
        SQLiteDatabase sqLiteDatabase = mGuokeSQLite.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("guoke",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                guoke.getResult().get(0).setImage(cursor.getString(cursor.getColumnIndex(GuokeSQLData.GUOKE_HEAD_IMG)));
                guoke.getResult().get(0).setGroup_name(cursor.getString(cursor.getColumnIndex(GuokeSQLData.GUOKE_GROUP_NAME)));
                guoke.getResult().get(0).setHeadline_img(cursor.getString(cursor.getColumnIndex(GuokeSQLData.GUOKE_HEAD_IMG)));
                guoke.getResult().get(0).setItemtitle(cursor.getString(cursor.getColumnIndex(GuokeSQLData.GUOKE_ITEM_TITLE)));
                guoke.getResult().get(0).setAuthor(cursor.getString(cursor.getColumnIndex(GuokeSQLData.GUOKE_AUTHOR)));
                guoke.getResult().get(0).setLikings_count(cursor.getInt(cursor.getColumnIndex(GuokeSQLData.GUOKE_LIKING_COUNT)));
                guoke.getResult().get(0).setReplies_count(cursor.getString(cursor.getColumnIndex(GuokeSQLData.GUOKE_REPLIES_COUNT)));
                mGuokeListSQL.add(guoke);
                Log.i("wkl", "selectData: "+mGuokeListSQL.size());
            }while (cursor.moveToNext());
            return true;
        }
        return false;
    }

}
