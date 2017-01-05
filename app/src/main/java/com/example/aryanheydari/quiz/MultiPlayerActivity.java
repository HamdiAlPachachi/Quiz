package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MultiPlayerActivity extends SuperClass {

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

        db.deleteTable(TABLE_MULTIPLAYERS);

        playerCounter = 1;
        individualTurnCounter = 0;

        Intent ResultsOrPlay = new Intent(this, ResultsOrPlay.class);
        startActivity(ResultsOrPlay);
    }

    public void NoButton(View view)
    {
        SuperClass.multiPlayer = false;
        Intent ResultsOrPlay = new Intent(this, ResultsOrPlay.class);
        startActivity(ResultsOrPlay);
    }

}
