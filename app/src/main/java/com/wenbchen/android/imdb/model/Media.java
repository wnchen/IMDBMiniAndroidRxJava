package com.wenbchen.android.imdb.model;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.wenbchen.android.imdb.util.UtilsString;

public class Media {
	public static final String TAG = "Media";

	private String title, thumbnailUrl;
	private String year;
	private String rating;
	private String uuid;
	private String type;
	private ArrayList<String> genre;

	public Media() {
	}

	public Media(String name, String thumbnailUrl, String year, String rating, String uuid,
			ArrayList<String> genre) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.year = year;
		this.rating = rating;
		this.uuid = uuid;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public ArrayList<String> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}
	
//	public static Movie detailMovieFromJson(String json) {
//    	Movie movie = new Movie();
//		
//    	try {
//			JSONObject jObject = new JSONObject(json);
//			movie.setTitle(jObject.getString(TITLE_KEY));
//			movie.setYear(jObject.getString(YEAR_KEY));
//			movie.setUuid(jObject.getString(ID_KEY));
//			movie.setRating(jObject.getString(RATING_KEY));
//			movie.setThumbnailUrl(jObject.getString(POSTER_KEY));
//		} catch (JSONException e) {
//			return null;
//		}
//    	return movie;
//    }
	
	public static Media movieFromJson(String json) {
    	Media media = new Media();
    	try {
			JSONObject jObject = new JSONObject(json);
			media.setTitle(jObject.getString(UtilsString.TITLE_KEY));
			media.setYear(jObject.getString(UtilsString.YEAR_KEY));
			media.setUuid(jObject.getString(UtilsString.ID_KEY));
			media.setThumbnailUrl(jObject.getString(UtilsString.POSTER_KEY));
			media.setType(jObject.getString(UtilsString.TYPE_KEY));
		} catch (JSONException e) {
			return null;
		}
    	return media;
    }

}
