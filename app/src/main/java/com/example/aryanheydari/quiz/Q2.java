package com.example.aryanheydari.quiz;

import android.content.Intent;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Q2 extends SuperClass
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final TextView ScoreCount = (TextView)findViewById(R.id.Score);

        final SuperClass score1 = new SuperClass();
        ScoreCount.setText("Score: " + SuperClass.getScore());

        RadioGroup Q2RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        final RadioButton CQ2 = (RadioButton) findViewById(R.id.CQ2);
        Q2RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup Q2RadioGroup, int checkedId) {

                if(CQ2.isChecked())
                {
                    SuperClass.score++;
                    for (int i = 0; i < Q2RadioGroup.getChildCount(); i++)
                    {
                        Q2RadioGroup.getChildAt(i).setEnabled(false);
                    }
                }
                else
                {
                    for (int i = 0; i < Q2RadioGroup.getChildCount(); i++)
                    {
                        Q2RadioGroup.getChildAt(i).setEnabled(false);
                    }
                }

                ScoreCount.setText("Score: " + SuperClass.getScore());
            }
        });
    }


    public void BackQ2 (View view){

        Intent Q1 = new Intent(this, Q1.class);
        startActivity(Q1);

    }

    public void NextQ2(View view) {

        Intent Q3 = new Intent(this, Q3.class);
        startActivity(Q3);
    }

    public void CheatQ2 (View view){

        TextView AnswerQ2 = (TextView) findViewById(R.id.AnswerQ2);
        AnswerQ2.setText("The correct answer is: 1826");

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
        getMenuInflater().inflate(R.menu.q2, menu);
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
