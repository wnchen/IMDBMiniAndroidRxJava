package com.wenbchen.android.imdb.http;

import com.wenbchen.android.imdb.entity.HttpResult;
import com.wenbchen.android.imdb.entity.Movie;
import com.wenbchen.android.imdb.util.ServiceType;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Observable;
import rx.functions.Func1;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wenbchen on 12/2/16.
 */

public class HttpMethods {

    public static final String BASE_URL = "http://www.omdbapi.com/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MovieService movieService;
    private MovieDetailService detailService;

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(MovieService.class);
        detailService = retrofit.create(MovieDetailService.class);
    }

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getMovie(ServiceType serviceType, Subscriber<List<Movie>> subscriber, String title, String year, String type, String uuid) {
        Observable observable = null;
        switch (serviceType) {
            case MOVIESEARCH:
                observable = movieService.getMovies(title, year, type)
                        .map(new HttpResultFunc<List<Movie>>());
                break;
            case MOVIEDETAIL:
                observable = detailService.getMovies(uuid);
                break;
            default:
                observable = movieService.getMovies(title, year, type)
                        .map(new HttpResultFunc<List<Movie>>());
                break;
        }


        tosubscribe(observable, subscriber);
    }

    private <T> void tosubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }


    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            //if (!httpResult.getResponse()) {
            if (httpResult.getResponse().equalsIgnoreCase("False")) {
                throw new ApiException(100);
            }
            return httpResult.getMovies();
        }
    }
}
