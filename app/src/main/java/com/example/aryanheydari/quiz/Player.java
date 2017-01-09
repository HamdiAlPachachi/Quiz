package com.example.aryanheydari.quiz;


import android.support.v7.app.AppCompatActivity;

public class Player extends AppCompatActivity
{

    private int id;
    public String PlayerName;//This variable is used to create ArrayLists of type Player.
    private int listScore;//This variable is used to create ArrayLists of type Player.
    public static int tempScore = 0; //This variable records the dynamic tempScore acvhieved by a player during a game.
    public static String UserName; //This variable temporarily stores the username of the player in the current session.

    public static int playerTurns = 1;//This counter measures the TOTAL numbers of quizzes attempted by (both) player(s).
    public static int playerCounter = 1;//This counter measures the number of players.


    public static boolean multiPlayer; //This variable is true if the game is in mutiplayer mode, true otherwise.
    public static boolean resultsIndicator; //This variable indicates to the system whether the user is playing or simply viewing past results.

    private static boolean Q1Active = true, Q2Active = true, Q3Active = true, Q4Active = true;

    public Player()
    {

    }

    public Player(String PlayerName, int score)
    {
        this.PlayerName = PlayerName;
        this.listScore = score;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setPlayerName(String PlayerName)
    {

        this.PlayerName = PlayerName;
    }

    public String getPlayerName()
    {
        return PlayerName;
    }

    public void setListScore(int score)
    {
        this.listScore = score;
    }

    public int getId()
    {
        return id;
    }

    public int getListScore()
    {
        return listScore;
    }

    public static int getTempScore()
    {
        return tempScore;
    }

    public static String getUserName(){
        return UserName;
    }

    public void setMultiPlayer(boolean mode)
    {
        multiPlayer = mode;
    }

    public void setResultsIndicator(boolean mode)
    {
        resultsIndicator = mode;
    }

    //SetQActive() methods are used as indicators of whether of radio buttons in each question are activated or deactivated.
    public void setQ1Active(boolean activity)
    {
        Q1Active = activity;
    }

    public void setQ2Active(boolean activity)
    {
        Q2Active = activity;
    }

    public void setQ3Active(boolean activity)
    {
        Q3Active = activity;
    }

    public void setQ4Active(boolean activity)
    {
        Q4Active = activity;
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

}
