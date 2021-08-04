package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SongCollection songCollection = new SongCollection();
    //I am creating a variable/copy of the SongCollection class.
    //So that I am able to call the methods and variables from the SongCollection class
    SharedPreferences sharedPreferences;
    static ArrayList<Song> faveList = new ArrayList<Song>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("playList",MODE_PRIVATE);
        //check for values inside list, if there is, convert back to array list
        String albums = sharedPreferences.getString("list","");
        if (!albums.equals(""))
        {
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
            Gson gson = new Gson();
            faveList = gson.fromJson(albums,token.getType());
        }
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
        int index = songCollection.searchSongById(songID);
        Song song = songCollection.getCurrentSong(index);
        faveList.add(song);
        Gson gson = new Gson();
        //convert values to store inside list, from ArrayList
        String json = gson.toJson(faveList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("list",json);
        editor.apply();
        Log.d("gson","json");
        Toast.makeText(this,"Added to Favourites",Toast.LENGTH_SHORT).show();
    }

    public void goToFavouritesActivity(View view)
    {
        //This will bring the user to FavouriteActivity
        Intent intent = new Intent(this,FavouriteActivity.class);
        startActivity(intent);
    }
}