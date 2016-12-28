package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MultiPlayerActivity extends SuperClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

    }


    public void YesButton(View view)
    {
        SuperClass.multiPlayer =  true;
        Intent QuestionList = new Intent(this, QuestionList.class);
        startActivity(QuestionList);
    }

    public void NoButton(View view)
    {
        SuperClass.multiPlayer = false;
        Intent QuestionList = new Intent(this, QuestionList.class);
        startActivity(QuestionList);
    }

}
