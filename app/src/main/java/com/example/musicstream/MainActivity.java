package com.example.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void handleSelection(View myView)
    {
        String resourceId=getResources().getResourceEntryName(myView.getId());
        Log.d("temasek","The is of the pressed ImageButton is: "+resourceId);
    }
}