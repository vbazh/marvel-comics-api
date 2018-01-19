package com.vbazh.marvelcomics.presentation.comics.ui;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.vbazh.marvelcomics.R;
import com.vbazh.marvelcomics.models.CharacterResponse;

import java.util.ArrayList;
import java.util.List;


public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private List<CharacterResponse.Character> characters;

    private OnClickListener listener;

    public CharacterAdapter(OnClickListener listener) {
        this.listener = listener;
        characters = new ArrayList<>();
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {

        holder.nameText.setText(characters.get(holder.getAdapterPosition()).getName());

        Picasso.with(holder.image.getContext()).load(characters.get(holder.getAdapterPosition()).getThumbnail().getPath()+"."+
                characters.get(holder.getAdapterPosition()).getThumbnail().getExtension()).placeholder(R.drawable.ic_portrait).into(holder.image);

    }

    public void addCharacter(CharacterResponse.Character character) {
        characters.add(character);
        notifyItemInserted(characters.size() - 1);
    }

    public void addCharacters(List<CharacterResponse.Character> newCharacters) {
        characters.clear();
        for (CharacterResponse.Character character : newCharacters) {
            addCharacter(character);
        }
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public interface OnClickListener {
        void onClick(int position, int id);
    }
}
