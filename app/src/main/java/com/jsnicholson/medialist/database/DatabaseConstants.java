package com.jsnicholson.medialist.database;

import com.google.common.collect.ImmutableMap;
import com.jsnicholson.medialist.R;
import com.jsnicholson.medialist.StringResources;

import java.util.Map;

public class DatabaseConstants {

    public static final String TABLE_MEDIA_ENTRY = "media_entry";

    public static final String MEDIA_ENTRY_ID = "media_entry_id";
    public static final String MEDIA_ENTRY_ID_EXTERNAL = "media_entry_id_external";
    public static final String MEDIA_ENTRY_STATUS = "media_entry_status";
    public static final String MEDIA_ENTRY_RATING = "media_entry_rating";

    public static final String MEDIA_DETAIL_TYPE = "media_detail_type";
    public static final String MEDIA_DETAIL_TITLE = "media_detail_title";
    public static final String MEDIA_DETAIL_RELEASED = "media_detail_released";
    public static final String MEDIA_DETAIL_OVERVIEW = "media_detail_overview";
    public static final String MEDIA_DETAIL_POSTER = "media_detail_poster";
    public static final String MEDIA_DETAIL_EPISODE_TOTAL = "media_details_episode_total";
    public static final String MEDIA_DETAIL_RUNTIME = "media_detail_runtime";

    public static final int MEDIA_TYPE_MOVIE = 1;
    public static final int MEDIA_TYPE_TV = 2;
    public static final int MEDIA_TYPE_BOOK = 3;
    public static final int MEDIA_TYPE_ANIME = 4;

    public static final Map<Integer, String> MAP_MEDIA_TYPE_TO_STRING = ImmutableMap.of(
            MEDIA_TYPE_MOVIE, StringResources.Get(R.string.media_type_movie),
            MEDIA_TYPE_TV, StringResources.Get(R.string.media_type_tv),
            MEDIA_TYPE_BOOK, StringResources.Get(R.string.media_type_book),
            MEDIA_TYPE_ANIME, StringResources.Get(R.string.media_type_anime));

    public static final int MEDIA_STATUS_ACTIVE = 1;
    public static final int MEDIA_STATUS_COMPLETED = 2;
    public static final int MEDIA_STATUS_PLANNED = 3;
    public static final int MEDIA_STATUS_ONHOLD = 4;
    public static final int MEDIA_STATUS_DROPPED = 5;

    public static final Map<Integer, String> MAP_MEDIA_STATUS_TO_STRING = ImmutableMap.of(
            MEDIA_STATUS_ACTIVE, StringResources.Get(R.string.status_active),
            MEDIA_STATUS_COMPLETED, StringResources.Get(R.string.status_completed),
            MEDIA_STATUS_PLANNED, StringResources.Get(R.string.status_planned),
            MEDIA_STATUS_ONHOLD, StringResources.Get(R.string.status_onhold),
            MEDIA_STATUS_DROPPED, StringResources.Get(R.string.status_dropped));

    public static final int VALUE_NOTFOUND = 0;
    public static final int VALUE_UNRELEASED = 1;

    public static final String EXTRA_TYPE = "extra_type";
}
