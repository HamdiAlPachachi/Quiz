package com.example.aryanheydari.quiz;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class QuestionList extends SuperClass {

    EditText Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        Name = (EditText) findViewById(R.id.Name);

    }

    public void Start (View view)
    {
        SuperClass superClass = new SuperClass();
        String UserName = Name.getText().toString();
        superClass.UserName = UserName;
        Intent Q1 = new Intent(this, Q1.class);
        startActivity(Q1);
    }

}
