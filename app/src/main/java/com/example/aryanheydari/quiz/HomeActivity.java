package com.example.aryanheydari.quiz;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends SuperClass
{
    Button btnSignIn,btnSignUp;
    DBHandler db;

    int limit = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a instance of SQLite Database
        db = new DBHandler(this);
        db.open();

        // Get The Refference Of Buttons
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

        // get the References of views
        final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = db.getSingleEntry(userName);

                // check if the Stored password matches with  Password entered by user



                    if (password.equals(storedPassword)) {
                        Toast.makeText(HomeActivity.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                        dialog.dismiss();

                        SuperClass superClass = new SuperClass();
                        superClass.UserName = userName;
//                      Easing login process in multiplayer mode.


                        if(playerCounter <= 1) {
                            Intent MultiPlayer = new Intent(getApplicationContext(), MultiPlayerActivity.class);//Start Q1
                            startActivity(MultiPlayer);
                        }
                        else {
                            Intent Q1 = new Intent(getApplicationContext(), Q1.class);//Start Q1
                            startActivity(Q1);
                        }


//                    Intent ResultsOrPlay = new Intent(getApplicationContext(), ResultsOrPlay.class);//Start Q1
//                    startActivity(ResultsOrPlay);
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