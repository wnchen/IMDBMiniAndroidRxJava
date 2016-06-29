package com.wenbchen.android.imdb.activities;

import java.net.URLEncoder;
import android.util.Log;
import com.wenbchen.android.imdb.R;
import com.wenbchen.android.imdb.util.UtilsString;

public class TVListViewActivity extends BaseListViewActivity {
    // Log tag
    private static final String TAG = "TVListViewActivity";

    @Override
    protected void setUpPageTitle() {
        setTitle(getResources().getString(R.string.tv_list));
    }

    @Override
    protected StringBuffer buildSearchRequest(String title, String year) {
        StringBuffer mUrlStringBuffer = new StringBuffer();
        mUrlStringBuffer.append(UtilsString.BASE_URL);
        mUrlStringBuffer.append("?s=");
        try {
            title = URLEncoder.encode(title, "UTF-8");
            year = URLEncoder.encode(year, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mUrlStringBuffer.append(title);
        mUrlStringBuffer.append("&y=");
        mUrlStringBuffer.append(year);
        mUrlStringBuffer.append("&type=series");
        Log.i(TAG, "Url is: " + mUrlStringBuffer.toString());
        return mUrlStringBuffer;
    }
}