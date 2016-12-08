package com.example.aryanheydari.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;



public class Q1 extends SuperClass
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final RadioButton AQ1 = (RadioButton) findViewById(R.id.AQ1);
        final RadioButton BQ1 = (RadioButton) findViewById(R.id.BQ1);
        final RadioButton CQ1 = (RadioButton) findViewById(R.id.CQ1);
        final RadioButton DQ1 = (RadioButton) findViewById(R.id.DQ1);

        final TextView ScoreCount = (TextView)findViewById(R.id.Score);

        ScoreCount.setText("Score: " + SuperClass.getScore());

        RadioGroup Q1RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        Q1RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup Q1RadioGroup, int checkedId) {


                if(CQ1.isChecked())
                {
                    SuperClass.score++;
                    for (int i = 0; i < Q1RadioGroup.getChildCount(); i++)
                    {
                        Q1RadioGroup.getChildAt(i).setEnabled(false);
                    }
                }
                else
                {
                    for (int i = 0; i < Q1RadioGroup.getChildCount(); i++)
                    {
                        Q1RadioGroup.getChildAt(i).setEnabled(false);
                    }
                }

                ScoreCount.setText("Score: " + SuperClass.getScore());

            }
        });
    }


    public void NextQ1 (View view){

        Intent Q2 = new Intent(this, Q2.class);
        startActivity(Q2);

    }

    public void BackQ1 (View view){

        Intent QuestionList = new Intent(this, QuestionList.class);
        startActivity(QuestionList);

    }



    public void CheatQ1 (View view){

        TextView AnswerQ1 = (TextView) findViewById(R.id.AnswerQ1);
        AnswerQ1.setText("The correct answer is: 1826");

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.q1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
}
