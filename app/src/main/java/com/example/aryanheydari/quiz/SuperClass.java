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

/**
 * Created by andro on 12/7/2016.
 */

public class SuperClass extends AppCompatActivity
{

    public static int score = 0;

    private static boolean Q1Active = true, Q2Active = true, Q3Active = true, Q4Active = true;

    public void setQ1Active(boolean activity)
    {
        this.Q1Active = activity;
    }

    public static boolean getQ1Active()
    {
        return Q1Active;
    }
    /**public SuperClass()
    {
        score = 0;
    }

    public SuperClass(int score)
    {

    }*/

    /**public static void setScore(int score)
    {
        this.score = score;
    }*/

    public static int getScore()
    {
        return score;
    }
}
