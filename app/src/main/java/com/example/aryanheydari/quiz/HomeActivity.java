package com.example.aryanheydari.quiz;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Player
{
    Button btnSignIn,btnSignUp;
    DBHandler db;

    public static final String TABLE_MULTIPLAYERS = "Multiplayers";
    public static final String KEY_PLAYERNAME = "PlayerName";

    private SharedPreferences logPr;
    private SharedPreferences.Editor logPrEd;
    private boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creates an instance of SQLite Database
        db = new DBHandler(this);
        db.open();

        TextView WelcomeText = (TextView) findViewById(R.id.WelcomeText);
        TextView Slogan = (TextView) findViewById(R.id.Slogan);
        btnSignIn = (Button)findViewById(R.id.buttonSignIN);
        btnSignUp = (Button)findViewById(R.id.buttonSignUP);

        logPr = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        logPrEd = logPr.edit();

        if(multiPlayer == true && playerCounter > 1)//This if-else statement changes the display of the Sign In screen when the second player signs in in multiplayer mode.
        {
            WelcomeText.setText("Player 2");
            Slogan.setVisibility(View.INVISIBLE);
        }

        saveLogin = logPr.getBoolean("saveLogin", false);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intentSignUP = new Intent(getApplicationContext(),SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
    }

    // Method  to handle Click of Sign In button
    public void signIn(View view)
    {

        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.activity_home);
        dialog.setTitle("Login");

        final CheckBox saveLoginCheckBox = (CheckBox)dialog.findViewById(R.id.checkBox);
        final  EditText editTextUserName = (EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword = (EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);

        //This ensures that the saved name and password do not populate the registration interface for the 2nd player in multiplayer mode.
        if(playerCounter > 1 && multiPlayer == true)
        {
            saveLoginCheckBox.setVisibility(View.INVISIBLE);
            editTextUserName.setText("");
            editTextPassword.setText("");
        }

        //This method populates the registration fields with the respective username and password.
        if (saveLogin == true && playerCounter == 1)
        {
            editTextUserName.setText(logPr.getString("username", ""));
            editTextPassword.setText(logPr.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                // get The User name and Password
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = db.getSingleEntry(userName);

                // check if the Stored password matches with Password entered by user
                boolean entryExists = db.checkName(TABLE_MULTIPLAYERS, KEY_PLAYERNAME, userName);//Scanning multiplayer table for identical username.

                if (saveLoginCheckBox.isChecked())//saving details upon clicking "Remember Me" button.
                {
                    logPrEd.putBoolean("saveLogin", true);
                    logPrEd.putString("username", userName);
                    logPrEd.putString("password", password);
                    logPrEd.commit();
                }
                else
                {
                    logPrEd.clear();
                    logPrEd.commit();
                }

                if (password.equals(storedPassword) && entryExists == false)
                {
                        Toast.makeText(HomeActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        dialog.dismiss();

                        Player player = new Player();
                        player.UserName = userName;

                        tempScore = 0;
                        setQ1Active(true);
                        setQ2Active(true);
                        setQ3Active(true);
                        setQ4Active(true);

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
                else if(password.equals(storedPassword) && entryExists == true) //INPUT VALIDATION: This checks whether the entered name has already been entered used in the multiplayer session.
                {
                    Toast.makeText(HomeActivity.this, "You have already played. Please pass it to the next player.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(HomeActivity.this, "User Name or Password does not match records", Toast.LENGTH_LONG).show();//INPUT VALIDATION.
                }

            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        db.close();
    }
}