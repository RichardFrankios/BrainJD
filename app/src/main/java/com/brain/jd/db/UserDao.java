package com.brain.jd.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.brain.jd.consts.IDbConst;
import com.brain.jd.utils.AESUtils;

import static com.brain.jd.utils.AESUtils.encrypt;

/**
 * @author : Brian
 * @date : 2017/6/23
 */

public class UserDao {

    private DbOpenHelper mDbOpenHelper;
    private final Context mContext;

    public UserDao(Context ctx) {
        mContext = ctx;
        mDbOpenHelper = new DbOpenHelper(ctx);
    }

    /**
     * 插入用户信息
     * @param username
     * @param pwd
     */
    public boolean insertUser(String username, String pwd) {
        // 1. 将数据进行加密
        try {
            username = encrypt(username);
            pwd = AESUtils.encrypt(pwd);
            SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(IDbConst.DB_ROW_USERNAME, username);
            values.put(IDbConst.DB_ROW_PWD, pwd);
            db.insert(IDbConst.DB_TABLE_USER, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取保存的用户信息
     */
    public UserInfo getUser(){
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(IDbConst.DB_TABLE_USER,
                new String[]{IDbConst.DB_ROW_USERNAME, IDbConst.DB_ROW_PWD}, null, null, null, null, null);

        if (cursor.moveToNext()) {
            String username = cursor.getString(0);
            String pwd = cursor.getString(1);

            try {
                username = AESUtils.decrypt(username);
                pwd = AESUtils.decrypt(pwd);
                return new UserInfo(username, pwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        cursor.close();
        db.close();
        return null;
    }

    /**
     * 清空用户信息
     */
    public void clearUser() {
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        db.delete(IDbConst.DB_TABLE_USER, null, null);
        db.close();
    }

    /**
     * 用户信息
     */
    public class UserInfo {
        public UserInfo(String username, String pwd) {
            this.username = username;
            this.pwd = pwd;
        }

        public String username;
        public String pwd;
    }







}
