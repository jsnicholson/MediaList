package com.jsnicholson.medialist.database;

import android.app.Application;
import android.provider.MediaStore;
import android.util.Log;

import androidx.room.Database;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jsnicholson.medialist.BuildConfig;
import com.jsnicholson.medialist.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RepositoryMedia {

    private final DaoMediaEntry m_daoMediaEntry;

    public RepositoryMedia(Application application) {
        DatabaseMedia db = DatabaseMedia.getDatabase(application);

        m_daoMediaEntry = db.daoMediaEntry();
    }

    public void GetSearchResults(String strQuery, Callback.ICallbackSearchResult iCallbackSearchResult) {
        AndroidNetworking.get(DatabaseConstants.TMDB_ENDPOINT_SEARCH)
                .addPathParameter(DatabaseConstants.TMDB_ENDPOINT_PARAM_API_KEY, BuildConfig.API_KEY_TMDB)
                .addPathParameter(DatabaseConstants.TMDB_ENDPOINT_PARAM_QUERY, strQuery)
                .setPriority(Priority.HIGH)
                .build() 
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<MediaSearchResult> list = BuildMediaSearchResults(response);
                            iCallbackSearchResult.CallbackSearchResult(list);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private List<MediaSearchResult> BuildMediaSearchResults(JSONObject json) throws JSONException {
        List<MediaSearchResult> listMediaSearchResult = new ArrayList<>();
        JSONArray arrayResults = json.getJSONArray(DatabaseConstants.TMDB_RESPONSE_KEY_RESULTS);
        for(int i = 0; i < arrayResults.length(); i++) {
            JSONObject o = arrayResults.getJSONObject(i);

            String type = o.getString(DatabaseConstants.TMDB_RESPONSE_KEY_MEDIATYPE);
            if(!(type.equals(DatabaseConstants.TMDB_RESPONSE_VALUE_MOVIE) || type.equals(DatabaseConstants.TMDB_RESPONSE_VALUE_TV)))
                continue;

            int idExternal = o.getInt(DatabaseConstants.TMDB_RESPONSE_KEY_ID);

            String title = "";
            String dateRaw = "";
            if(type.equals(DatabaseConstants.TMDB_RESPONSE_VALUE_MOVIE)) {
                title = o.getString(DatabaseConstants.TMDB_RESPONSE_KEY_TITLE);

                if(o.has(DatabaseConstants.TMDB_RESPONSE_KEY_RELEASEDATE))
                    dateRaw = o.getString(DatabaseConstants.TMDB_RESPONSE_KEY_RELEASEDATE);
            } else if(type.equals(DatabaseConstants.TMDB_RESPONSE_VALUE_TV)) {
                title = o.getString(DatabaseConstants.TMDB_RESPONSE_KEY_NAME);

                if(o.has(DatabaseConstants.TMDB_RESPONSE_KEY_FIRSTAIRDATE))
                    dateRaw = o.getString(DatabaseConstants.TMDB_RESPONSE_KEY_FIRSTAIRDATE);
            }

            int released = DatabaseConstants.VALUE_NOTFOUND;
            if(!dateRaw.equals(""))
                released = Integer.parseInt(dateRaw.substring(0, 4));

            int popularity = o.getInt(DatabaseConstants.TMDB_RESPONSE_KEY_POPULARITY);
            boolean isAnime = IsAnime(o);

            MediaSearchResult searchResult = new MediaSearchResult(type, idExternal, title, released, popularity, isAnime);
            listMediaSearchResult.add(searchResult);
        }
        return listMediaSearchResult;
    }

    public boolean IsAnime(JSONObject json) throws JSONException {
        JSONArray arrGenres = json.getJSONArray(DatabaseConstants.TMDB_RESPONSE_KEY_GENRE_IDS);
        List<Integer> listGenres = new ArrayList<>();
        for(int i =0; i < arrGenres.length(); i++)
            listGenres.add(arrGenres.getInt(i));
        String strLang = json.getString(DatabaseConstants.TMDB_RESPONSE_KEY_ORIGINAL_LANGUAGE);
        return (listGenres.contains(DatabaseConstants.TMDB_RESPONSE_VALUE_ANIMATION) && strLang.equals(DatabaseConstants.TMDB_RESPONSE_VALUE_JAPANESE));
    }
}