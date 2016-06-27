package com.wenbchen.android.imdb.activities;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wenbchen.android.imdb.R;
import com.wenbchen.android.imdb.adater.CustomListAdapter;
import com.wenbchen.android.imdb.database.WatchedMoviesDataSource;
import com.wenbchen.android.imdb.model.Media;
import com.wenbchen.android.imdb.util.UtilsString;
import com.wenbchen.android.imdb.volleysingleton.VolleySingleton;

public class MovieListViewActivity extends BaseListViewActivity {
	// Log tag
	private static final String TAG = "MovieListViewActivity";

	@Override
	protected StringBuffer buildSearchRequest(String title, String year) {
		StringBuffer mUrlStringBuffer = new StringBuffer();
		mUrlStringBuffer.append(UtilsString.BASE_URL);
		mUrlStringBuffer.append("?s=");
		mUrlStringBuffer.append(title);
		mUrlStringBuffer.append("&y=");
		mUrlStringBuffer.append(year);
		mUrlStringBuffer.append("&type=movie");
		Log.i(TAG, "Url is: " + mUrlStringBuffer.toString());
		return mUrlStringBuffer;
	}
}