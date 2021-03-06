package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultsOrPlay extends Player
{
    //This activity enables the player to view their previous scores in single player mode.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_or_play);

        Button PrevResults = (Button) findViewById(R.id.PrevResults);

        if(multiPlayer == true)
        {
            PrevResults.setVisibility(View.INVISIBLE);
        }
    }

    public void playButton(View view)
    {
        Intent Q1 = new Intent(this, Q1.class);
        startActivity(Q1);
        playerTurns = 0;
        tempScore = 0;

        setQ1Active(true);
        setQ2Active(true);
        setQ3Active(true);
        setQ4Active(true);
    }

    public void PrevResults(View view)
    {
        Intent Result = new Intent(this, Result.class);
        startActivity(Result);
        playerTurns = 0;
        setResultsIndicator(true);
    }
}


