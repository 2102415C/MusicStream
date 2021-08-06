package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

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
        SearchView searchView = findViewById(R.id.searchView);
        //setOnQueryTextListener will detect whenever there is text in searchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                songAdapter.getFilter().filter(newText);
                return false;
            }
        });
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