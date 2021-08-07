package com.example.musicstream;

import android.util.Log;

public class SongCollection {
    //This is to create an Array to store Song variables.
    //This is similar to creating an Array of String Class
    Song[] songs= new Song[4];
    public SongCollection(){
        Song theWayYouLookTonight= new Song("S1001","1. The Way You Look Tonight","Michael Buble",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",4.66,R.drawable.michael_buble_collection,"GM7            G#dim Am7        Dsus4\n" +
                "With each word your tenderness grows,\n" +
                "Bm7        Bb6    Am7 Dsus4\n" +
                "Tearing my fears apart\n" +
                "Bm7      Bb6        Am7           Dsus4\n" +
                "And that laugh that wrinkles your nose,\n" +
                "GM7            F#m    B7sus4\n" +
                "It touches my foolish heart.");
        Song billieJean= new Song("S1002","2. Billie Jean","Michael Jackson",
                "https://p.scdn.co/mp3-preview/f504e6b8e037771318656394f532dede4f9bcaea?cid=2afe87a64b0042dabf51f37318616965",5.95,R.drawable.billie_jean,"She says I am the one,\n" +
                "                         F#m\n" +
                "  But the kid is not my son\n" +
                "F#m\n" +
                "  Billie Jean is not my lover,\n" +
                "                                               Bm\n" +
                "  She's just a girl who claims that I am the one\n" +
                "                          F#m\n" +
                "  But the kid is not my son\n" +
                "                      Bm\n" +
                "  She says I am the one,\n" +
                "                         F#m\n" +
                "  But the kid is not my son");
        Song photograph= new Song("S1003","3. Photograph","Ed Sheeran",
                "https://p.scdn.co/mp3-preview/097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=2afe87a64b0042dabf51f37318616965",4.32,R.drawable.photograph,"[Chorus]\n" +
                "           D\n" +
                "So you can keep me inside the pocket of your\n" +
                "A\n" +
                "Ripped jeans holding me closer till our\n" +
                "Bm                               G\n" +
                "Eyes meet, you won't ever be alone\n" +
                " \n" +
                "[Bridge]\n" +
                "           D\n" +
                "And if you hurt me that's okay baby, only\n" +
                "A\n" +
                "Words bleed inside these pages you just\n" +
                "Bm                              G\n" +
                "Hold me, and I won't ever let you go");
        Song wonderwall= new Song("S1004","4. Wonderwall","Oasis",
                "https://p.scdn.co/mp3-preview/51fe5f9383a662f8b50caf8a0dfe1d577a43e93f?cid=2afe87a64b0042dabf51f37318616965",4.32,R.drawable.wonder_wall,"[Pre-Chorus]\n" +
                "     C                D            Em\n" +
                "And all the roads we have to walk are winding\n" +
                "     C                D                Em\n" +
                "And all the lights that lead us there are blinding\n" +
                " C              D\n" +
                "There are many things that I would\n" +
                "G       D      Em\n" +
                "Like to say to you\n" +
                "       D      A7sus4\n" +
                "But I don't know how\n" +
                " \n" +
                " \n" +
                "[Chorus]\n" +
                "         C    Em  G\n" +
                "Because maybe\n" +
                "        Em                   C        Em  G\n" +
                "You're gonna be the one that saves me?\n" +
                "     Em   C  Em  G\n" +
                "And after all\n" +
                "                Em  C  Em  G   Em\n" +
                "You're my wonderwall");

        songs[0]=theWayYouLookTonight;
        songs[1]=billieJean;
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
