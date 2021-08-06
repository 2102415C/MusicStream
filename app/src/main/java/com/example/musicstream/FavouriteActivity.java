package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FavouriteActivity extends AppCompatActivity {
RecyclerView faveList;
SongAdapter songAdapter;
Button btnBack2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        faveList = findViewById(R.id.recyclerView);
        songAdapter = new SongAdapter(MainActivity.faveList);
        faveList.setAdapter(songAdapter);
        faveList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void deleteAll(View view) {
        MainActivity.faveList.clear();
        songAdapter.notifyDataSetChanged();
    }
    public void goBack(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}