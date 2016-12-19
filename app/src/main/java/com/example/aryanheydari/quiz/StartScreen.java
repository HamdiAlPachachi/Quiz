package com.example.aryanheydari.quiz;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class StartScreen extends AppCompatActivity {
    String New_user_name;
    EditText Name;

    DBHandler db;

    public static final String TABLE_PLAYERS = "Players";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void Continue (View view)
    {
        Intent QuestionList = new Intent(this, QuestionList.class);
        startActivity(QuestionList);
    }

    public void NewGame(View view)
    {
        Intent QuestionList = new Intent(this, QuestionList.class);
        db = new DBHandler(this);
        db.clearDataBase();
        startActivity(QuestionList);
    }
}