package com.wenbchen.android.imdb.activities;

import org.json.JSONException;
import org.json.JSONObject;


import com.squareup.picasso.Picasso;
import com.wenbchen.android.imdb.R;
import com.wenbchen.android.imdb.model.MediaDetailEntity;
import com.wenbchen.android.imdb.http.HttpMethods;
import com.wenbchen.android.imdb.subscribers.ProgressSubscriber;
import com.wenbchen.android.imdb.subscribers.SubscriberOnNextListener;
import com.wenbchen.android.imdb.util.ServiceType;
import com.wenbchen.android.imdb.util.UtilsString;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MediaDetailBaseActivity extends AppCompatActivity {
    public static final String TAG = "MediaDetailBaseActivity";

    private String uuid;
    private TextView mTitleTextView;
    private ImageView mPosterNetworkImageView;
    private TextView mYearTextView;
    private TextView mDurationTextView;
    private TextView mGenreTextView;
    private TextView mDirectorTextView;
    private TextView mWriterTextView;
    private TextView mRatingTextView;
    private TextView mActorsTextView;
    private TextView mPlotTextView;
    private TextView mLanguageTextView;
    private TextView mCountryTextView;
    private TextView mAwardsTextView;

    private StringBuffer mStringBuffer;

    private ProgressDialog pDialog;
    //private ImageLoader imageLoader;

    private Toolbar toolbar;
    private SubscriberOnNextListener getMediaDetailOnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.movie_detail);

        //set a Toolbar to replace the ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitleTextView = (TextView)findViewById(R.id.name);
        mYearTextView = (TextView)findViewById(R.id.year);
        mDirectorTextView = (TextView)findViewById(R.id.director);
        mRatingTextView = (TextView)findViewById(R.id.rating);
        mPosterNetworkImageView = (ImageView)findViewById(R.id.detailImage);

        mDurationTextView = (TextView)findViewById(R.id.duration);
        mGenreTextView = (TextView)findViewById(R.id.genre);
        mWriterTextView = (TextView)findViewById(R.id.writer);
        mActorsTextView = (TextView)findViewById(R.id.actors);
        mPlotTextView = (TextView)findViewById(R.id.plot);
        mLanguageTextView = (TextView)findViewById(R.id.language);
        mCountryTextView = (TextView)findViewById(R.id.country);
        mAwardsTextView = (TextView)findViewById(R.id.award);

        //imageLoader = VolleySingleton.getInstance(this.getApplicationContext()).getImageLoader();
        //mPosterNetworkImageView.setImageUrl(null, imageLoader);

        //mStringBuffer = new StringBuffer();
        Bundle bundle = getIntent().getExtras();
        uuid = bundle.getString(UtilsString.UUID_KEY);

    /*    mStringBuffer.append(UtilsString.BASE_URL);
        mStringBuffer.append("?i=");
        mStringBuffer.append(uuid);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage(getResources().getString(R.string.load_dialog_msg));
        pDialog.show();*/

        // Creating volley request obj
      /*  JsonObjectRequest movieReq = new JsonObjectRequest(mStringBuffer.toString(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        parse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });*/

        // Adding request to request queue
        //VolleySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(movieReq, UtilsString.MOVIE_DETAIL_TAG);

        getMediaDetailOnNext = new SubscriberOnNextListener<MediaDetailEntity>() {

            @Override
            public void onNext(MediaDetailEntity media) {
                Log.i("TAG", "response is "+ media.toString());
                String thumbUrl = media.getPoster();
                //mPosterNetworkImageView.setDefaultImageResId(R.drawable.default_thumb);
                if (thumbUrl != null && !thumbUrl.equalsIgnoreCase(UtilsString.NA_STRING)){
                    Log.i(TAG, "thumbnail url not null");
                    // mPosterNetworkImageView.setImageUrl(obj.getString(UtilsString.POSTER_KEY), imageLoader);
                    Picasso.with(MediaDetailBaseActivity.this).load(thumbUrl).into(mPosterNetworkImageView);
                }
                else {
                    Log.i(TAG, "null thumbnail url");
                    mPosterNetworkImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_thumb));
                }
                mTitleTextView.setText(media.getTitle());
                mYearTextView.setText(media.getYear());
                mDirectorTextView.setText(media.getDirector());
                mRatingTextView.setText(media.getRated());

                mDurationTextView.setText(media.getRunTime());
                mGenreTextView.setText(media.getGenre());
                mWriterTextView.setText(media.getWriter());
                mActorsTextView.setText(media.getActors());
                mPlotTextView.setText(media.getPlot());
                mLanguageTextView.setText(media.getLanguage());
                mCountryTextView.setText(media.getCountry());
                mAwardsTextView.setText(media.getAwards());

            }
        };
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        getMediaDetail();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        //hidePDialog();
        //VolleySingleton.getInstance(this.getApplicationContext()).cancelPendingRequests(UtilsString.MOVIE_DETAIL_TAG);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
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

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private void parse(JSONObject obj){
        try {
            String thumbUrl = obj.getString(UtilsString.POSTER_KEY);
            //mPosterNetworkImageView.setDefaultImageResId(R.drawable.default_thumb);
            if (thumbUrl != null && !thumbUrl.equalsIgnoreCase(UtilsString.NA_STRING)){
                Log.i(TAG, "thumbnail url not null");
               // mPosterNetworkImageView.setImageUrl(obj.getString(UtilsString.POSTER_KEY), imageLoader);
            }
            else {
                Log.i(TAG, "null thumbnail url");
                //mPosterNetworkImageView.setDefaultImageResId(R.drawable.default_thumb);
            }
            mTitleTextView.setText(obj.getString(UtilsString.TITLE_KEY));
            mYearTextView.setText(obj.getString(UtilsString.YEAR_KEY));
            mDirectorTextView.setText(obj.getString(UtilsString.DIRECTOR_KEY));
            mRatingTextView.setText(obj.getString(UtilsString.RATING_KEY));

            mDurationTextView.setText(obj.getString(UtilsString.DURATION_KEY));
            mGenreTextView.setText(obj.getString(UtilsString.GENRE_KEY));
            mWriterTextView.setText(obj.getString(UtilsString.WRITER_KEY));
            mActorsTextView.setText(obj.getString(UtilsString.ACTORS_KEY));
            mPlotTextView.setText(obj.getString(UtilsString.PLOT_KEY));
            mLanguageTextView.setText(obj.getString(UtilsString.LANGUAGE_KEY));
            mCountryTextView.setText(obj.getString(UtilsString.COUNTRY_KEY));
            mAwardsTextView.setText(obj.getString(UtilsString.AWARD_KEY));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void getMediaDetail() {
        HttpMethods.getInstance().getMovie(ServiceType.MOVIEDETAIL, new ProgressSubscriber(getMediaDetailOnNext, MediaDetailBaseActivity.this), "", "", "", uuid);
    }

}
