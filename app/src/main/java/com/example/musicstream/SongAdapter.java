package com.example.musicstream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<MyView> implements Filterable {
    private List<Song> songsFiltered;//list for search bar
    public SongAdapter(List<Song> songs) {
        this.songs = songs;
        this.songsFiltered = songs;
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
    public void onBindViewHolder(@NonNull MyView holder,final int position) {
        //Let android studios know which attributes to use to eg. title, Cover Art, artist
        Song song = songsFiltered.get(position);
        TextView artist = holder.artistTxt;
        artist.setText(song.getArtist());
        TextView title = holder.titleTxt;
        title.setText(song.getTitle());
        int imageId = song.getDrawable();
        holder.image.setImageResource(imageId);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.faveList.remove(position);
                notifyDataSetChanged();
            }
        });
    }
    @Override
    public int getItemCount() {
        return songsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty())
                {
                    //if nothing is typed in, show original list
                    songsFiltered=songs;
                }
                else
                {
                    List<Song> filteredList = new ArrayList<Song>();
                    for (int i = 0; i < songs.size(); i++) {
                        //shows whichever songs match what the user types in
                        if (songs.get(i).getTitle().toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(songs.get(i));
                        }
                    }
                    songsFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = songsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                songsFiltered = (List<Song>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
