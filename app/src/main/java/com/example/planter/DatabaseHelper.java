package com.example.planter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "hello";

    public static final String DATABASE_NAME = "Planter.db";
    public static final String TABLE_NAME = "registerUser";
    public static final String COLUMN_0 = "Id";
    public static final String COLUMN_1 = "Username";
    public static final String COLUMN_2 = "Firstname";
    public static final String COLUMN_3 = "Lastname";
    public static final String COLUMN_4 = "Email";
    public static final String COLUMN_5 = "Password";

    public static final String TABLE2_NAME = "plantUser";
    public static final String COLUMN2_0 = "Id";
    public static final String COLUMN2_1 = "Username";
    public static final String COLUMN2_2 = "Plant_count";
    public static final String COLUMN2_3 = "Ads_watched";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registerUser (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT NOT NULL UNIQUE, Firstname TEXT, Lastname TEXT, Email TEXT NOT NULL, Password TEXT NOT NULL)");
        db.execSQL("CREATE TABLE plantUser (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT NOT NULL UNIQUE, Plant_count DEFAULT 0, Ads_watched DEFAULT 0, FOREIGN KEY (Username) REFERENCES registerUser(Username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE2_NAME);
        onCreate(db);
    }

    public long addUser(String user, String pass, String email, String first, String last) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", user);
        contentValues.put("Email", email);
        contentValues.put("Firstname", first);
        contentValues.put("Lastname", last);
        contentValues.put("Password", pass);
        long res = db.insert("registerUser", null, contentValues);
        db.close();
        return res;
    }

    public long addPlantUser(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", user);
        long res = db.insert("plantUser", null, contentValues);
        db.close();
        return res;
    }

    public void updatePlantCount(String user, int plant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Plant_count", plant);
        db.update("plantUser", contentValues, "Username = '" + user + "'", null);
    }

    public void updateAdsWatched(String user, int ad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Ads_watched", ad);
        db.update("plantUser", contentValues, "Username = '" + user + "'", null);
    }

    public boolean checkUser(String user, String pass) {
        String[] columns = {COLUMN_0};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_1 + "=?" + " and " + COLUMN_5 + "=?";
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

    public void listUsers(TextView textView) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
        textView.setText("");
        while (cursor.moveToNext()) {
            textView.append(cursor.getString(1) + " " + cursor.getString(2) + cursor.getString(3) + cursor.getString(4) + "\n");
        }
    }

    public int getAdsWatched(String user) {
        String res = "";
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE2_NAME + " WHERE Username = '" + user + "'", null);
        while (cursor.moveToNext()) {
            res = cursor.getString(3);
        }
        cursor.close();
        return Integer.parseInt(res);
    }

    public int getPlantCount(String user) {
        String res = "";
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE2_NAME + " WHERE Username = '" + user + "'", null);
        while (cursor.moveToNext()) {
            res = cursor.getString(2);
        }
        cursor.close();
        return Integer.parseInt(res);
    }

    public void listPlantUsers(TextView textView) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE2_NAME, null);
        textView.setText("");
        while (cursor.moveToNext()) {
            textView.append(cursor.getString(1) + " " + cursor.getString(2) + cursor.getString(3) + "\n");
        }
    }
}
