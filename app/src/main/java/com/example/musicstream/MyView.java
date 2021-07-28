package com.example.musicstream;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyView extends RecyclerView.ViewHolder {
    public TextView titleTxt;
    public TextView artistTxt;
    public ImageView image;
    public Button deleteBtn;
    public MyView(@NonNull View itemView) {
        super(itemView);
        titleTxt = itemView.findViewById(R.id.titleTxt);
        artistTxt = itemView.findViewById(R.id.artistTxt);
        image = itemView.findViewById(R.id.image);
        deleteBtn = itemView.findViewById(R.id.deleteBtn);
    }
}
