package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class ChordsAndLyricsActivity extends AppCompatActivity {
    String chordsAndLyrics = "";
    SongCollection songCollection = new SongCollection();
    int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chords_and_lyrics);
        displayChordsAndLyricsBasedOnIndex(currentIndex);
    }
    public void displayChordsAndLyricsBasedOnIndex(int index)
    {
        Song song = songCollection.getCurrentSong(index);
        chordsAndLyrics = song.getChordsAndLyrics();
        TextView txtChordsAndLyrics = findViewById(R.id.txtChordsAndLyrics);
        txtChordsAndLyrics.setText(chordsAndLyrics);
    }
    public void displayChordsAndLyrics(View view){
        currentIndex=songCollection.getNextSong(currentIndex);
        displayChordsAndLyricsBasedOnIndex(currentIndex);
    }
    public void goBack(View view){
        Intent intent = new Intent(this,PlaySongActivity.class);
        startActivity(intent);
    }
}