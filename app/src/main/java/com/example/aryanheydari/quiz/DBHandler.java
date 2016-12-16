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

        String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_PLAYERNAME + " TEXT, " + KEY_PLAYERSCORE + " TEXT, " + ")";
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

    public int getNumberofEntries() {


        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                KEY_ID,
                KEY_PLAYERNAME,
                KEY_PLAYERSCORE
        };

// Filter results WHERE "title" = 'My Title'
        String selection = KEY_ID + " = ?";
        String[] selectionArgs = { "*" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                KEY_PLAYERSCORE + " DESC";

        Cursor c = db.query(
                TABLE_PLAYERS,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return c.getCount();

    }

    public String getNextPlayer(int pos) {

        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                KEY_ID,
                KEY_PLAYERNAME,
                KEY_PLAYERSCORE
        };

// Filter results WHERE "title" = 'My Title'
        String selection = KEY_ID + " = ?";
        String[] selectionArgs = { "*" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                KEY_PLAYERSCORE + " DESC";

        Cursor c = db.query(
                TABLE_PLAYERS,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToPosition(pos);
        long dbPlayerID = c.getLong(c.getColumnIndexOrThrow(KEY_ID));
        String dbPlayerName = c.getString(c.getColumnIndexOrThrow(KEY_PLAYERNAME));
        long dbPlayerScore= c.getLong(c.getColumnIndexOrThrow(KEY_PLAYERSCORE));
        return (dbPlayerID+ "       " + dbPlayerName + "       " + dbPlayerScore);
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
//        for (Player p : scoreList) {
//            String log = "Id: " + p.getId() + " ,Name: " + p.get_PlayerName() +" ,Score: " +p.get_Score(); //according to this for loop (using Log),
//            Log.d("Name: ", log);                                                                            the Log command does not return individual names.
//        }
        return scoreList;
    }

//    public int updateScore(Player player)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_PLAYERNAME, player.get_PlayerName());
//        values.put(KEY_PLAYERSCORE, player.getScore());
//        return db.update(TABLE_PLAYERS, values, KEY_ID + "=?", new String[]{String.valueOf(player.getId())});
//    }

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
