package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    public final String[] usernameList = {"Placeholder","IDKWhatToPut"};
    public final String[] passwordList = {"Substitute","P@55W0RD"};//final is used so that information cannot be changed
    EditText username;
    EditText password;
    Button login;
    boolean loginSuccess = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.editTxtUsername);
        password = findViewById(R.id.editTxtPassword);
        login = (Button)findViewById(R.id.btnLogin);
    }
}