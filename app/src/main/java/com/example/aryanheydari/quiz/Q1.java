package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Q1 extends SuperClass
        implements NavigationView.OnNavigationItemSelectedListener {

    RadioGroup Q1RadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RadioGroup Q1RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final RadioButton DQ1 = (RadioButton) findViewById(R.id.DQ1);

        final TextView ScoreCount = (TextView) findViewById(R.id.Score);

        ScoreCount.setText("Score: " + SuperClass.getScore());


        Q1RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup Q1RadioGroup, int checkedId) {


                if (DQ1.isChecked())
                {
                    SuperClass.score++;
                    Toast.makeText(Q1.this, "Well done, 1826 is the correct answer!", Toast.LENGTH_SHORT).show();

                } else
                {
                    Toast.makeText(Q1.this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                }

                setQ1Active(false);
                ScoreCount.setText("Score: " + SuperClass.getScore());

                for (int i = 0; i < Q1RadioGroup.getChildCount(); i++) {
                    Q1RadioGroup.getChildAt(i).setEnabled(false);
                }
            }

        });

        if (SuperClass.getQ1Active() == false)
        {
            for (int i = 0; i < Q1RadioGroup.getChildCount(); i++) {
                Q1RadioGroup.getChildAt(i).setEnabled(false);
            }
        }

    }

    public void NextQ1 (View view){

        Intent Q2 = new Intent(this, Q2.class);
        startActivity(Q2);

    }

    public void CheatQ1 (View view){

        Toast.makeText(Q1.this, "1826 is the correct answer", Toast.LENGTH_SHORT).show();
        setQ1Active(false);

        final RadioGroup Q1RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        for (int i = 0; i < Q1RadioGroup.getChildCount(); i++)
        {
            Q1RadioGroup.getChildAt(i).setEnabled(false);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
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

    @Override
    public void onBackPressed(){
        Toast.makeText(Q1.this, "The back button for Question 1 is inactive to prevent cheating.", Toast.LENGTH_LONG).show();
    }
}
