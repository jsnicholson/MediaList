package com.jsnicholson.medialist.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {

    public static List<MediaSearchResult> BuildMediaSearchResults(JSONObject json) throws JSONException {
        List<MediaSearchResult> listMediaSearchResult = new ArrayList<>();
        JSONArray arrayResults = json.getJSONArray(TMDBConstants.RESPONSE_KEY_TMDB_RESULTS);
        for(int i = 0; i < arrayResults.length(); i++) {
            JSONObject o = arrayResults.getJSONObject(i);

            String strType = o.getString(TMDBConstants.RESPONSE_KEY_TMDB_MEDIATYPE);
            if(!TMDBConstants.MAP_TMDB_TYPE_TO_INT.containsKey(strType))
                continue;

            int type = TMDBConstants.MAP_TMDB_TYPE_TO_INT.get(strType);
            int idExternal = o.getInt(TMDBConstants.RESPONSE_KEY_TMDB_ID);

            String title = "";
            String dateRaw = "";
            if(type == DatabaseConstants.MEDIA_TYPE_MOVIE) {
                title = o.getString(TMDBConstants.RESPONSE_KEY_TMDB_TITLE);

                if(o.has(TMDBConstants.RESPONSE_KEY_TMDB_RELEASEDATE))
                    dateRaw = o.getString(TMDBConstants.RESPONSE_KEY_TMDB_RELEASEDATE);
            } else if(type == DatabaseConstants.MEDIA_TYPE_TV) {
                title = o.getString(TMDBConstants.RESPONSE_KEY_TMDB_NAME);

                if(o.has(TMDBConstants.RESPONSE_KEY_TMDB_FIRSTAIRDATE))
                    dateRaw = o.getString(TMDBConstants.RESPONSE_KEY_TMDB_FIRSTAIRDATE);
            }

            int released = DatabaseConstants.VALUE_NOTFOUND;
            if(!dateRaw.equals(""))
                released = Integer.parseInt(dateRaw.substring(0, 4));

            int popularity = o.getInt(TMDBConstants.RESPONSE_KEY_TMDB_POPULARITY);
            boolean isAnime = IsAnime(o);

            MediaSearchResult searchResult = new MediaSearchResult(type, idExternal, title, released, popularity, isAnime);
            listMediaSearchResult.add(searchResult);
        }
        return listMediaSearchResult;
    }

    public static MediaDetails BuildMediaDetails(int type, JSONObject json) throws JSONException {
        String title = "";
        String dateRaw = "";
        int episodeTotal = 0;
        int runtime = 0;
        if(type == DatabaseConstants.MEDIA_TYPE_MOVIE) {
            title = json.getString(TMDBConstants.RESPONSE_KEY_TMDB_TITLE);

            dateRaw = json.getString(TMDBConstants.RESPONSE_KEY_TMDB_RELEASEDATE);

            runtime = json.getInt(TMDBConstants.RESPONSE_KEY_TMDB_RUNTIME);
        } else if(type == DatabaseConstants.MEDIA_TYPE_TV) {
            title = json.getString(TMDBConstants.RESPONSE_KEY_TMDB_NAME);

            dateRaw = json.getString(TMDBConstants.RESPONSE_KEY_TMDB_FIRSTAIRDATE);

            episodeTotal = json.getInt(TMDBConstants.RESPONSE_KEY_TMDB_EPISODE_TOTAL);
        }
        int released = DatabaseConstants.VALUE_NOTFOUND;
        if(!dateRaw.equals(""))
            released = Integer.parseInt(dateRaw.substring(0, 4));

        String overview = json.getString(TMDBConstants.RESPONSE_KEY_TMDB_OVERVIEW);
        String poster = json.getString(TMDBConstants.RESPONSE_KEY_TMDB_POSTER_PATH);

        return new MediaDetails(type, title, released, overview, poster, episodeTotal, runtime);
    }

    public static boolean IsAnime(JSONObject json) throws JSONException {
        JSONArray arrGenres = json.getJSONArray(TMDBConstants.RESPONSE_KEY_TMDB_GENREIDS);
        List<Integer> listGenres = new ArrayList<>();
        // need a check for being an id
        for(int i =0; i < arrGenres.length(); i++)
            listGenres.add(arrGenres.getInt(i));
        String strLang = json.getString(TMDBConstants.RESPONSE_KEY_TMDB_ORIGINALLANG);
        return (listGenres.contains(TMDBConstants.RESPONSE_VALUE_TMDB_ANIMATION) && strLang.equals(TMDBConstants.RESPONSE_VALUE_TMDB_JAPANESE));
    }
}
