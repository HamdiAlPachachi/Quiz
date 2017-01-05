package com.example.aryanheydari.quiz;


public class Player extends SuperClass{

    private int id;
    public String PlayerName;
    private int score;

    public Player()
    {

    }

    public Player(String PlayerName) {
        this.PlayerName = PlayerName;
    }

    public Player(String PlayerName, int score)
    {
        this.PlayerName = PlayerName;
        this.score = score;
    };

    public void setId(int id)
    {
        this.id = id;
    }

    public void setPlayerName(String PlayerName) {

        this.PlayerName = PlayerName;
    }

    public String get_PlayerName()
    {
        return this.PlayerName;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getId()
    {
        return id;
    }

    public int get_Score()
    {
        return this.score;
    }
}
