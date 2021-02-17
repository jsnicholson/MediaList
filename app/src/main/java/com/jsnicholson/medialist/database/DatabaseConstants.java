package com.jsnicholson.medialist.database;

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

    public static final int MEDIA_TYPE_FILM = 0;
    public static final int MEDIA_TYPE_TV = 1;
    public static final int MEDIA_TYPE_BOOK = 2;
    public static final int MEDIA_TYPE_ANIME = 3;

    public static final int MEDIA_STATUS_ACTIVE = 0;
    public static final int MEDIA_STATUS_COMPLETED = 1;
    public static final int MEDIA_STATUS_PLANNED = 2;
    public static final int MEDIA_STATUS_ONHOLD = 3;
    public static final int MEDIA_STATUS_DROPPED = 4;

    public static final String TMDB_ENDPOINT_SEARCH = "https://api.themoviedb.org/3/search/multi?api_key={api_key}&language=en-US&query={query}&page=1&include_adult=false";
    public static final String TMDB_ENDPOINT_PARAM_API_KEY = "api_key";
    public static final String TMDB_ENDPOINT_PARAM_QUERY = "query";

    public static final String TMDB_RESPONSE_KEY_RESULTS = "results";
    public static final String TMDB_RESPONSE_KEY_ID = "id";
    public static final String TMDB_RESPONSE_KEY_TITLE = "title";
    public static final String TMDB_RESPONSE_KEY_NAME = "name";
    public static final String TMDB_RESPONSE_KEY_MEDIATYPE = "media_type";
    public static final String TMDB_RESPONSE_KEY_RELEASEDATE = "release_date";
    public static final String TMDB_RESPONSE_KEY_FIRSTAIRDATE = "first_air_date";
    public static final String TMDB_RESPONSE_KEY_POPULARITY = "popularity";
    public static final String TMDB_RESPONSE_KEY_GENRE_IDS = "genre_ids";
    public static final String TMDB_RESPONSE_KEY_ORIGINAL_LANGUAGE = "original_language";

    public static final String TMDB_RESPONSE_VALUE_MOVIE = "movie";
    public static final String TMDB_RESPONSE_VALUE_TV = "tv";
    public static final String TMDB_RESPONSE_VALUE_JAPANESE = "ja";
    public static final int TMDB_RESPONSE_VALUE_ANIMATION = 16;

    public static final int VALUE_NOTFOUND = 0;
    public static final int VALUE_UNRELEASED = 1;
}
