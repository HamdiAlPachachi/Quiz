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


public class Result extends SuperClass
{

    ArrayList<String> resultsList = new ArrayList<String>();
    ArrayList<String> multiPlayerResultsList = new ArrayList<String>();
    ListView resultsListView;

    DBHandler db;

    public static int individualTurnCounter = 0;
    Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView ScoreDisplay = (TextView) findViewById(R.id.ScoreDisplay);
        ScoreDisplay.setText("" + SuperClass.getScore()); //Displays score achieved at the top of the page.

        TextView NameEntry = (TextView) findViewById(R.id.NameEntry);
        Player player = new Player();
        NameEntry.setText(player.getPlayerName() + ": "); //Displays current username at the top of the page.

        Button NextPlayerButton = (Button) findViewById(R.id.NextPlayer);

        db = new DBHandler(this);
        db.addPlayer(new Player(Player.getPlayerName(), SuperClass.getScore())); //Adds the current username and score combination to the single player table.

        ArrayList<Player> singlePlayer = db.getSpecificPlayer();

        NextPlayerButton.setVisibility(View.INVISIBLE);

            for(Player p : singlePlayer)//This converts the ArrayList singlePlayer of type Player, to the ArrayList resultsList of type String.
            {
                resultsList.add(p.getPlayerName() + "              " + Integer.toString(p.get_Score()));
                resultsListView = (ListView) findViewById(R.id.resultsListView);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultsList);
            resultsListView.setAdapter(adapter);
        }


    public void StartAgain(View view)
    {
        Player player = new Player();
        player.setPlayerName(player.get_PlayerName());

        playerTurns++;
        individualTurnCounter++;

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
        Intent HomeActivity = new Intent(this, HomeActivity.class);
        startActivity(HomeActivity);
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(Result.this, "The back button is inactive to prevent amendments.", Toast.LENGTH_LONG).show();
    }

}