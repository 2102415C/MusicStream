package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SongCollection songCollection = new SongCollection();
    //I am creating a variable/copy of the SongCollection class.
    //So that I am able to call the methods and variables from the SongCollection class, by accessing
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
}