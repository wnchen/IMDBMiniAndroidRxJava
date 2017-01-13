package com.wenbchen.android.imdb.http;

import com.wenbchen.android.imdb.model.MediaDetailEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wenbchen on 1/10/17.
 */

public interface MovieDetailService {
    @GET(" ")
    Observable<MediaDetailEntity> getMovies(@Query("i") String uuid);
}