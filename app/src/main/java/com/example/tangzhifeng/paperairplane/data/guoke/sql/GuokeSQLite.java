package com.example.tangzhifeng.paperairplane.data.guoke.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/23.
 */

public class GuokeSQLite extends SQLiteOpenHelper{
    private static final String CREATE_GUOKE = "create table guoke(" +
        "id integer primary key autoincrement,"
        +GuokeSQLData.GUOKE_GROUP_NAME+ " text,"
        +GuokeSQLData.GUOKE_HEAD_IMG+ " text,"
        +GuokeSQLData.GUOKE_IMG+" text,"
        +GuokeSQLData.GUOKE_ITEM_TITLE+" text,"
        +GuokeSQLData.GUOKE_AUTHOR+" text,"
        +GuokeSQLData.GUOKE_LIKING_COUNT+" integer,"
        +GuokeSQLData.GUOKE_REPLIES_COUNT+" text)";
    Context nContext;
    public GuokeSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        nContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_GUOKE);
        Toast.makeText(nContext, "创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists guoke");
        onCreate(sqLiteDatabase);
    }
}
