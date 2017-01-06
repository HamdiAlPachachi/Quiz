package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MultiResultsPage extends SuperClass
{
    public static final String TABLE_MULTIPLAYERS = "Multiplayers";

    ArrayList<String> multiPlayerResultsList = new ArrayList<String>();
    ListView resultsListView;

    DBHandler db;

    public static int individualTurnCounter = 0;
    Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView ScoreDisplay = (TextView) findViewById(R.id.ScoreDisplay);
        TextView NameEntry = (TextView) findViewById(R.id.NameEntry);
        ScoreDisplay.setText("" + SuperClass.getScore()); //This displays the score achieved.
        NameEntry.setText(player.getPlayerName() + ": ");

        Button NextPlayerButton = (Button) findViewById(R.id.NextPlayer);
        NextPlayerButton.setVisibility(View.VISIBLE);

        db = new DBHandler(this);
        db.addMultiplayer(new Player(SuperClass.getPlayerName(), SuperClass.getScore()));

        //The following 3 methods call the following from DBHandler, which interacts with the database:
        ArrayList<Player> multiPlayer = db.getAllMultiplayers(); //1. An ArrayList of type Player containing the username and score data of each attempt by all players in multiplayer mode.
        int maxScore = db.selectMaxScore(TABLE_MULTIPLAYERS); //2. The maziximum score achieved in a session, of type int.
        int relevantScoreCounter = db.numberOfRelevantScores(score); //The number of scores in multiplayer mode that are less than the current score.

        if(score == maxScore && relevantScoreCounter == playerTurns) //High score indicator.
        {
            Toast.makeText(MultiResultsPage.this, "Congratulations, new high score achieved!", Toast.LENGTH_SHORT).show();
        }

        for (Player p : multiPlayer)// This for loop transforms the multiPlayer ArrayList into an ArrayList of type String, from type Player.
        {
            multiPlayerResultsList.add(p.get_PlayerName() + "           " + Integer.toString(p.get_Score()));
            resultsListView = (ListView) findViewById(R.id.resultsListView);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, multiPlayerResultsList);
        resultsListView.setAdapter(adapter);
    }

    public void StartAgain(View view)
    {
        Player player = new Player();
        player.setPlayerName(player.get_PlayerName());

        playerTurns++;

        Intent Q1 = new Intent(this, Q1.class);
        startActivity(Q1);
        SuperClass.score = 0;
        setQ1Active(true);
        setQ2Active(true);
        setQ3Active(true);
        setQ4Active(true);

    }

    public void WelcomeScreen(View view)
    {
        playerCounter = 1;

        db.clearMultiDataBase();

        Intent HomeActivity = new Intent(this, HomeActivity.class);
        startActivity(HomeActivity);
    }

    public void NextPlayer(View view)
    {

        playerCounter++;

        if (playerCounter >= 3)//INPUT VALIDATION
        {
            Toast.makeText(MultiResultsPage.this, "The maximum limit of 2 players has been reached. Please click Welcome Screen to begin a new game.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent SecondSignIN = new Intent(this, HomeActivity.class);
            startActivity(SecondSignIN);
            SuperClass.playerTurns++; //resets the counter for the number of turns.
            individualTurnCounter = 0;
        }
    }

        @Override
        public void onBackPressed ()
        {
            Toast.makeText(MultiResultsPage.this, "The back button is inactive to prevent amendments.", Toast.LENGTH_LONG).show();
        }

}
