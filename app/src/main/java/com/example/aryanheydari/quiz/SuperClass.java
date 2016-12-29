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

public class SuperClass extends AppCompatActivity
{

    public static int score = 0;

    public static int playerTurns = 0;//This counter measures the TOTAL numbers of quizzes attempted by (both) player(s).
    public static int individualTurnCounter = 0;//This counter measures the number of quizzes attempted by EACH player.
    public static int playerCounter = 1;//This counter measures the number of players.

    public static String UserName;

    public static boolean multiPlayer;

    private static boolean Q1Active = true, Q2Active = true, Q3Active = true, Q4Active = true;

    public void setQ1Active(boolean activity)
    {
        this.Q1Active = activity;
    }

    public void setQ2Active(boolean activity)
    {
        this.Q2Active = activity;
    }

    public void setQ3Active(boolean activity)
    {
        this.Q3Active = activity;
    }

    public void setQ4Active(boolean activity)
    {
        this.Q4Active = activity;
    }


    public static boolean getQ1Active()
    {
        return Q1Active;
    }

    public static boolean getQ2Active()
    {
        return Q2Active;
    }

    public static boolean getQ3Active()
    {
        return Q3Active;
    }

    public static boolean getQ4Active()
    {
        return Q4Active;
    }

    public static int getPlayerTurns()
    {
        return playerTurns;
    }

    public static int getScore()
    {
        return score;
    }

    public static String getPlayerName(){
        return UserName;
    }
}
