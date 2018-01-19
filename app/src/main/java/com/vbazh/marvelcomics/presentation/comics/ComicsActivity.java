package com.vbazh.marvelcomics.presentation.comics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.vbazh.marvelcomics.Consts;
import com.vbazh.marvelcomics.R;
import com.vbazh.marvelcomics.di.ComponentManager;
import com.vbazh.marvelcomics.models.CharacterResponse;
import com.vbazh.marvelcomics.models.ComicsResponse;
import com.vbazh.marvelcomics.presentation.comics.ui.ComicAdapter;
import com.vbazh.marvelcomics.utils.DateFormatUtils;

import java.util.List;

import javax.inject.Inject;

public class ComicsActivity extends MvpAppCompatActivity implements ComicsContract.View {

    Toolbar toolbar;
    RecyclerView comicsRecyclerView;
    ComicAdapter comicAdapter;
    ProgressBar loadingProgressBar;
    Button retryButton;
    long start, end;
    TextView textNoData;
    boolean isLoading;

    @Inject
    DateFormatUtils dateFormatUtils;

    @Inject
    @InjectPresenter
    ComicsPresenter presenter;

    @ProvidePresenter
    ComicsPresenter providePresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ComponentManager.getInstance().getComicsComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);

        start = getIntent().getLongExtra(Consts.EXTRA_START_INTERVAL, 0);
        end = getIntent().getLongExtra(Consts.EXTRA_END_INTERVAL, 0);

        if (savedInstanceState == null) {
            presenter.loadData(dateFormatUtils.formatIntervalForQuery(start, end));
        }

        initViews();
    }

    private void initViews() {

        toolbar = findViewById(R.id.toolbar);
        comicsRecyclerView = findViewById(R.id.recycler_comics);
        loadingProgressBar = findViewById(R.id.progress_bar);
        retryButton = findViewById(R.id.button_retry);
        textNoData = findViewById(R.id.text_no_data);

        toolbar.setTitle(dateFormatUtils.formatIntervalText(start, end));
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        retryButton.setOnClickListener(view -> presenter.loadData(dateFormatUtils.formatIntervalForQuery(start, end)));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        comicsRecyclerView.setLayoutManager(layoutManager);

        comicAdapter = new ComicAdapter((position, id) -> {
            presenter.loadCharacters(position, id);

        });

        comicsRecyclerView.setAdapter(comicAdapter);

        comicsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                if (!isLoading) {
                    presenter.onScrolled(visibleItemCount, totalItemCount, firstVisibleItem);
                }
            }
        });
    }

    @Override
    public void addData(List<ComicsResponse.Comic> comics) {

        comicAdapter.addComics(comics);
        comicsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {

        retryButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {

        comicsRecyclerView.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        isLoading = true;
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        isLoading = false;
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNodata() {

        textNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNodata() {

        textNoData.setVisibility(View.INVISIBLE);
    }

    @Override
    public void addCharacters(int position, int id, List<CharacterResponse.Character> characters) {

        comicAdapter.addCharacters(position, id, characters);
    }

    @Override
    protected void onDestroy() {
        ComponentManager.getInstance().destroyComicsComponent();
        super.onDestroy();
    }
}
