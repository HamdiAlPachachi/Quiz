package com.example.aryanheydari.quiz;

/**
 * Created by aryanheydari on 10/12/2016.
 */

public class Player {

    private int _id;
    private String _PlayerName;
    private int score;

    public Player()
    {

    }

    public Player(String _PlayerName) {
        this._PlayerName = _PlayerName;
    }

    public Player(int _id, String _PlayerName, int score)
    {
        this._id = _id;
        this._PlayerName = _PlayerName;
        this.score = score;
    };

    public void setId(int _id) {
        this._id = _id;
    }

    public void setPlayerName(String _PlayerName) {
        this._PlayerName = _PlayerName;
    }

    public int getid() {
        return _id;
    }

    public String getPlayerName() {
        return _PlayerName;
    }

    public int getScore()
    {
        return SuperClass.getScore();
    }
}
