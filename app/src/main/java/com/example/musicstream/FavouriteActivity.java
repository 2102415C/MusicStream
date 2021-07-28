package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class FavouriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        for (int i = 0; i < MainActivity.faveList.size(); i++)
        {
            Log.d("temasek", MainActivity.faveList.get(i).getTitle());
        }
    }
}