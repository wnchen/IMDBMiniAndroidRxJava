package com.wenbchen.android.imdb.http;

import com.wenbchen.android.imdb.entity.HttpResult;
import com.wenbchen.android.imdb.entity.Movie;

import java.util.List;
import rx.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wenbchen on 12/2/16.
 */

public interface MovieService {

    @GET(" ")
    Observable<HttpResult<List<Movie>>> getMovies(@Query("s") String title);
}
