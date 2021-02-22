package com.jsnicholson.medialist;

import com.jsnicholson.medialist.database.MediaDetails;
import com.jsnicholson.medialist.database.MediaSearchResult;

import java.util.List;

public class Callback {
    public interface ICallbackSearchResult {
        void CallbackSearchResult(List<MediaSearchResult> list);
    }

    public interface ICallbackSingleMedia {
        void CallbackSingleMedia(MediaDetails details);
    }
}