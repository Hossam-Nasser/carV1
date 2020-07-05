package com.example.car;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.util.ArrayList;

public class MyDatabase extends SQLiteAssetHelper {
    public final static String DB_name  = "cars.db.zip";
    public final static int DB_version = 2;



    public final static String CAR_CLN_NAME ="cars";
    public final static String CAR_CLN_ID ="id";
    public final static String CAR_CLN_MODEL ="model";
    public final static String CAR_CLN_COLOR ="color";
    public final static String CAR_CLN_Description ="description";
    public final static String CAR_CLN_IMAGE ="image";
    public final static String CAR_CLN_Dpl ="distancePerLetter";

    public MyDatabase(Context context) {
        super(context, DB_name,null, DB_version);

    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE "+CAR_CLN_NAME +" ("+CAR_CLN_ID+" INTERGER PRIMARY KEY AUTOINCREMENT," +
//                ""+CAR_CLN_MODEL+" TEXT,"+CAR_CLN_COLOR+" TEXT, "+CAR_CLN_Description+" TEXT, "+CAR_CLN_IMAGE+" TEXT, "+CAR_CLN_Dpl+" REAL)");
//    }

    //upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + CAR_CLN_NAME);
        onCreate(db);
    }




}
