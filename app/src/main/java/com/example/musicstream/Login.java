package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public final String[] usernameList = {"Placeholder","IDKWhatToPut"};
    public final String[] passwordList = {"Substitute","P@55W0RD"};//final is used so that information cannot be changed
    EditText username;
    //username corresponds to editTxtUsername in login xml
    EditText password;
    //password corresponds to editTxtPassword in login xml
    Button login;
    //login corresponds to btnLogin in login xml
    boolean loginSuccess = false;
    //default value is false
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Loads page
        username = findViewById(R.id.editTxtUsername);
        password = findViewById(R.id.editTxtPassword);
        login = (Button)findViewById(R.id.btnLogin);
    }
    public void login (View view)
    {
        loginSuccess = checkCredentials(username.getText().toString(),password.getText().toString());
        if (loginSuccess)
        {
            Toast.makeText(getBaseContext(),"Welcome To My Music App! :)",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getBaseContext(),"Your login was unsuccessful. Please try again."
            ,Toast.LENGTH_LONG).show();
        }
    }
    public boolean checkCredentials(String usernameInput,String passwordInput)
    {
        loginSuccess = false;
        boolean usernameExist = false;
        //If Username and Password are empty
        if ((usernameInput.isEmpty()||usernameInput == null)&&(passwordInput.isEmpty()||passwordInput == null))
        {
            username.setError("Enter your Username!");
            password.setError("Enter your Password!");
            Toast.makeText(getBaseContext(),"Please enter your Username and Password to login.",Toast.LENGTH_LONG).show();
        }
        //If Username is empty
        else if ((usernameInput.isEmpty()||usernameInput == null))
        {
            username.setError("Enter your Username!");
            Toast.makeText(getBaseContext(),"Please enter your Username to login successfully.",Toast.LENGTH_LONG).show();
        }
        //If Password is empty
        else if ((passwordInput.isEmpty()||passwordInput == null))
        {
            password.setError("Enter your Password!");
            Toast.makeText(getBaseContext(),"Please enter your Password to login successfully.",Toast.LENGTH_LONG).show();
        }
        else
        {
            //Check if Username or Password keyed in by the user match the existing usernames and passwords
            for (int index = 0;index < usernameList.length;index++)
            {
                if ((usernameList[index].equals(usernameInput)))
                {
                    usernameExist = true;
                    if (passwordList[index].equals(passwordInput))
                    {
                        loginSuccess = true;
                        break;//stops the code in the for loop
                    }
                    else
                    {
                        password.setError("Enter a valid Password!");
                        Toast.makeText(getBaseContext(),"You have entered an incorrect password for"+usernameInput,Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
            if (!usernameExist)
            {
                username.setError("Enter a valid Username!");
                Toast.makeText(getBaseContext(),"The Username you have entered does not exist.",Toast.LENGTH_LONG).show();
            }
        }
        return loginSuccess;
    }
}