package com.vbazh.marvelcomics.presentation.comics.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vbazh.marvelcomics.R;


public class CharacterViewHolder extends RecyclerView.ViewHolder {

    TextView nameText;
    ImageView image;


    public CharacterViewHolder(View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.text_name);
        image = itemView.findViewById(R.id.image);
    }
}
