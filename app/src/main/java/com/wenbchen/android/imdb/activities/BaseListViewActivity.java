package com.wenbchen.android.imdb.activities;

import java.util.ArrayList;
import java.util.List;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.wenbchen.android.imdb.R;
import com.wenbchen.android.imdb.adater.CustomListAdapter;
import com.wenbchen.android.imdb.database.WatchedMoviesDataSource;
import com.wenbchen.android.imdb.model.MediaSearchEntity;
import com.wenbchen.android.imdb.subscribers.SubscriberOnNextListener;
import com.wenbchen.android.imdb.util.UtilsString;

public class BaseListViewActivity extends AppCompatActivity {
    // Log tag
    private static final String TAG = "BaseListViewActivity";

    // Movies json url
    private Toolbar toolbar;
    private StringBuffer mUrlStringBuffer;
    private ProgressDialog pDialog;
    private List<MediaSearchEntity> mediaSearchEntityList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView mNoMoviesTextView;
    private WatchedMoviesDataSource dataSource;
    protected String title;
    protected String year;

    private SubscriberOnNextListener getTopMovieOnNext;

    public List<MediaSearchEntity> getMediaSearchEntityList() {
        return mediaSearchEntityList;
    }

    public SubscriberOnNextListener getGetTopMovieOnNext() {
        return getTopMovieOnNext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        //set a Toolbar to replace the ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpPageTitle();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediaSearchEntityList = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        title = extras.getString(UtilsString.TITLE_KEY);
        year = extras.getString(UtilsString.YEAR_KEY);

        dataSource = new WatchedMoviesDataSource(this);
        dataSource.open();

        mNoMoviesTextView = (TextView) findViewById(R.id.no_movies);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomListAdapter(this, mediaSearchEntityList, dataSource);
        recyclerView.setAdapter(adapter);

        getTopMovieOnNext = new SubscriberOnNextListener<List<MediaSearchEntity>>() {
            @Override
            public void onNext(List<MediaSearchEntity> movies) {
                Log.i("TAG", "response is "+ movies.toString());
                getMediaSearchEntityList().clear();
                getMediaSearchEntityList().addAll(movies);
                adapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMovies();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dataSource != null) {
            dataSource.close();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    protected void setUpPageTitle() {
        return;
    }

    protected void getMovies() {
    }
}
