package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.os.Bundle;
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

public class Q3 extends Player implements NavigationView.OnNavigationItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final TextView ScoreCount = (TextView)findViewById(R.id.Score);
        ScoreCount.setText("Score: " + Player.getTempScore());

        RadioGroup Q3RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        final RadioButton DQ3 = (RadioButton) findViewById(R.id.DQ3);

        Q3RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            public void onCheckedChanged(RadioGroup Q3RadioGroup, int checkedId)
            {

                if (DQ3.isChecked())
                {
                    Player.tempScore++;
                    Toast.makeText(Q3.this, "Well done, 2 is the correct answer!", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(Q3.this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                }

                setQ3Active(false);//This is an indicator that all radio buttons in Q3 are inactive.
                ScoreCount.setText("Score: " + Player.getTempScore());

                for (int i = 0; i < Q3RadioGroup.getChildCount(); i++) //Deactivates all radio buttons.
                {
                    Q3RadioGroup.getChildAt(i).setEnabled(false);
                }

            }
        });
        if (Player.getQ3Active() == false) //Ensures that all radio buttons are deactivated if they should be.
        {
            for (int i = 0; i < Q3RadioGroup.getChildCount(); i++)
            {
                Q3RadioGroup.getChildAt(i).setEnabled(false);
            }
        }
    }

    public void BackQ3 (View view)
    {
        Intent Q2 = new Intent(this, Q2.class);
        startActivity(Q2);
    }

    public void NextQ3(View view)
    {
        Intent Q4 = new Intent(this, Q4.class);
        startActivity(Q4);
    }

    public void CheatQ3 (View view)
    {
        Toast.makeText(Q3.this, "2 is the correct answer", Toast.LENGTH_SHORT).show();
        setQ3Active(false);

        final RadioGroup Q3RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        for (int i = 0; i < Q3RadioGroup.getChildCount(); i++)//Deactivates all radio buttons.
        {
            Q3RadioGroup.getChildAt(i).setEnabled(false);
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
