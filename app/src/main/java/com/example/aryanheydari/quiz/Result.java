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


public class Result extends SuperClass { //implements NavigationView.OnNavigationItemSelectedListener {

    protected String PREFS = "Score";
    public static final String TABLE_PLAYERS = "Players";

    ArrayList<String> resultsList = new ArrayList<String>();
    ListView resultsListView;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        TextView ScoreDisplay = (TextView) findViewById(R.id.ScoreDisplay);
        ScoreDisplay.setText("" + SuperClass.getScore());

        TextView NameEntry = (TextView) findViewById(R.id.NameEntry);
        NameEntry.setText(Player.getPlayerName() + ": ");

        Player player = new Player();

        final String TAG = "COMP211P";
        db = new DBHandler(this);

        db.addPlayer(new Player(Player.getPlayerName(), SuperClass.getScore()));

        //Log.d(TAG, "Reading all users...");
        ArrayList<Player> players = db.getAllPlayers();

        for (Player play : players) {
            String log = "Name: " + play.get_PlayerName() + " , Score: " + play.get_Score();
            // Writing users to log
            Log.d(TAG, log);

        }

        resultsList = new ArrayList<>();
        for (Player p : players) {
            resultsList.add(p.get_PlayerName() + "       " + Integer.toString(p.get_Score()));
            resultsListView = (ListView) findViewById(R.id.resultsListView);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultsList);
        resultsListView.setAdapter(adapter);
    }

    public void StartAgain(View view) {

        Intent QuestionList = new Intent(this, QuestionList.class);
        startActivity(QuestionList);
        SuperClass.score = 0;
        setQ1Active(true);
        setQ2Active(true);
        setQ3Active(true);
        setQ4Active(true);
    }

    public void WelcomeScreen(View view) {
        Intent WelcomeScreen = new Intent(this, StartScreen.class);
        startActivity(WelcomeScreen);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}