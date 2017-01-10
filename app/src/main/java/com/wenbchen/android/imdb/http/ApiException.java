package com.wenbchen.android.imdb.http;

/**
 * Created by wenbchen on 12/2/16.
 */

public class ApiException extends RuntimeException {

    public static final int MOVIE_NOT_FOUND = 100;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    private static String getApiExceptionMessage(int code) {
        String msg = "";
        switch(code) {
            case MOVIE_NOT_FOUND:
                msg = "No Movie Found";
                break;
            default:
                msg = "Unknown Error";
                break;
        }
        return msg;
    }
}
