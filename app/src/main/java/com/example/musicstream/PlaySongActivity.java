package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class PlaySongActivity extends AppCompatActivity {

    private String title = "";
    private String artist = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;

    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    private SongCollection songCollection = new SongCollection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        //Create a Bundle to retrieve the Intent
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        Log.d("temasek","Retrieved position is: "+ currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    public void displaySongBasedOnIndex(int selectedIndex)
    {
        Song song = songCollection.getCurrentSong(selectedIndex);
        //Retrieve all data from the Song Collection list
        title = song.getTitle();
        artist = song.getArtist();
        fileLink = song.getFileLink();
        drawable = song.getDrawable();
        Log.d("temasek 1","The song selected in: "+ title+artist+drawable);
        //Get and assign the UI elements in Android Studios to maipulate or display the details.
        //Assigning the TextView UI into a variable. So that
        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);
        TextView txtArtist = findViewById(R.id.txtArtist);
        Log.d("temasek",artist);
        txtArtist.setText(artist);
        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        iCoverArt.setImageResource(drawable);
    }
    public void playSong(String songUrl)
    {
        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            gracefullyStopsWhenMusicEnds();
            btnPlayPause.setText("PAUSE");
            setTitle(title);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void playOrPauseMusic(View view)
    {
        if(player.isPlaying())
        {
            player.pause();
            btnPlayPause.setText("PLAY");
        }
        else
        {
            player.start();
            btnPlayPause.setText("PAUSE");
        }
    }
    private void gracefullyStopsWhenMusicEnds()
    {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getBaseContext(),"The song had ended and the OnCompleteListener is activated \n"+
                        "The button text is changed to 'PLAY'",Toast.LENGTH_LONG).show();
                btnPlayPause.setText("PLAY");
            }
        });
    }
    public void playNext(View view)
    {
        currentIndex=songCollection.getNextSong(currentIndex);
        Toast.makeText(this,"After clicking playNext,\nthe current index of this song\n"+
                "in the songCollection array is now: "+currentIndex,Toast.LENGTH_LONG).show();
        Log.d("temasek","After playNext, the index is now:"+currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    public void playPrevious(View view)
    {
        currentIndex=songCollection.getPreviousSong(currentIndex);
        Toast.makeText(this,"After clicking playPrevious,\nthe current index of this song\n"+
                "in the SongCollection array is now: "+currentIndex,Toast.LENGTH_LONG).show();
        Log.d("temasek","After playPrevious, the index is now:"+currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        player.release();
    }
}