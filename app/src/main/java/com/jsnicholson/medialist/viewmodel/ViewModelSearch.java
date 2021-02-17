package com.jsnicholson.medialist.viewmodel;

import android.app.Application;
import android.database.Observable;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.jsnicholson.medialist.R;
import com.jsnicholson.medialist.database.MediaSearchResult;
import com.jsnicholson.medialist.database.RepositoryMedia;

import java.util.ArrayList;
import java.util.List;

public class ViewModelSearch extends AndroidViewModel {

    private final RepositoryMedia m_repository;
    public MutableLiveData<List<MediaSearchResult>> m_listMediaSearchResult = new MutableLiveData<>();
    public MutableLiveData<String> m_strNoResults = new MutableLiveData<>();

    public ViewModelSearch(@NonNull Application application) {
        super(application);
        m_repository = new RepositoryMedia(application);
    }

    public void GetAllSearchResults(String strQuery) {
        if(strQuery.equals("")) {
            m_listMediaSearchResult.postValue(new ArrayList<>());
            m_strNoResults.postValue("");
        }

        m_repository.GetSearchResults(strQuery, (List<MediaSearchResult> list) -> {
            m_listMediaSearchResult.setValue(list);

            if(list.isEmpty())
                m_strNoResults.postValue(getApplication().getResources().getString(R.string.no_results));
            else
                m_strNoResults.postValue("");
        });
    }
}
