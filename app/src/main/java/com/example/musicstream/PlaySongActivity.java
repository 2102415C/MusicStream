package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {

    private String title = "";
    private String artist = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;
    SeekBar seekbar;
    Handler handler = new Handler();
    Runnable runnable;
    Button btnRepeat;
    Button btnShuffle;
    Boolean repeatFlag = false;
    Boolean shuffleFlag = false;
    Button btnBack;
    Button btnChordsAndLyrics;
    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    private SongCollection songCollection = new SongCollection();
    private SongCollection originalSongCollection = new SongCollection();
    //A list can swap the items in the list around
    List<Song> shuffleList = Arrays.asList(songCollection.songs);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        //After setting and successfully loading up the activity_play_song.xml,check if there are an existing music/player being started.
        if (player.isPlaying())
        {
            player = null;
        }
        seekbar = (SeekBar) findViewById(R.id.songSeekBar);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        //Create a Bundle to retrieve the Intent
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        Log.d("temasek","Retrieved position is: "+ currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekbar.setMax(player.getDuration());
                updateSeekbar();
            }
        });
        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                double ratio = percent/100;
            }
        });
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
            btnPlayPause.setText("PAUSE");
            setTitle(title);
            player.seekTo(0);
            System.out.println("This is the song Duration" + player.getDuration());
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser)
                    {
                        player.seekTo(progress);
                        seekBar.setProgress(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            btnRepeat = findViewById(R.id.btnRepeat);
            btnShuffle = findViewById(R.id.btnShuffle);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        gracefullyStopsWhenMusicEnds();
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
    public void updateSeekbar()
    {
        int currPos = player.getCurrentPosition();
        seekbar.setProgress(currPos);
        runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekbar();
            }
        };
        handler.postDelayed(runnable,1000);
    }
    private void gracefullyStopsWhenMusicEnds()
    {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (repeatFlag)
                {
                    playOrPauseMusic(null);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "The song had ended and the OnCompleteListener is activated \n" +
                            "The button text is changed to 'PLAY'", Toast.LENGTH_LONG).show();
                    btnPlayPause.setText("PLAY");
                    seekbar.setProgress(0);
                }
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
        if (player != null)
        {
            player.release();
            if (handler != null)
                handler.removeCallbacksAndMessages(null);
        }
        finish();
        super.onBackPressed();
    }

    public void repeatSong(View view) {
        if (repeatFlag)
        {
            //changes the repeat button image from on to off
            btnRepeat.setBackgroundResource(R.drawable.repeat_off);
        }
        else
        {
            //changes the repeat button image from off to on
            btnRepeat.setBackgroundResource(R.drawable.repeat_on);
        }
        //changes the value of repeatFlag from false to true or true to false depending on the value at first
        repeatFlag = !repeatFlag;
    }
    public void shuffleSong(View view) {
        if (shuffleFlag)
        {
            //changes the shuffle button image from on to off
            btnShuffle.setBackgroundResource(R.drawable.shuffle_off);
            //resets the order of the songs
            songCollection = new SongCollection();
        }
        else
        {
            //changes the shuffle button image from off to on
            btnShuffle.setBackgroundResource(R.drawable.shuffle_on);
            //shuffles the contents in the list
            Collections.shuffle(shuffleList);
            //overrides the original sequence and uses the new sequence
            shuffleList.toArray(songCollection.songs);
        }
        //changes the value of shuffleFlag from false to true or true to false depending on the value at first
        shuffleFlag = !shuffleFlag;
    }
    public void goBack(View view){
        if (player.isPlaying())
        {
            player.pause();
        }
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void goToChordsAndLyrics(View view){
        Intent intent = new Intent(this,ChordsAndLyricsActivity.class);
        startActivity(intent);
    }
}