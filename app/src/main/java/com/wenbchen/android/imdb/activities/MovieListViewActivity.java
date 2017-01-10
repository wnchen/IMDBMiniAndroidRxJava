package com.wenbchen.android.imdb.activities;

import java.net.URLEncoder;
import java.util.LinkedHashMap;

import android.util.Log;
import com.wenbchen.android.imdb.R;
import com.wenbchen.android.imdb.http.HttpMethods;
import com.wenbchen.android.imdb.subscribers.ProgressSubscriber;
import com.wenbchen.android.imdb.util.HttpUrlBuilder;
import com.wenbchen.android.imdb.util.ServiceType;
import com.wenbchen.android.imdb.util.UtilsString;

public class MovieListViewActivity extends BaseListViewActivity {
	// Log tag
	private static final String TAG = "MovieListViewActivity";

	@Override
	protected void setUpPageTitle() {
		setTitle(getResources().getString(R.string.movie_list));
	}

	@Override
	protected void getMovies() {
		HttpMethods.getInstance().getMovie(ServiceType.MOVIESEARCH, new ProgressSubscriber(getGetTopMovieOnNext(), MovieListViewActivity.this), title, year, "movie", "");
	}
}