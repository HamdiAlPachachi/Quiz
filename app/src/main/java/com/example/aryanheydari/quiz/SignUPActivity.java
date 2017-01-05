package com.example.aryanheydari.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUPActivity extends Activity
{
    public static final String TABLE_PLAYERS = "Players";
    public static final String KEY_PLAYERNAME = "PlayerName";

    EditText editTextUserName,editTextPassword,editTextConfirmPassword;
    Button btnCreateAccount;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new DBHandler(this);
        db = db.open();

        editTextUserName = (EditText)findViewById(R.id.editTextUserName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText)findViewById(R.id.editTextConfirmPassword);

        btnCreateAccount=(Button)findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                boolean checkUserExists = db.checkStoredName(TABLE_PLAYERS, KEY_PLAYERNAME, userName);

                // checks if any of the fields are empty - INPUT VALIDATION
                if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }

                // check if both passwords match - INPUT VALIDATION
                if(checkUserExists == false)
                {
                    if (!password.equals(confirmPassword))
                    {
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else
                    {
                        // Save the Data in Database
                        db.insertEntry(userName, password);
                        Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Account already exists", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        db.close();
    }

    public void CreateAccount(View view)
    {
        Intent HomeActivity = new Intent(this, HomeActivity.class);
        startActivity(HomeActivity);
    }
}