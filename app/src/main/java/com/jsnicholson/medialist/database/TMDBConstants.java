package com.jsnicholson.medialist.database;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class TMDBConstants {
    public static final String ENDPOINT_TMDB_SEARCH = "https://api.themoviedb.org/3/search/multi?api_key={api_key}&language=en-US&query={query}&page=1&include_adult=false";
    public static final String PARAM_TMDB_API_KEY = "api_key";
    public static final String PARAM_TMDB_QUERY = "query";

    public static final String ENDPOINT_TMDB_SINGLE_MEDIA = "https://api.themoviedb.org/3/{type}/{id}?api_key={api_key}";
    public static final String PARAM_TMDB_TYPE = "type";
    public static final String PARAM_TMDB_ID = "id";

    public static final String ENDPOINT_TMDB_IMAGE = "https://image.tmdb.org/t/p/w500{image_path}";
    public static final String PARAM_TMDB_IMAGE_PATH = "{image_path}";

    public static final String URL_SINGLE_MEDIA = "https://www.themoviedb.org/type/id";

    public static final String RESPONSE_KEY_TMDB_RESULTS = "results";
    public static final String RESPONSE_KEY_TMDB_ID = "id";
    public static final String RESPONSE_KEY_TMDB_TITLE = "title";
    public static final String RESPONSE_KEY_TMDB_NAME = "name";
    public static final String RESPONSE_KEY_TMDB_MEDIATYPE = "media_type";
    public static final String RESPONSE_KEY_TMDB_RELEASEDATE = "release_date";
    public static final String RESPONSE_KEY_TMDB_FIRSTAIRDATE = "first_air_date";
    public static final String RESPONSE_KEY_TMDB_POPULARITY = "popularity";
    public static final String RESPONSE_KEY_TMDB_GENREIDS = "genre_ids";
    public static final String RESPONSE_KEY_TMDB_ORIGINALLANG = "original_language";
    public static final String RESPONSE_KEY_TMDB_OVERVIEW = "overview";
    public static final String RESPONSE_KEY_TMDB_POSTER_PATH = "poster_path";
    public static final String RESPONSE_KEY_TMDB_EPISODE_TOTAL = "number_of_episodes";
    public static final String RESPONSE_KEY_TMDB_RUNTIME = "runtime";

    public static final String RESPONSE_VALUE_TMDB_MOVIE = "movie";
    public static final String RESPONSE_VALUE_TMDB_TV = "tv";
    public static final String RESPONSE_VALUE_TMDB_JAPANESE = "ja";
    public static final int RESPONSE_VALUE_TMDB_ANIMATION = 16;

    public static final String EXTRA_TMDB_ID = "tmdb_id";

    public static final Map<String, Integer> MAP_TMDB_TYPE_TO_INT = ImmutableMap.of(
            RESPONSE_VALUE_TMDB_MOVIE, DatabaseConstants.MEDIA_TYPE_MOVIE,
            RESPONSE_VALUE_TMDB_TV, DatabaseConstants.MEDIA_TYPE_TV);
}
