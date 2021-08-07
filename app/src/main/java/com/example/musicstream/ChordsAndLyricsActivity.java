package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class ChordsAndLyricsActivity extends AppCompatActivity {
    String chordsAndLyrics = "";
    SongCollection songCollection = new SongCollection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chords_and_lyrics);
    }
    public void displaySongBasedOnIndex(int selectedIndex)
    {
        Song song = songCollection.getCurrentSong(selectedIndex);
        chordsAndLyrics = song.getChordsAndLyrics();
    }
    public void displayChordsAndLyrics(View view) {
        TextView txtChordsAndLyrics = findViewById(R.id.txtChordsAndLyrics);
        txtChordsAndLyrics.setText(chordsAndLyrics);
    }
}