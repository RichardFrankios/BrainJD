package com.brain.jd.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.brain.jd.consts.IDbConst;

/**
 * 数据库帮助类
 * @author : Brian
 * @date : 2017/6/23
 */

public class DbOpenHelper extends SQLiteOpenHelper {


    private final Context mContext;

    public DbOpenHelper(Context context) {
        super(context, IDbConst.DB_NAME, null, IDbConst.DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                IDbConst.DB_TABLE_USER + "(" +
                IDbConst.DB_ROW_ID + " integer primary key autoincrement, " +
                IDbConst.DB_ROW_USERNAME + " text, " +
                IDbConst.DB_ROW_PWD + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
