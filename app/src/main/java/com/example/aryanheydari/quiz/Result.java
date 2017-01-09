package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.Toast;


public class Result extends Player
{

    public static final String TABLE_PLAYERS = "Players";
    public static final String TABLE_MULTIPLAYERS = "Multiplayers";

    ArrayList<String> multiPlayerResultsList = new ArrayList<String>();
    ArrayList<String> resultsList = new ArrayList<String>();
    ListView resultsListView;

    DBHandler db;

//    public static int indivTurnCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView ResultText = (TextView) findViewById(R.id.ResultText);
        TextView ScoreDisplay = (TextView) findViewById(R.id.ScoreDisplay);
        TextView NameEntry = (TextView) findViewById(R.id.NameEntry);

        if(resultsIndicator == true)
        {
            ResultText.setVisibility(View.INVISIBLE);
            ScoreDisplay.setVisibility(View.INVISIBLE);
            NameEntry.setVisibility(View.INVISIBLE);
        }

        ScoreDisplay.setText("" + Player.getTempScore()); //Displays tempScore achieved at the top of the page.

        Player player = new Player();
        NameEntry.setText(player.getUserName() + ": "); //Displays current username at the top of the page.

        Button NextPlayerButton = (Button) findViewById(R.id.NextPlayer);
        if(multiPlayer == false)
            NextPlayerButton.setVisibility(View.INVISIBLE);

        db = new DBHandler(this);

        if (multiPlayer == false)
        {
            if(resultsIndicator == false)
            {
                db.addPlayer(new Player(Player.getUserName(), Player.getTempScore())); //Adds the current username and tempScore combination to the single player table.
            }

            highScoreChecker(TABLE_PLAYERS);

            ArrayList<Player> singlePlayer = db.getSpecificPlayer();
            ArrayListConv(singlePlayer);
        }
        else
        {
            if(resultsIndicator == false)
            {
                db.addMultiplayer(new Player(Player.getUserName(), Player.getTempScore()));
            }

            //The following 3 methods call the following from DBHandler, which interacts with the database:
            highScoreChecker(TABLE_MULTIPLAYERS);

            ArrayList<Player> multiPlayer = db.getAllMultiplayers(); //1. An ArrayList of type Player containing the username and tempScore data of each attempt by all players in multiplayer mode.
            ArrayListConv(multiPlayer);
        }
    }

    public void StartAgain(View view)
    {
        Player player = new Player();
        player.setPlayerName(player.getPlayerName());

        playerTurns++;
//        indivTurnCounter++;

        Intent Q1 = new Intent(this, Q1.class);
        startActivity(Q1);
        tempScore = 0;
        setQ1Active(true);
        setQ2Active(true);
        setQ3Active(true);
        setQ4Active(true);
    }

    public void NextPlayer(View view)
    {

        playerCounter++;

        if (playerCounter >= 3)//INPUT VALIDATION
        {
            Toast.makeText(Result.this, "The maximum limit of 2 players has been reached. Please click Welcome Screen to begin a new game.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent SecondSignIN = new Intent(this, HomeActivity.class);
            startActivity(SecondSignIN);
            Player.playerTurns++; //resets the counter for the number of turns.
        }
    }

    public void WelcomeScreen(View view)
    {
        playerCounter = 1;

        db.clearMultiDataBase();

        Intent HomeActivity = new Intent(this, HomeActivity.class);
        startActivity(HomeActivity);
    }

    public void highScoreChecker(String TableName)
    {
        int maxScore = db.selectMaxScore(TableName); //The maximum tempScore achieved in a session, of type int.
        int relevantScoreCounter = db.numberOfRelevantScores(tempScore); //The number of scores in multiplayer mode that are less than the current tempScore.
        if (tempScore == maxScore && relevantScoreCounter == playerTurns) //High tempScore indicator.
        {
            Toast.makeText(Result.this, "Congratulations, new high score achieved!", Toast.LENGTH_SHORT).show();
        }
    }

    public void ArrayListConv(ArrayList<Player> Name)//Converts ArrayList of Type Player to one of type String.
    {
        for (Player p : Name)//This converts the ArrayList singlePlayer of type Player, to the ArrayList resultsList of type String.
        {
            resultsList.add(p.getUserName() + "              " + Integer.toString(p.getListScore()));
            resultsListView = (ListView) findViewById(R.id.resultsListView);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultsList);
        resultsListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(Result.this, "The back button is inactive to prevent amendments.", Toast.LENGTH_LONG).show();
    }

}