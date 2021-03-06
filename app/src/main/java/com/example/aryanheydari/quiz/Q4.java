package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Q4 extends Player implements NavigationView.OnNavigationItemSelectedListener
{

    boolean doubleBackPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final TextView ScoreCount = (TextView)findViewById(R.id.Score);
        ScoreCount.setText("Score: " + Player.getTempScore());

        RadioGroup Q4RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        final RadioButton CQ4 = (RadioButton) findViewById(R.id.CQ4);

        Q4RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            public void onCheckedChanged(RadioGroup Q4RadioGroup, int checkedId)
            {

            if (CQ4.isChecked())
            {
                Player.tempScore++;
                Toast.makeText(Q4.this, "Well done, 7 is the correct answer!", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(Q4.this, "Wrong answer!", Toast.LENGTH_SHORT).show();
            }
            setQ4Active(false); //This is an indicator that all radio buttons in Q4 are inactive.
            ScoreCount.setText("Score: " + Player.getTempScore());

            for (int i = 0; i < Q4RadioGroup.getChildCount(); i++)//Deactivates all radio buttons.
            {
                Q4RadioGroup.getChildAt(i).setEnabled(false);
            }

            }
        });
        if (Player.getQ4Active() == false)//Ensures that all radio buttons are deactivated if they should be.
        {
            for (int i = 0; i < Q4RadioGroup.getChildCount(); i++)
            {
                Q4RadioGroup.getChildAt(i).setEnabled(false);
            }
        }
    }

    public void NextQ4 (View view)
    {
        if (doubleBackPressed)//This if statement ensures that the client understands that he/she will be unable to amend answer after progressing to the results page.
        {
//            if(multiPlayer == false)
//            {
                Intent Result = new Intent(this, Result.class);
                startActivity(Result);
//            }
//            else
//            {
//                Intent multiResult = new Intent(this, MultiResultsPage.class);
//                startActivity(multiResult);
//            }
        }

        doubleBackPressed = true;
        Toast.makeText(this, "You will be unable to amend your responses. Please click again to proceed.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() //This resets the value of boolean doubleBackPressed to false after 2 seconds.
        {

            @Override
            public void run()
            {
                doubleBackPressed = false;
            }

        }, 2000);

    }

    public void BackQ4 (View view)
    {
        Intent Q3 = new Intent(this, Q3.class);
        startActivity(Q3);
    }

    public void CheatQ4 (View view)
    {
        Toast.makeText(Q4.this, "7 is the correct answer", Toast.LENGTH_SHORT).show();
        setQ4Active(false);

        final RadioGroup Q4RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        for (int i = 0; i < Q4RadioGroup.getChildCount(); i++) //Deactivates all radio buttons.
        {
            Q4RadioGroup.getChildAt(i).setEnabled(false);
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.List_Q1)
        {
            Intent Q1 = new Intent(this, Q1.class);
            startActivity(Q1);
        }
        else if (id == R.id.List_Q2)
        {
            Intent Q2 = new Intent(this, Q2.class);
            startActivity(Q2);
        }
        else if (id == R.id.List_Q3)
        {
            Intent Q3 = new Intent(this, Q3.class);
            startActivity(Q3);
        }
        else if (id == R.id.List_Q4)
        {
            Intent Q4 = new Intent(this, Q4.class);
            startActivity(Q4);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
