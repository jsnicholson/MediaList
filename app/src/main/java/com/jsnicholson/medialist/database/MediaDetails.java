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
}