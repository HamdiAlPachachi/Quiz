package com.example.aryanheydari.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aryanheydari on 10/12/2016.
 */

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LeaderBord.db";
    public static final String Table_Players = "Players";
    public static final String Table_Scores = "Player";
    public static final String KEY_ID = "_id";
    public static final String KEY_PlayerName = "PlayerName";
    public static final String KEY_PLAYER_SCORE = "PlayerScore";

    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_SCORE_TABLE = "Create Table" + Table_Scores + "(" +
                KEY_ID + " Integer Primary Key Autoincrement " +
                KEY_PlayerName + " text " +
                ");";
        db.execSQL(CREATE_SCORE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + Table_Scores);
        onCreate(db);
    }

    public void addPlayer (Player player)
    {

        //Player.put(KEY_PlayerName, player.get_PlayerName());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PlayerName, player.getPlayerName());
        //values.put(KEY_PLAYER_SCORE, player.getScore());
        db.insert(Table_Scores, null, values);
        db.close();

    }

    public Player getPlayerId(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Table_Scores, new String[] {
                KEY_ID,
                KEY_PlayerName, KEY_PLAYER_SCORE}, KEY_ID + "=?",
                new String[] { String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Player player = new Player(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        return player;

    }

    public List<Player> getAllPlayers()
    {
        List<Player> scoreList = new ArrayList<Player>();
        String selectQuery = "SELECT * FROM" + Table_Scores;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                Player player = new Player();
                player.setId(Integer.parseInt(cursor.getString(0)));
                player.setPlayerName(cursor.getString(2));

                scoreList.add(player);
            }

            while (cursor.moveToNext());
        }
        return scoreList;
    }

    public int updateScore(Player player)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PlayerName, player.getPlayerName());
        values.put(KEY_PLAYER_SCORE, player.getScore());
        return db.update(Table_Players, values, KEY_ID + "=?", new String[]{String.valueOf(player.getid())});
    }

    /*public String databaseToString(){

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
    }*/


}
