package com.example.aryanheydari.quiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
