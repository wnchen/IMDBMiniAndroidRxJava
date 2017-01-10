package com.wenbchen.android.imdb.fragments;

import android.app.ProgressDialog;
import android.content.Intent;

import com.wenbchen.android.imdb.R;
import com.wenbchen.android.imdb.activities.BaseListViewActivity;
import com.wenbchen.android.imdb.activities.MovieListViewActivity;
import com.wenbchen.android.imdb.util.UtilsString;

public class MovieFragment extends BaseFragment {
    public static final String TAG = "MovieFragment";
    @Override
    protected void initViews() {
        mSearchTitleTextView.setText(R.string.movie_search_title);
    }

    protected void performSearch(String title, String year) {
        Intent intent = new Intent(getActivity(), MovieListViewActivity.class);
        intent.putExtra(UtilsString.TITLE_KEY, title);
        intent.putExtra(UtilsString.YEAR_KEY, year);
        startActivity(intent);
    }
}
