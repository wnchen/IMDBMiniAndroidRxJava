package com.wenbchen.android.imdb.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wenbchen.android.imdb.R;
import com.wenbchen.android.imdb.activities.MovieListViewActivity;
import com.wenbchen.android.imdb.asynctask.InternetReachabilityTestAyncTask;
import com.wenbchen.android.imdb.util.UtilsString;

public class BaseFragment extends Fragment {
    public static final String TAG = "BaseFragment";

    protected TextView mSearchTitleTextView;
    private EditText mTitleEditText;
    private EditText mYearEditText;
    private Button mSearchButton;

    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        mSearchTitleTextView = (TextView) view.findViewById(R.id.movie_search_title);
        mTitleEditText = (EditText) view.findViewById(R.id.title_edit_text);
        mYearEditText = (EditText) view.findViewById(R.id.year_edit_text);
        mSearchButton = (Button) view.findViewById(R.id.search_button);

        initViews();

        return view;
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();



        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = mTitleEditText.getText().toString();
                final String year = mYearEditText.getText().toString();
                if (title == null || title.length() == 0) {
                    makeLongToast(getResources().getString(R.string.title_empty_msg));
                    return;
                }
                final InternetReachabilityTestAyncTask internetTestAsyncTask = new InternetReachabilityTestAyncTask(getActivity(), UtilsString.INTERNET_CHECK_URL, new InternetReachabilityTestAyncTask.Callback() {

                    @Override
                    public void onReachabilityTestPassed() {
                        progressDialog = ProgressDialog.show(getActivity(),
                                getResources().getString(R.string.wait_dialog_msg), getResources().getString(R.string.retrieve_dialog_msg), true, true);
                        performSearch(title, year);
                    }

                    @Override
                    public void onReachabilityTestFailed() {
                        Log.i(TAG, "No internet connection");
                        makeLongToast(getResources().getString(R.string.no_internet_msg));
                    }
                });
                internetTestAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
        hidePDialog();
    }

    @Override
    public void onDestroy() {
        //TODO Auto-generated method stub
        super.onDestroy();
    }

    protected void initViews() {

    }

    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


    public void makeLongToast(CharSequence message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    protected void performSearch(String title, String year) {
        return;
    }
}
