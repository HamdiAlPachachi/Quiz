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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Result extends SuperClass
        implements NavigationView.OnNavigationItemSelectedListener {

    protected String PREFS = "Score";

    ArrayList<String> resultsList = new ArrayList<String>();
    ListView resultsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView ScoreDisplay = (TextView) findViewById(R.id.ScoreDisplay);
        ScoreDisplay.setText("" + SuperClass.getScore());


        SharedPreferences prefs = getSharedPreferences("Player 1", MODE_PRIVATE);
        String text1= prefs.getString("name", "Player1: ");
        int text2= prefs.getInt("score", SuperClass.getScore());
        TextView LeaderBoard = (TextView) findViewById(R.id.LeaderBoard);
        LeaderBoard.setText(text1 + text2);

        DBHandler db = new DBHandler(this);

        QuestionList player = new QuestionList();

        db.addPlayer(new Player(player.New_user_name, SuperClass.score));

        List<Player> playerList = db.getAllPlayers();
        //loadScore();
        resultsList = new ArrayList<>();
        for (Player p : playerList) {
            resultsList.add(p.getPlayerName() + "   " + Integer.toString(score));
        }

        resultsListView = (ListView) findViewById(R.id.resultsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultsList);
        resultsListView.setAdapter(adapter);

        //saveScore();*/

    }

    /*public void saveScore()
    {
        SharedPreferences scoreList = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = scoreList.edit();

        Set<String> set = new HashSet<>();
        set.addAll(resultsList);
        //for (String s: set)
          //  Log.e("tag", s);
        editor.putStringSet("savedScore", set);//can add .putInt("savedPreviousScore", score); for more variables.
        editor.commit();
    }

    public void loadScore()
    {
        SharedPreferences scoreList = getSharedPreferences(PREFS, Context.MODE_PRIVATE);


        Set<String> set = new HashSet<>();
        set = scoreList.getStringSet("savedScore", null);//issue is that getStringSet is not a method in SharedPreferences library. Must find way of retreiving all values fomr an ArrayList.
        resultsList.addAll(set);
    }*/


    public void StartAgain (View view)
    {

        Intent QuestionList = new Intent(this, QuestionList.class);
        startActivity(QuestionList);
        SuperClass.score = 0;
        setQ1Active(true);
        setQ2Active(true);
        setQ3Active(true);
        setQ4Active(true);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.List_Q1) {
            // Handle the camera action
        } else if (id == R.id.List_Q2) {

        } else if (id == R.id.List_Q3) {

        } else if (id == R.id.List_Q4) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
