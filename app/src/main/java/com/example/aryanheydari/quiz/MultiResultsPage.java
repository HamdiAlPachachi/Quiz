package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MultiResultsPage extends SuperClass {

    ArrayList<String> resultsList = new ArrayList<String>();
    ArrayList<String> multiPlayerResultsList = new ArrayList<String>();
    ListView resultsListView;
    ListView multiPlayerResultsListView;

    DBHandler db;

    int limit = 6;

    public static int individualTurnCounter = 0;
    Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        TextView ScoreDisplay = (TextView) findViewById(R.id.ScoreDisplay);
        ScoreDisplay.setText("" + SuperClass.getScore());

        TextView NameEntry = (TextView) findViewById(R.id.NameEntry);

        Player player = new Player();
        NameEntry.setText(player.getPlayerName() + ": ");

//        String log = "Strange: " + player.get_PlayerName();// Checking database entries when testing
//        Log.d("Counter", log);

        Button NextPlayerButton = (Button) findViewById(R.id.NextPlayer);

        final String TAG = "COMP211P";
        db = new DBHandler(this);

        db.addMultiplayer(new Player(SuperClass.getPlayerName(), SuperClass.getScore()));

        ArrayList<Player> players = db.getAllPlayers();
        ArrayList<Player> singlePlayer = db.getSpecificPlayer();
        ArrayList<Player> multiPlayer = db.getAllMultiplayers();

        int maxScore = db.selectMaxScore();
        int relevantScoreCounter = db.numberOfRelevantScores(score);

        if(score == maxScore && relevantScoreCounter == playerTurns) //high score indicator.
        {
            Toast.makeText(MultiResultsPage.this, "Congratulations, new high score achieved!", Toast.LENGTH_SHORT).show();
        }

        NextPlayerButton.setVisibility(View.VISIBLE);


//            String log = "List in results: " + multiPlayer;// Ckecking database entries when testing
//            Log.d("List", log);

//            for(Player p : players)
//            {
//                String log = "Name: " + players;// Ckecking database entries when testing
//                Log.d("List", log);
//            }

        resultsList = new ArrayList<>();
        for (Player p : multiPlayer) {
            multiPlayerResultsList.add(p.get_PlayerName() + "           " + Integer.toString(p.get_Score()));
            resultsListView = (ListView) findViewById(R.id.resultsListView);
        }
//
        String log = "Name: " + multiPlayer;// Ckecking database entries when testing
        Log.d("List", log);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, multiPlayerResultsList);
        resultsListView.setAdapter(adapter);
    }

    public void StartAgain(View view)
    {
        Player player = new Player();
        player.setPlayerName(player.get_PlayerName());

        playerTurns++;

//        individualTurnCounter++;

//        if(playerCounter >= 3 && SuperClass.multiPlayer == true)//This assumes that the first player will play at least one round.
//        {
//            Toast.makeText(MultiResultsPage.this, "You have reached your maximum limit of 2 players per game. Please press Welcome Screen and hit New Game to begin a new round.", Toast.LENGTH_LONG).show();
//        }
//        else
//        {
            Intent Q1 = new Intent(this, Q1.class);
            startActivity(Q1);
            SuperClass.score = 0;
            setQ1Active(true);
            setQ2Active(true);
            setQ3Active(true);
            setQ4Active(true);
        //}

    }

    public void WelcomeScreen(View view)
    {
        playerCounter = 1;

        Intent HomeActivity = new Intent(this, HomeActivity.class);
        startActivity(HomeActivity);
    }

    public void NextPlayer(View view) {

        playerCounter++;
//        individualTurnCounter = 0;


        if (playerCounter >= 3) {
            Toast.makeText(MultiResultsPage.this, "The maximum limit of 2 players has been reached. Please click Welcome Screen to begin a new game.", Toast.LENGTH_SHORT).show();
        } else {
            Intent SecondSignIN = new Intent(this, HomeActivity.class);
            startActivity(SecondSignIN);
            SuperClass.playerTurns++; //resets the counter for the number of turns.
            individualTurnCounter = 0;

//            if(playerCounter == 2)//This changes the registration instructions to ask individual players to register. This is only triggered in multiplayer mode.
//            {
//                TextView RegistrationCommand = (TextView) findViewById(R.id.RegistrationCommand);
//                RegistrationCommand.setText("Player 2, Please Enter your Name");
//            }
            //}

//        String log = "Name: " + playerCounter;// Checking database entries when testing
//        Log.d("Counter", log);

        }

    }

        @Override
        public void onBackPressed ()
        {
            Toast.makeText(MultiResultsPage.this, "The back button is inactive to prevent amendments.", Toast.LENGTH_LONG).show();

        }

}
