package com.wenbchen.android.imdb.fragments;

import android.content.Intent;

import com.wenbchen.android.imdb.R;
import com.wenbchen.android.imdb.activities.TVListViewActivity;
import com.wenbchen.android.imdb.util.UtilsString;

public class TVFragment extends BaseFragment {

    @Override
    protected void initViews() {
        mSearchTitleTextView.setText(R.string.tv_search_title);
    }

    protected void performSearch(String title, String year) {
        Intent intent = new Intent(getActivity(), TVListViewActivity.class);
        intent.putExtra(UtilsString.TITLE_KEY, title);
        intent.putExtra(UtilsString.YEAR_KEY, year);
        startActivity(intent);
    }
}
