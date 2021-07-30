package com.example.musicstream;

import android.util.Log;

public class SongCollection {
    //This is to create an Array to store Song variables.
    //This is similar to creating an Array of String Class
    Song[] songs= new Song[4];
    public SongCollection(){
        Song theWayYouLookTonight= new Song("S1001","1. The Way You Look Tonight","Michael Buble",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",4.66,R.drawable.michael_buble_collection);
        Song thriller= new Song("S1002","2. Thriller","Michael Jackson",
                "https://p.scdn.co/mp3-preview/dd0621b7f11364a37c96031dbedbca6b88dbfa7d?cid=2afe87a64b0042dabf51f37318616965",5.95,R.drawable.billie_jean);
        Song photograph= new Song("S1003","3. Photograph","Ed Sheeran",
                "https://p.scdn.co/mp3-preview/097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=2afe87a64b0042dabf51f37318616965",4.32,R.drawable.photograph);
        Song wonderwall= new Song("S1004","4. Wonderwall","Oasis",
                "https://p.scdn.co/mp3-preview/51fe5f9383a662f8b50caf8a0dfe1d577a43e93f?cid=2afe87a64b0042dabf51f37318616965",4.32,R.drawable.wonder_wall);
        songs[0]=theWayYouLookTonight;
        songs[1]=thriller;
        songs[2]=photograph;
        songs[3]=wonderwall;
    }
    public int searchSongById(String id)
    {
        for (int index = 0; index<songs.length; index++)
        {
            Song tempSong = songs[index];
            if (tempSong.getId().equals(id))
            {
                Log.d("TempSong","TempSong Data: "+tempSong.getArtist());
                return index;
                //return the index of the song in the array.
            }
        }
        return -1;
    }
    public Song getCurrentSong(int currentSongId)
    {
        return songs[currentSongId];
    }
    public int getNextSong(int currentSongIndex)
    {
        if (currentSongIndex>=songs.length-1)
        {
            return 0;
        }
        else
        {
            return currentSongIndex+1;
        }
    }
    public int getPreviousSong(int currentSongIndex)
    {
        if (currentSongIndex<=0)
        {
            return 2;
        }
        else
        {
            return currentSongIndex-1;
        }
    }
}
