package com.example.aryanheydari.quiz;

import android.support.v7.app.AppCompatActivity;

public class SuperClass extends AppCompatActivity
{
    //This class is used to store data for quiz sessions.

    public static int score = 0; //This variable records the dynamic score acvhieved by a player during a game.

    public static int playerTurns = 1;//This counter measures the TOTAL numbers of quizzes attempted by (both) player(s).
    public static int individualTurnCounter = 0;//This counter measures the number of quizzes attempted by EACH player.
    public static int playerCounter = 1;//This counter measures the number of players.

    public static String UserName; //This variable temporarily stores the username of the player in the current session.

    public static boolean multiPlayer; //This variable is true if the game is in mutiplayer mode, true otherwise.

    private static boolean Q1Active = true, Q2Active = true, Q3Active = true, Q4Active = true;

    //SetQActive() methods are used as indicators of whether of radio buttons in each question are activated or deactivated.
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

    public static int getScore()
    {
        return score;
    }

    public static String getPlayerName(){
        return UserName;
    }
}
