package com.jsnicholson.medialist;

public class StringResources {
    public static String Get(int resourceId) {
        return ApplicationMediaList.GetContext().getResources().getString(resourceId);
    }

    public static android.content.res.Resources GetResources() {
        return ApplicationMediaList.GetContext().getResources();
    }
}
