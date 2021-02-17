package com.jsnicholson.medialist;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class ApplicationMediaList extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(getApplicationContext());
    }
}
