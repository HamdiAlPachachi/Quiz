package com.example.aryanheydari.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aryanheydari on 10/12/2016.
 */

public class DBHandler extends SQLiteOpenHelper{

    private static final int DataBase_Version = 1;
    private static final String Database_Name = "LeaderBord.db";
    public static final String Table_Scores = "Scores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PlayerName = "PlayerName";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Database_Name, factory, DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Query = "Creat Table" + Table_Scores + "(" +
                COLUMN_ID + " Integer Primary Key Autoincrement " +
                COLUMN_PlayerName + " text " +
                ");";
        db.execSQL(Query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AddPlayer (Scores player){

        ContentValues Scores = new ContentValues();
        Scores.put(COLUMN_PlayerName, player.get_PlayerName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(Table_Scores, null, Scores);
        db.close();

    }


    public String databaseToString(){

        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Table_Scores + "where 1";
        Cursor c = db.rawQuery(Query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("PlayerName"))!= null){
                dbString += c.getString(c.getColumnIndex("PlayerName"));
                dbString += "\n";
            }
        }

        db.close();
        return dbString;
    }


}
