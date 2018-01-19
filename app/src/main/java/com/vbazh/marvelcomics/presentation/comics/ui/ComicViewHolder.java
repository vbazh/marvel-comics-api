package com.vbazh.marvelcomics.presentation.comics.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vbazh.marvelcomics.R;


public class ComicViewHolder extends RecyclerView.ViewHolder {

    TextView titleText, textCharacters;
    RecyclerView characterRecycler;
    FrameLayout charactersLayout;

    public ComicViewHolder(View itemView) {
        super(itemView);
        titleText = itemView.findViewById(R.id.text_title);
        textCharacters = itemView.findViewById(R.id.text_characters);
        characterRecycler = itemView.findViewById(R.id.recycler_characters);
        charactersLayout = itemView.findViewById(R.id.charactersLayout);
    }
}
