package com.jsnicholson.medialist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.jsnicholson.medialist.database.RepositoryMedia;

public class ViewModelSingleMedia extends AndroidViewModel {
    private final RepositoryMedia m_repository;

    public ViewModelSingleMedia(@NonNull Application application) {
        super(application);
        m_repository = new RepositoryMedia(application);
    }
}
