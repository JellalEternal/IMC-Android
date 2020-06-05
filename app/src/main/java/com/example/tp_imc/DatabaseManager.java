package com.example.tp_imc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Imc.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE T_Imc (" + "id integer PRIMARY KEY AUTOINCREMENT,"
                                               + "nom text," + "prenom text," + "imc double," + "date long" + ")";
        db.execSQL(strSql);
        Log.i("DATABASE", "OnCreate done");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertIMCData(String name, String firstName, double imc){
        name = name.replace("'", "''");
        firstName = firstName.replace("'", "''");
        String strSql = "INSERT INTO T_Imc (nom, prenom, imc, date) VALUES ('"
                      + name + "', '" + firstName + "', " + imc +", "+ new Date(System.currentTimeMillis()).getTime() +")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "Insert done");
    }

    public void deleteIMCHistorique(){
        SQLiteDatabase db = this.getWritableDatabase();
        String strSql = "DELETE FROM T_Imc";
        db.execSQL(strSql);
        Log.i("DATABASE", "Delete done");
    }

    public List<IMCData> getInfoIMC() {
        List<IMCData> listDataIMC = new ArrayList<>();
        String strSql = "SELECT * FROM T_Imc ORDER BY date DESC";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            IMCData dataIMC = new IMCData(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getDouble(3),new Date(cursor.getLong(4)));
            listDataIMC.add(dataIMC);
            cursor.moveToNext();
        }
        cursor.close();
        return listDataIMC;
    }
}

