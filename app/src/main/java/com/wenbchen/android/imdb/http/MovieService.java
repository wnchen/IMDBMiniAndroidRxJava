package com.wenbchen.android.imdb.http;

import com.wenbchen.android.imdb.model.MediaSearchEntity;
import com.wenbchen.android.imdb.model.MediaSearchHttpResult;

import java.util.List;
import rx.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wenbchen on 12/2/16.
 */

public interface MovieService {

    @GET(" ")
    Observable<MediaSearchHttpResult<List<MediaSearchEntity>>> getMovies(@Query("s") String title, @Query("y") String year, @Query("type") String type);
}
