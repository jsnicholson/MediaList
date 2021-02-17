package com.jsnicholson.medialist;

import com.jsnicholson.medialist.database.MediaSearchResult;

import java.util.List;

public class Callback {
    public interface ICallbackSearchResult {
        void CallbackSearchResult(List<MediaSearchResult> list);
    }
}