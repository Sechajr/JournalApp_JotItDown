package com.example.android.jotitdown;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;


public class DBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="JotItDown.db";
    public static final String TABLE_NAME ="Notes_table";
    public static final String Column0 ="ID";
    public static final String Column1 ="Title";
    public static final String Column2 ="Date";
    public static final String Column3 ="Notes";

    public DBase(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "Title TEXT, Date DATE, Notes TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String Title, String Date, String Notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column1,Title);
        contentValues.put(Column2, Date);
        contentValues.put(Column3,Notes);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result==-1){
            return false;
        } else {
            return true;
        }

    }

    public Cursor getTitles(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *  FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTitleId(String Title){
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + Column1 + "='" + Title + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public void editNote(String editedNote, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + Column3 + "= '" + editedNote + " ' WHERE " + Column0 + "=" + id ;
        db.execSQL(query);
    }

    public void deleteNote(int id, String Title, String Notes){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + Column0 + "=" + id  + " AND " + Column1 + "= '" + Title + "'" + " AND " + Column3 + "= '" + Notes + "'";
        db.execSQL(query);

    }
}
