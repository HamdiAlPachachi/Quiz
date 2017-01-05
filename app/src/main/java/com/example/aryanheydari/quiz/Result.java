package com.example.aryanheydari.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collections;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


public class Result extends SuperClass { //implements NavigationView.OnNavigationItemSelectedListener {

    protected String PREFS = "Score";
    public static final String TABLE_PLAYERS = "Players";
    public static final String TABLE_MULTIPLAYERS = "Multiplayers";
    public static final String KEY_PLAYERNAME = "PlayerName";
    public static final String KEY_PLAYERSCORE = "PlayerScore";
    public static final String KEY_PLAYERPASSWORD = "Password";


    ArrayList<String> resultsList = new ArrayList<String>();
    ArrayList<String> multiPlayerResultsList = new ArrayList<String>();
    ListView resultsListView;
    ListView multiPlayerResultsListView;

    DBHandler db;

    int limit = 6;

    public static int individualTurnCounter = 0;
    Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView ScoreDisplay = (TextView) findViewById(R.id.ScoreDisplay);
        ScoreDisplay.setText("" + SuperClass.getScore());

        TextView NameEntry = (TextView) findViewById(R.id.NameEntry);

        Player player = new Player();
        NameEntry.setText(player.getPlayerName() + ": ");

        Button NextPlayerButton = (Button) findViewById(R.id.NextPlayer);

        final String TAG = "COMP211P";
        db = new DBHandler(this);

        db.addPlayer(new Player(Player.getPlayerName(), SuperClass.getScore()));

        ArrayList<Player> players = db.getAllPlayers();
        ArrayList<Player> singlePlayer = db.getSpecificPlayer();
        ArrayList<Player> multiPlayer = db.getAllMultiplayers();


        NextPlayerButton.setVisibility(View.INVISIBLE);

            for(Player p : singlePlayer)
            {
                resultsList.add(p.getPlayerName() + "              " + Integer.toString(p.get_Score()));
                resultsListView = (ListView) findViewById(R.id.resultsListView);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultsList);
            ArrayAdapter<String> adapterMulti = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, multiPlayerResultsList);

            resultsListView.setAdapter(adapter);
        }


    public void StartAgain(View view)
    {
        Player player = new Player();
        player.setPlayerName(player.get_PlayerName());

        playerTurns++;
        individualTurnCounter++;
        if(SuperClass.playerTurns <= limit/2 && individualTurnCounter >= 3 && SuperClass.multiPlayer == true)//include playerTurn parameter to account for scenario where player quits before having 3 turns. Not to print statement in case where second player has finished, as New Game statement must be printed in this case.
        {
            Toast.makeText(Result.this, "You have reached your maximum limit of 3 allowed turns per game. Please pass it to the next player.", Toast.LENGTH_LONG).show();
        }
        else if(SuperClass.playerTurns > limit/2 && individualTurnCounter >= 3 && SuperClass.multiPlayer == true)//This assumes that the first player will play at least one round.
        {
            Toast.makeText(Result.this, "You have reached your maximum limit of 2 players per game. Please press Welcome Screen and hit New Game to begin a new round.", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent Q1 = new Intent(this, Q1.class);
            startActivity(Q1);
            SuperClass.score = 0;
            setQ1Active(true);
            setQ2Active(true);
            setQ3Active(true);
            setQ4Active(true);
        }

    }

    public void WelcomeScreen(View view)
    {
        Intent HomeActivity = new Intent(this, HomeActivity.class);
        startActivity(HomeActivity);
    }

//  public void NextPlayer(View view)
//    {
//        //playerCounter++;
//
////        if(playerCounter >= 3)
////        {
////            Toast.makeText(Result.this, "The maximum limit of 2 players has been reached. Please click Welcome Screen to begin a new game.", Toast.LENGTH_SHORT).show();
////        }
//
////        else
////        {
////            Intent SecondSignIN = new Intent(this, SecondSignINScreen.class);
////            startActivity(SecondSignIN);
////            SuperClass.playerTurns++; //resets the counter for the number of turns.
////            individualTurnCounter = 0;
//
////            if(playerCounter == 2)//This changes the registration instructions to ask individual players to register. This is only triggered in multiplayer mode.
////            {
////                TextView RegistrationCommand = (TextView) findViewById(R.id.RegistrationCommand);
////                RegistrationCommand.setText("Player 2, Please Enter your Name");
////            }
////      }
//
//        String log = "Name1: " + playerCounter;// Checking database entries when testing
//        Log.d("Counter", log);
//
//    }


    @Override
    public void onBackPressed()
    {
        Toast.makeText(Result.this, "The back button is inactive to prevent amendments.", Toast.LENGTH_LONG).show();

    }

}