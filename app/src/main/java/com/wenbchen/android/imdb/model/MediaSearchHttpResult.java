package com.wenbchen.android.imdb.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wenbchen on 12/1/16.
 */

public class MediaSearchHttpResult<T> {

    //to model data
    @SerializedName("Search")
    private T movies;
    @SerializedName("totalResults")
    private String totalResults;
    @SerializedName("Response")
    private String response;


    public T getMovies() {
        return movies;
    }

    public void setMovies(T movies) {
        this.movies = movies;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("totalResults=" + totalResults + " response=" + response);
        if (movies != null) {
            sb.append(" movies:" + movies.toString());
        }
        return sb.toString();
    }

}
