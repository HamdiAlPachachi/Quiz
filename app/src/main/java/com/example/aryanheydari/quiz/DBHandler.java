package com.example.aryanheydari.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Leaderboard4";
    public static final String TABLE_PLAYERS = "Players";
    public static final String TABLE_MULTIPLAYERS = "Multiplayers";
    public static final String KEY_ID = "id";
    public static final String KEY_PLAYERNAME = "PlayerName";
    public static final String KEY_PLAYERSCORE = "PlayerScore";
    public static final String KEY_PLAYERPASSWORD = "Password";

    static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_PLAYERS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_PLAYERNAME + " TEXT, " + KEY_PLAYERSCORE + " TEXT, " + KEY_PLAYERPASSWORD + " TEXT" +")";
    static final String DATABASE_CREATE_MULTI = "CREATE TABLE " + TABLE_MULTIPLAYERS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_PLAYERNAME + " TEXT, " + KEY_PLAYERSCORE + " TEXT, " + KEY_PLAYERPASSWORD + " TEXT" +")";

    public  SQLiteDatabase db;
    private DBHandler dbHelper;

    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);//Create table for single player.
        db.execSQL(DATABASE_CREATE_MULTI);//Create table for multiplayer

//        String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_PLAYERNAME + " TEXT, " + KEY_PLAYERSCORE + " TEXT" + ")";
//        db.execSQL(CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
//        onCreate(db);

        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(db);
    }

    public  DBHandler open() throws SQLException
    {
        db = this.getWritableDatabase();
        return this;
    }


    public void clearDataBase()
    {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYERS);
    }

//    public boolean checkStoredName(String TableName, String fieldName, String enteredName)//Use this method in class
//    {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String Query = "SELECT * FROM " + TableName + " WHERE " + fieldName + " ='" + enteredName + "'";
//        Cursor cursor = db.rawQuery(Query, null);
//        if(cursor.getCount() <= 0)
//        {
//            cursor.close();
//            return false;//returns false if entered name (UserName) exists in the database.
//        }
//        else
//        {
//            cursor.close();
//            return true;
//        }
//    }

    public String getSingleEntry(String userName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLAYERS, null, KEY_PLAYERNAME+"=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(KEY_PLAYERPASSWORD));
        cursor.close();
        return password;
    }

    public void insertEntry(String userName,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(KEY_PLAYERNAME, userName);
        newValues.put(KEY_PLAYERPASSWORD, password);

        // Insert the row into your table
        db.insert(TABLE_PLAYERS, null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public void addPlayer (Player player)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYERNAME, player.get_PlayerName());
        values.put(KEY_PLAYERSCORE, player.get_Score());
        db.insert(TABLE_PLAYERS, null, values);
        db.close();

    }

    public void addMultiplayer (Player player)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYERNAME, player.get_PlayerName());
        values.put(KEY_PLAYERSCORE, player.get_Score());
        db.insert(TABLE_MULTIPLAYERS, null, values);
        db.close();

    }

    public ArrayList<Player> getSpecificPlayer() {
        ArrayList<Player> playerList = new ArrayList<>();

        Player p = new Player();

        String Query = "SELECT * FROM " + TABLE_PLAYERS + " WHERE " + KEY_PLAYERNAME + " ='" + p.getPlayerName() + "'" + " ORDER BY " + KEY_PLAYERSCORE+ " DESC LIMIT 6";
        // In Multiplayer mode (with 2 players), a maximum of 3 attempts per player can be displayed.
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        String log = "List: " + p.getPlayerName();// Ckecking database entries when testing
        Log.d("List", log);


        if (cursor.moveToFirst()) // looping through all rows and adding to list
        {
            do
            {
                Player player = new Player();
                player.setPlayerName(cursor.getString(1));
                player.setScore(cursor.getInt(2));// Adding player to list
                playerList.add(player);
            }

            while (cursor.moveToNext());
        }

        cursor.close();
        return playerList;
    }


    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> playerListSingle = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PLAYERS + " ORDER BY " + KEY_PLAYERSCORE+ " DESC LIMIT 6";   //Orders player scores> Single player mode, the attempts displayed are limited to 6.
                                                                                                                  // In Multiplayer mode (with 2 players), a maximum of 3 attempts per player can be displayed.
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) // looping through all rows and adding to list
        {
            do
            {
                Player player = new Player();
                player.setPlayerName(cursor.getString(1));
                player.setScore(cursor.getInt(2));// Adding player to list
                playerListSingle.add(player);
            }
            
            while (cursor.moveToNext());
        }

        cursor.close();
        String log = "List in dbhandler: " + playerListSingle;// Ckecking database entries when testing
        Log.d("List", log);
        return playerListSingle;
    }

    public ArrayList<Player> getAllMultiplayers() {
        ArrayList<Player> playerListMulti = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_MULTIPLAYERS + " ORDER BY " + KEY_PLAYERSCORE+ " DESC LIMIT 6";   //Orders player scores> Single player mode, the attempts displayed are limited to 6.
        // In Multiplayer mode (with 2 players), a maximum of 3 attempts per player can be displayed.
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) // looping through all rows and adding to list
        {
            do
            {
                Player player = new Player();
                player.setPlayerName(cursor.getString(1));
                player.setScore(cursor.getInt(2));// Adding player to list
                playerListMulti.add(player);
            }

            while (cursor.moveToNext());
        }

        cursor.close();

        return playerListMulti;
    }

    public int selectMaxScore()//not working
    {
        int highestScore;

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_MULTIPLAYERS + " ORDER BY " + KEY_PLAYERSCORE+ " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst(); // looping through all rows and adding to list
        highestScore = cursor.getInt(2);

        cursor.close();

        return highestScore;
    }

    public int numberOfRelevantScores(int score)
    {
        int relevantPlayerCounter = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_MULTIPLAYERS + " WHERE " + KEY_PLAYERSCORE + " < '" + score + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) // looping through all rows and adding to list
        {
            do
            {
                relevantPlayerCounter++;
            }

            while (cursor.moveToNext());
        }

        cursor.close();

        return relevantPlayerCounter;
    }

    public void close()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.close();
    }

    public void deleteTable(String TableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TableName);
        db.close();
    }

}
