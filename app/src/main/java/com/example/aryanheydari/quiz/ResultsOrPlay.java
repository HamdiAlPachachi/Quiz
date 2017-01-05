package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultsOrPlay extends SuperClass
{
    //This activity enables the player to view their previous scores in single player mode.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_or_play);

        Button viewPreviousResultsButton = (Button) findViewById(R.id.viewPreviousResultsButton);

        if(multiPlayer == true)
        {
            viewPreviousResultsButton.setVisibility(View.INVISIBLE);
        }
    }

    public void playButton(View view)
    {
        Intent Q1 = new Intent(this, Q1.class);
        startActivity(Q1);
        playerTurns = 0;
    }

    public void viewPreviousResultsButton(View view)
    {
        Intent Result = new Intent(this, Result.class);
        startActivity(Result);
        playerTurns = 0;
    }
}


