package com.wenbchen.android.imdb.model;

public class WatchedMovie {
	public static final String TAG = "WatchedMovie";
	
	  private long id;
	  private int isWatched;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public int getWatched() {
	    return isWatched;
	  }

	  public void setWatched(int isWatched) {
	    this.isWatched = isWatched;
	  }
	} 
