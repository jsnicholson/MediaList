package com.jsnicholson.medialist.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class MediaDetails {

    @NonNull
    @ColumnInfo(name = DatabaseConstants.MEDIA_DETAIL_TYPE)
    public int type;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.MEDIA_DETAIL_TITLE)
    public String title;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.MEDIA_DETAIL_RELEASED)
    public int released;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.MEDIA_DETAIL_OVERVIEW)
    public String overview;

    @NonNull
    @ColumnInfo(name = DatabaseConstants.MEDIA_DETAIL_POSTER)
    public String poster;

    @ColumnInfo(name = DatabaseConstants.MEDIA_DETAIL_EPISODE_TOTAL)
    public int episodeTotal;

    @ColumnInfo(name = DatabaseConstants.MEDIA_DETAIL_RUNTIME)
    public int runtime;

    public MediaDetails() {}

    public MediaDetails(int type, String title, int released, String overview, String poster, int episodeTotal, int runtime) {
        this.type = type;
        this.title = title;
        this.released = released;
        this.overview = overview;
        this.poster = poster;
        this.episodeTotal = episodeTotal;
        this.runtime = runtime;
    }
}