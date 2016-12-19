package com.example.aryanheydari.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Leaderboard";
    public static final String TABLE_PLAYERS = "Players";
    public static final String KEY_ID = "id";
    public static final String KEY_PLAYERNAME = "PlayerName";
    public static final String KEY_PLAYERSCORE = "PlayerScore";

    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_PLAYERNAME + " TEXT, " + KEY_PLAYERSCORE + " TEXT" + ")";
        db.execSQL(CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }

    public void clearDataBase()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYERS);
    }

    public void addPlayer (Player player)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_PLAYERS); //--------- activate this code to delete all SQL data entries.
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYERNAME, player.get_PlayerName());
        values.put(KEY_PLAYERSCORE, player.get_Score());
        db.insert(TABLE_PLAYERS, null, values);
        db.close();

    }


    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PLAYERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setPlayerName(cursor.getString(1));
                player.setScore(cursor.getInt(2));
                // Adding contact to list
                playerList.add(player);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return playerList;
    }

//    public int updateScore(Player player)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_PLAYERNAME, player.get_PlayerName());
//        values.put(KEY_PLAYERSCORE, player.getScore());
//        return db.update(TABLE_PLAYERS, values, KEY_ID + "=?", new String[]{String.valueOf(player.getId())});
//    }

}
