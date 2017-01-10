package com.wenbchen.android.imdb.adater;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.android.volley.toolbox.ImageLoader;
//import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;
import com.wenbchen.android.imdb.R;
import com.wenbchen.android.imdb.activities.MovieDetailActivity;
import com.wenbchen.android.imdb.activities.MovieListViewActivity;
import com.wenbchen.android.imdb.activities.TVDetailActivity;
import com.wenbchen.android.imdb.asynctask.InternetReachabilityTestAyncTask;
import com.wenbchen.android.imdb.database.WatchedMoviesDataSource;
import com.wenbchen.android.imdb.entity.Movie;
import com.wenbchen.android.imdb.util.UtilsString;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {
	public static final String TAG = "CustomListAdapter";
	
	private Activity activity;
	private LayoutInflater inflater;
	private List<Movie> movieItems;
	//ImageLoader imageLoader;
	private WatchedMoviesDataSource dataSource;
	
	public CustomListAdapter(Activity activity, List<Movie> movieItems, WatchedMoviesDataSource dataSource) {
		this.activity = activity;
		this.movieItems = movieItems;
		this.dataSource = dataSource;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//imageLoader = VolleySingleton.getInstance(activity.getApplicationContext()).getImageLoader();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView thumbNail;
		TextView title;
		TextView type;
		TextView year;
		TextView watched;
		Button details;
		public ViewHolder(View view) {
			super(view);
			this.type = (TextView) view.findViewById(R.id.type);
			this.thumbNail = (ImageView) view.findViewById(R.id.thumbnail);
			this.title = (TextView) view.findViewById(R.id.title);
			this.year = (TextView) view.findViewById(R.id.releaseYear);
			this.watched = (TextView) view.findViewById(R.id.viewed);
			this.details = (Button) view.findViewById(R.id.detailButton);
		}
	}


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
		ViewHolder vh = new ViewHolder(view);
		return vh;
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		Movie m = movieItems.get(position);
		final String uuid = m.getUuid();

		// thumbnail image
		Log.i(TAG, "POS " + position + " thumb url " + m.getThumbnailUrl());
		if (m.getThumbnailUrl().length() > 0 &&!m.getThumbnailUrl().equalsIgnoreCase(UtilsString.NA_STRING)) {
			Picasso.with(activity).load(m.getThumbnailUrl()).into(holder.thumbNail);
		} else {
			holder.thumbNail.setImageBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.default_thumb));
		}
		// title
		holder.title.setText(m.getTitle());
		// rating
		holder.type.setText(activity.getResources().getString(R.string.type_detail) + m.getType());
		// release year
		holder.year.setText(m.getYear());
		// watched history
		if (dataSource.exists(uuid)) {
			holder.watched.setText(activity.getResources().getString(R.string.viewed));
		} else {
			holder.watched.setText("");
		}
		//details button
		holder.details.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				final InternetReachabilityTestAyncTask internetTestAsyncTask = new InternetReachabilityTestAyncTask(activity, UtilsString.INTERNET_CHECK_URL, new InternetReachabilityTestAyncTask.Callback() {

					@Override
					public void onReachabilityTestPassed() {
						dataSource.insertMovie(uuid, 1);
						Intent intent;
						if (activity instanceof MovieListViewActivity) {
							intent = new Intent(activity, MovieDetailActivity.class);
						} else {
							intent = new Intent(activity, TVDetailActivity.class);
						}
						intent.putExtra(UtilsString.UUID_KEY, uuid);
						activity.startActivity(intent);
						holder.watched.setText(activity.getResources().getString(R.string.viewed));
					}

					@Override
					public void onReachabilityTestFailed() {
						Log.i(TAG, "No internet connection");
						Toast.makeText(activity, activity.getResources().getString(R.string.no_internet_msg), Toast.LENGTH_LONG).show();
					}
				});
				internetTestAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}
		});
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemCount() {
		return movieItems.size();
	}

}