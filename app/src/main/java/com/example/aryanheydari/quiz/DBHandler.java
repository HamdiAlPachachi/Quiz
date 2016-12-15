package com.example.aryanheydari.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Leaderboard";
    public static final String TABLE_PLAYERS = "Players";
    //public static final String Table_Scores = "Player";
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

        String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " + KEY_PLAYERNAME + " TEXT, " + KEY_PLAYERSCORE + " TEXT, " + ")";
        db.execSQL(CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }

    public void addPlayer (Player player)
    {

        //Player.put(KEY_PLAYERNAME, player.getPlayerName());

        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_PLAYERS); --------- activate this code to delete all SQL data entries.
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYERNAME, player.get_PlayerName());
        values.put(KEY_PLAYERSCORE, player.get_Score());
        db.insert(TABLE_PLAYERS, null, values);
        db.close();

    }

    /*public Player getPlayerId(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Table_Scores, new String[] {
                KEY_ID,
                KEY_PLAYERNAME, KEY_PLAYERSCORE}, KEY_ID + "=?",
                new String[] { String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Player player = new Player(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        return player;

    }*/

    public List<Player> getAllPlayers()
    {
        List<Player> scoreList = new ArrayList<Player>();
        String selectQuery = "SELECT * FROM " + TABLE_PLAYERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                Player player = new Player();
                player.setId(Integer.parseInt(cursor.getString(0)));
                player.setPlayerName(cursor.getString(1));
                player.setScore(Integer.parseInt(cursor.getString(2)));

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
        values.put(KEY_PLAYERNAME, player.getPlayerName());
        values.put(KEY_PLAYERSCORE, player.getScore());
        return db.update(TABLE_PLAYERS, values, KEY_ID + "=?", new String[]{String.valueOf(player.getId())});
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
