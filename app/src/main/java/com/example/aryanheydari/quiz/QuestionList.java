package com.example.aryanheydari.quiz;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

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

        if (UserName.matches("[a-zA-Z]+")) //This conditional statement is an input validation of the username entry.
        {
            superClass.UserName = UserName;//it return a toast asking the user to re-enter his/her name, should the name entered contain a number.

            Intent Q1 = new Intent(this, Q1.class);
            startActivity(Q1);
        }
        else
        {
            Toast.makeText(QuestionList.this, "Please enter a valid name.", Toast.LENGTH_SHORT).show();
        }
        setQ1Active(true);
        setQ2Active(true);
        setQ3Active(true);
        setQ4Active(true);
        SuperClass.score = 0;


    }

}
