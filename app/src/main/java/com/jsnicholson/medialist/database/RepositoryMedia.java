package com.jsnicholson.medialist.database;

import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
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
        AndroidNetworking.get(TMDBConstants.ENDPOINT_TMDB_SEARCH)
                .addPathParameter(TMDBConstants.PARAM_TMDB_API_KEY, BuildConfig.API_KEY_TMDB)
                .addPathParameter(TMDBConstants.PARAM_TMDB_QUERY, strQuery)
                .setPriority(Priority.HIGH)
                .build() 
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<MediaSearchResult> list = ConvertUtils.BuildMediaSearchResults(response);
                            iCallbackSearchResult.CallbackSearchResult(list);
                        } catch (JSONException e) { e.printStackTrace(); }
                    }

                    @Override
                    public void onError(ANError error) {}
                });
    }

    public void GetSingleMedia(int type, long id, Callback.ICallbackSingleMedia iCallbackSingleMedia) {
        AndroidNetworking.get(TMDBConstants.ENDPOINT_TMDB_SINGLE_MEDIA)
                .addPathParameter(TMDBConstants.PARAM_TMDB_API_KEY, BuildConfig.API_KEY_TMDB)
                .addPathParameter(TMDBConstants.PARAM_TMDB_TYPE, DatabaseConstants.MAP_MEDIA_TYPE_TO_STRING.get(type).toLowerCase())
                .addPathParameter(TMDBConstants.PARAM_TMDB_ID, String.valueOf(id))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            MediaDetails details = ConvertUtils.BuildMediaDetails(type, response);
                            iCallbackSingleMedia.CallbackSingleMedia(details);
                        } catch (JSONException e) { e.printStackTrace(); }
                    }

                    @Override
                    public void onError(ANError error) {}
                });
    }
}