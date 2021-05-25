package com.example.musicstream;

public class SongCollection {
    //This is to create an Array to store Song variables.
    //This is similar to creating an Array of String Class
    private Song[] songs= new Song[3];
    public SongCollection(){
        Song theWayYouLookTonight= new Song("S1001","1. The Way You Look Tonight","Michael Buble",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",4.66,R.drawable.michael_buble_collection);
        Song billieJean= new Song("S1002","2. Billie Jean","Michael Jackson",
                "https://p.scdn.co/mp3-preview/dd0621b7f11364a37c96031dbedbca6b88dbfa7d?cid=2afe87a64b0042dabf51f37318616965",5.95,R.drawable.billie_jean);
    }
}
