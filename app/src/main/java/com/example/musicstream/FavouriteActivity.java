package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

public class FavouriteActivity extends AppCompatActivity {
RecyclerView faveList;
SongAdapter songAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        faveList = findViewById(R.id.recyclerView);
        songAdapter = new SongAdapter(MainActivity.faveList);
        faveList.setAdapter(songAdapter);
        faveList.setLayoutManager(new LinearLayoutManager(this));
    }
}