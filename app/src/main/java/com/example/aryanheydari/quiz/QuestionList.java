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

        if (UserName.length() > 0 && UserName.length() <3) //This conditional statement is an input validation of the username entry.
        {
            if(UserName.matches(".*\\d+.*"))
            {
                Toast.makeText(QuestionList.this, "The name contains numbers and less than 3 characters.", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(QuestionList.this, "The name correctly excludes numbers, but contains less than 3 characters.", Toast.LENGTH_LONG).show();
            }

        }

        else if(UserName.length() >= 3)
        {
            if(UserName.matches(".*\\d+.*"))
            {
                Toast.makeText(QuestionList.this, "The name is correctly at least 3 characters long, but it contains numbers.", Toast.LENGTH_LONG).show();
            }

            else
            {
                superClass.UserName = UserName;//it return a toast asking the user to re-enter his/her name, should the name entered contain a number.

                Intent Q1 = new Intent(this, Q1.class);
                startActivity(Q1);
            }
        }

        else
        {
            Toast.makeText(QuestionList.this, "Please enter a name as per the below stated requirements.", Toast.LENGTH_LONG).show();
        }
        setQ1Active(true);
        setQ2Active(true);
        setQ3Active(true);
        setQ4Active(true);
        SuperClass.score = 0;
    }

}
