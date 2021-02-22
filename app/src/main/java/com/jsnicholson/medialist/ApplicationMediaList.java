package com.jsnicholson.medialist;

import android.app.Application;
import android.content.Context;

import com.androidnetworking.AndroidNetworking;

public class ApplicationMediaList extends Application {

    private static Context m_Context;

    @Override
    public void onCreate() {
        super.onCreate();
        m_Context = this;
        AndroidNetworking.initialize(getApplicationContext());
    }

    public static Context GetContext() {
        return m_Context;
    }
}
