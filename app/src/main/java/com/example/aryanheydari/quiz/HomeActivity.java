package com.example.aryanheydari.quiz;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends SuperClass
{
    Button btnSignIn,btnSignUp;
    DBHandler db;

    int limit = 6;

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Leaderboard4";
    public static final String TABLE_PLAYERS = "Players";
    public static final String TABLE_MULTIPLAYERS = "Multiplayers";
    public static final String KEY_ID = "id";
    public static final String KEY_PLAYERNAME = "PlayerName";
    public static final String KEY_PLAYERSCORE = "PlayerScore";
    public static final String KEY_PLAYERPASSWORD = "Password";


    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a instance of SQLite Database
        db = new DBHandler(this);
        db.open();

        TextView WelcomeText = (TextView) findViewById(R.id.WelcomeText);
        TextView Slogan = (TextView) findViewById(R.id.Slogan);
        CheckBox saveLoginCheckBox = (CheckBox)findViewById(R.id.checkBox);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        if(multiPlayer == true && playerCounter > 1)
        {
            WelcomeText.setText("Player 2");
            Slogan.setVisibility(View.INVISIBLE);
        }

        saveLogin = loginPreferences.getBoolean("saveLogin", false);


        // Get The Reference Of Buttons
        btnSignIn = (Button)findViewById(R.id.buttonSignIN);
        btnSignUp = (Button)findViewById(R.id.buttonSignUP);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentSignUP = new Intent(getApplicationContext(),SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
    }
    // Methods to handleClick Event of Sign In Button
    public void signIn(View V)
    {

        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.activity_home);
        dialog.setTitle("Login");

        final CheckBox saveLoginCheckBox = (CheckBox)dialog.findViewById(R.id.checkBox);
        final  EditText editTextUserName = (EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword = (EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);

        if(playerCounter > 1 && multiPlayer == true)
        {
            saveLoginCheckBox.setVisibility(View.INVISIBLE);
            editTextUserName.setText("");
            editTextPassword.setText("");
        }

        if (saveLogin == true && playerCounter == 1)
        {
            editTextUserName.setText(loginPreferences.getString("username", ""));
            editTextPassword.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = db.getSingleEntry(userName);
                boolean entryExists = db.checkStoredName(TABLE_MULTIPLAYERS, KEY_PLAYERNAME, userName);//Scanning multiplayer table for identical username.
                // check if the Stored password matches with  Password entered by user

                if (saveLoginCheckBox.isChecked())//saving details upon clicking Remember Me.
                {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", userName);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                }
                else
                {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }

                if (password.equals(storedPassword) && entryExists == false) {
                        Toast.makeText(HomeActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        dialog.dismiss();

                        SuperClass superClass = new SuperClass();
                        superClass.UserName = userName;


                        //This if-else statement ensures that the second player in multiplayer mode is directed straight to Q1 after entering details.
                        if(playerCounter <= 1)
                        {
                            Intent MultiPlayer = new Intent(getApplicationContext(), MultiPlayerActivity.class);//Start Q1
                            startActivity(MultiPlayer);
                        }
                        else
                        {
                            Intent Q1 = new Intent(getApplicationContext(), Q1.class);//Start Q1
                            startActivity(Q1);
                        }

                }
                else if(password.equals(storedPassword) && entryExists == true)
                {
                    Toast.makeText(HomeActivity.this, "You have already played. Please pass it to the next player.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(HomeActivity.this, "User Name or Password does not match records", Toast.LENGTH_LONG).show();
                }

            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        db.close();
    }
}