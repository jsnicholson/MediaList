package com.jsnicholson.medialist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.jsnicholson.medialist.R;
import com.jsnicholson.medialist.database.MediaDetails;
import com.jsnicholson.medialist.database.MediaSearchResult;
import com.jsnicholson.medialist.database.RepositoryMedia;

import java.util.ArrayList;
import java.util.List;

public class ViewModelSingleMedia extends AndroidViewModel {

    private final RepositoryMedia m_Repository;
    public MutableLiveData<MediaDetails> m_Details = new MutableLiveData<>();

    public ViewModelSingleMedia(@NonNull Application application) {
        super(application);
        m_Repository = new RepositoryMedia(application);
    }

    public void GetSingleMedia(int type, long id) {
        m_Repository.GetSingleMedia(type, id, (MediaDetails details) -> {
            m_Details.postValue(details);
        });
    }
}
