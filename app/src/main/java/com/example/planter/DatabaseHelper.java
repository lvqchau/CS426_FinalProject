package com.example.planter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Planter.db";
    public static final String TABLE_NAME = "registerUser";
    public static final String COLUMN_0 = "Id";
    public static final String COLUMN_1 = "Username";
    public static final String COLUMN_2 = "Fullname";
    public static final String COLUMN_3 = "Email";
    public static final String COLUMN_4 = "Password";

    public static final String TABLE2_NAME = "plantUser";
    public static final String COLUMN2_1 = "UserId";
    public static final String COLUMN2_2 = "Plant_count";
    public static final String COLUMN2_3 = "Ads_watched";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registerUser (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT NOT NULL UNIQUE, Fullname TEXT, Email TEXT NOT NULL, Password TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String user, String pass, String email, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", user);
        contentValues.put("Email", email);
        contentValues.put("Password", pass);
        contentValues.put("Fullname", name);
        long res = db.insert("registerUser", null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String user, String pass) {
        String[] columns = {COLUMN_0};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_1 + "=?" + " and " + COLUMN_4 + "=?";
        String[] selectionArgs = {user, pass};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
