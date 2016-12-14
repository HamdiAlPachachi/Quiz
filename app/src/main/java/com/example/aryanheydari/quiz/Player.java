package com.example.aryanheydari.quiz;

/**
 * Created by aryanheydari on 10/12/2016.
 */

public class Player {

    private int id;
    private String PlayerName;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setPlayerName(String PlayerName) {
        this.PlayerName = PlayerName;
    }

    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public int getScore()
    {
        return SuperClass.getScore();
    }
}
