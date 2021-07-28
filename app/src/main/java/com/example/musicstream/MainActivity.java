package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SongCollection songCollection = new SongCollection();
    //I am creating a variable/copy of the SongCollection class.
    //So that I am able to call the methods and variables from the SongCollection class, by accessing
    static ArrayList<Song> faveList = new ArrayList<Song>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void handleSelection(View myView)
    {
        String resourceId=getResources().getResourceEntryName(myView.getId());
        Log.d("temasek","The is of the pressed ImageButton is: "+resourceId);
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        sendDataToActivity(currentArrayIndex);
    }
    public void sendDataToActivity(int index)
    {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index",index);//optional
        startActivity(intent);
    }
    public void addToFavourites(View view)
    {
        //Getting contentDescription found in activity_main.xml, e.g S1001, and turning it into String songID
        String songID = view.getContentDescription().toString();
        Song song = songCollection.getCurrentSong(songCollection.searchSongById(songID));
        faveList.add(song);
        //Toast.makeText(this,"Added to Favourites",Toast.LENGTH_SHORT).show();
    }

    public void goToFavouritesActivity(View view)
    {
        //This will bring the user to FavouriteActivity
        Intent intent = new Intent(this,FavouriteActivity.class);
        startActivity(intent);
    }
}