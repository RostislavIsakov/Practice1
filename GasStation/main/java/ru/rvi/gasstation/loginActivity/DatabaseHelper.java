package ru.rvi.gasstation.loginActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Класс для взаимодействия с базой данных Login.db
 *
 * @author Исаков.Р, 17ит17
 */


public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table user(email text primary key, password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");
    }

    public boolean insert(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = sqLiteDatabase.insert("user", null, contentValues);
        if (ins == -1) return false;
        else return true;

    }

    public Boolean chkemail(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;

    }

    //Проверка email и password

    public Boolean emailpassword(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from user where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

}
