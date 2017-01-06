package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MultiPlayerActivity extends SuperClass
{
    //This activity prompts the user as to whether they would like to enter single or multiplayer mode. In the former case, the results would be
    // displayed in Result activity, while in the latter case, the results would appear in the MultiResultPage activity.
    //Other layout changes also occur based on the choice of mode.

    //The choice of mode alters the boolean mutliPlayer variable, which is declared and stored in SuperClass.

    DBHandler db;

    public static final String TABLE_MULTIPLAYERS = "Multiplayers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

        db = new DBHandler(this);
    }

    public void YesButton(View view)
    {
        SuperClass.multiPlayer =  true;

        db.deleteTable(TABLE_MULTIPLAYERS); //This deletes the table from the previous muliplayer session.

        playerCounter = 1; //Clicking yes does the following: 1. it resets the playerCounter to 1, which implies that the first player (in multiplayer mode) is currently playing.
        individualTurnCounter = 0; //And 2. It resets individualTurnCounter to 0, implying that no attempts have yet been made in the current session.

        Intent ResultsOrPlay = new Intent(this, ResultsOrPlay.class);
        startActivity(ResultsOrPlay);
    }

    public void NoButton(View view)
    {
        SuperClass.multiPlayer = false;
        Intent ResultsOrPlay = new Intent(this, ResultsOrPlay.class);
        startActivity(ResultsOrPlay);
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(MultiPlayerActivity.this, "The back button is inactive to prevent amendments.", Toast.LENGTH_LONG).show();
    }

}
