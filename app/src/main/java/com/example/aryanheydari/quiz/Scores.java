package com.example.aryanheydari.quiz;

/**
 * Created by aryanheydari on 10/12/2016.
 */

public class Scores {

    private int _id;
    private String _PlayerName;

    public Scores (){


    }

    public Scores(String PlayerName) {
        this._PlayerName = PlayerName;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_PlayerName(String _PlayerName) {
        this._PlayerName = _PlayerName;
    }

    public int get_id() {
        return _id;
    }

    public String get_PlayerName() {
        return _PlayerName;
    }
}
