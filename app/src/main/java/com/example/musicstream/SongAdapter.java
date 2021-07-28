package com.example.musicstream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<MyView> {
    public SongAdapter(List<Song> songs) {
        this.songs = songs;
    }

    //SongAdapter will transfer information from the ArrayList into the songs object
    List<Song> songs;
    Context context;
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_song,parent,false);
        MyView viewHolder = new MyView(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        //Let android studios know which attributes to use to eg. title, Cover Art, artist
        Song song = songs.get(position);
        TextView artist = holder.artistTxt;
        artist.setText(song.getArtist());
        TextView title = holder.titleTxt;
        title.setText(song.getTitle());
        int imageId = AppUtill.getImageIdFromDrawable(context,song.getDrawable());
        holder.image.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
