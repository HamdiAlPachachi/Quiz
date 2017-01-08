package com.example.aryanheydari.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper
{

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

    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL(DATABASE_CREATE);//Create table for single player mode.
        db.execSQL(DATABASE_CREATE_MULTI);//Create table for multiplayer mode
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        onCreate(db);
    }

    public  DBHandler open() throws SQLException
    {
        db = this.getWritableDatabase();
        return this;
    }

    //This method clears the multiplayer table, upon clicking "Welcome Screen" or "Yes" in Multiplayer screen.
    public void clearMultiDataBase()
    {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MULTIPLAYERS);
    }

    //This method scans a given table to ensure that the entered username has not already been entered.
    public boolean checkStoredName(String TableName, String fieldName, String enteredName)//Use this method in class
    {
        db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TableName + " WHERE " + fieldName + " ='" + enteredName + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() <= 0)
        {
            cursor.close();
            return false;//returns false if enteredName (ie. UserName) does not exist in the database.
        }
        else
        {
            cursor.close();
            return true;
        }
    }

    //This method enables the programme to cross-check an entered password (for a given username) with the correct password in the database.
    public String getSingleEntry(String userName)//Used to read certain username.
    {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLAYERS, null, KEY_PLAYERNAME + "=?", new String[]{userName}, null, null, null);
        if(cursor.getCount() < 1)
        {
            cursor.close();
            return "Does not exist";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(KEY_PLAYERPASSWORD));
        cursor.close();
        return password;
    }

    public void insertEntry(String userName,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(KEY_PLAYERNAME, userName);
        newValues.put(KEY_PLAYERPASSWORD, password);
        db.insert(TABLE_PLAYERS, null, newValues);
    }

    //The following 2 methods insert new quiz attempts (username and score) into respective tables by calling the values stored in the Player class.

    public void addPlayer (Player player)//For single player mode.
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYERNAME, player.get_PlayerName());
        values.put(KEY_PLAYERSCORE, player.get_Score());
        db.insert(TABLE_PLAYERS, null, values);
        db.close();

    }

    public void addMultiplayer (Player player)//For multiplayer mode.
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYERNAME, player.get_PlayerName());
        values.put(KEY_PLAYERSCORE, player.get_Score());
        db.insert(TABLE_MULTIPLAYERS, null, values);
        db.close();

    }

    //This method returns an arraylist containing all the past attempts made by the respective player in single player mode.
    public ArrayList<Player> getSpecificPlayer()
    {
        ArrayList<Player> playerList = new ArrayList<>();

        Player p = new Player();

        String Query = "SELECT * FROM " + TABLE_PLAYERS + " WHERE " + KEY_PLAYERNAME + " ='" + p.getPlayerName() + "'" + " ORDER BY " + KEY_PLAYERSCORE+ " DESC LIMIT 6";
        // In Multiplayer mode (with 2 players), a maximum of 3 attempts per player can be displayed.
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

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

    //This method returns an arraylist containing all the past attempts made by both players in multiplayer session.
    public ArrayList<Player> getAllMultiplayers()
    {
        ArrayList<Player> playerListMulti = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_MULTIPLAYERS + " ORDER BY " + KEY_PLAYERSCORE+ " DESC LIMIT 6";   //Orders player scores> Single player mode, the attempts displayed are limited to 6.
        // In Multiplayer mode (with 2 players), a maximum of 3 attempts per player can be displayed.
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())                            // looping through all rows and adding to list
        {
            do
            {
                Player player = new Player();
                player.setPlayerName(cursor.getString(1));
                player.setScore(cursor.getInt(2));          // Adding player to list
                playerListMulti.add(player);
            }

            while (cursor.moveToNext());
        }

        cursor.close();

        return playerListMulti;
    }

    //This method returns the highest achieved score in a multiplayer session.
    public int selectMaxScore(String TableName)
    {
        int highestScore;
        Player p = new Player();

        db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TableName + " WHERE " + KEY_PLAYERNAME + " ='" + p.getPlayerName() + "'" + " ORDER BY " + KEY_PLAYERSCORE+ " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst(); // looping through all rows and adding to list
        highestScore = cursor.getInt(2);
        cursor.close();
        return highestScore;
    }

    //This method returns the number of scores that are strictly less than the current score.
    public int numberOfRelevantScores(int score)
    {
        int relevantPlayerCounter = 0;

        db = this.getReadableDatabase();
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
        db = this.getWritableDatabase();
        db.close();
    }

    public void deleteTable(String TableName)
    {
        db = this.getWritableDatabase();
        db.execSQL("delete from "+ TableName);
        db.close();
    }
}
