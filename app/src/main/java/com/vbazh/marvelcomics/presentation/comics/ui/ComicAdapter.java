package com.vbazh.marvelcomics.presentation.comics.ui;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vbazh.marvelcomics.R;
import com.vbazh.marvelcomics.models.CharacterResponse;
import com.vbazh.marvelcomics.models.ComicsResponse;

import java.util.ArrayList;
import java.util.List;


public class ComicAdapter extends RecyclerView.Adapter<ComicViewHolder> {

    private List<ComicsResponse.Comic> comics;
    private SparseArray<List<CharacterResponse.Character>> characters;

    private OnClickListener listener;
    private CharacterAdapter characterAdapter;

    public ComicAdapter(OnClickListener listener) {
        this.listener = listener;
        comics = new ArrayList<>();
        characters = new SparseArray<>();
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comic, parent, false);
        return new ComicViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(ComicViewHolder holder, int position) {

        if (comics.get(holder.getAdapterPosition()).getCharacterList().getAvailable() != 0 &&
                !comics.get(holder.getAdapterPosition()).getOpen()) {

            holder.itemView.setOnClickListener(view -> listener.onClick(holder.getAdapterPosition(),
                    comics.get(holder.getAdapterPosition()).getId()));
        } else {
            holder.itemView.setOnClickListener(v -> {});
        }

        holder.titleText.setText(comics.get(holder.getAdapterPosition()).getTitle());
        holder.textCharacters.setText(String.format("%s: %d", holder.textCharacters.getContext().getString(R.string.text_characters),
                comics.get(holder.getAdapterPosition()).getCharacterList().getAvailable()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.titleText.getContext());
        holder.characterRecycler.setLayoutManager(layoutManager);

        characterAdapter = new CharacterAdapter((position1, id) -> {

        });

        if (comics.get(holder.getAdapterPosition()).getOpen()) {
            holder.charactersLayout.setVisibility(View.VISIBLE);
            characterAdapter.addCharacters(characters.get(comics.get(holder.getAdapterPosition()).getId()));
        } else holder.charactersLayout.setVisibility(View.GONE);

        holder.characterRecycler.setAdapter(characterAdapter);

    }

    private void addComic(ComicsResponse.Comic comic) {
        comics.add(comic);
        notifyItemInserted(comics.size() - 1);
    }

    public void addComics(List<ComicsResponse.Comic> newComics) {
        for (ComicsResponse.Comic comic : newComics) {
            addComic(comic);
        }
    }

    public void addCharacters(int position, int id, List<CharacterResponse.Character> newCharacters) {

        characters.put(id, newCharacters);
        comics.get(position).setOpen(true);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public interface OnClickListener {
        void onClick(int position, int id);
    }
}
