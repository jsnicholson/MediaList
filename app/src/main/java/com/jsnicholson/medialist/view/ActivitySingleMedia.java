package com.jsnicholson.medialist.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Database;

import com.androidnetworking.widget.ANImageView;
import com.jsnicholson.medialist.R;
import com.jsnicholson.medialist.StringResources;
import com.jsnicholson.medialist.database.DatabaseConstants;
import com.jsnicholson.medialist.database.TMDBConstants;
import com.jsnicholson.medialist.viewmodel.ViewModelSearch;
import com.jsnicholson.medialist.viewmodel.ViewModelSingleMedia;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ActivitySingleMedia extends AppCompatActivity {

    private ViewModelSingleMedia m_viewModel;
    private TextView textTitle;
    private TextView textOverview;
    private TextView textReleased;
    private TextView textLength;
    private ANImageView imagePoster;
    private long lExternalId;
    private String strUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_media);

        m_viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ViewModelSingleMedia.class);

        Toolbar toolbar = findViewById(R.id.toolbarSingle);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textTitle = findViewById(R.id.textTitle);
        textOverview = findViewById(R.id.textOverview);
        textReleased = findViewById(R.id.textReleased);
        textLength = findViewById(R.id.textLength);
        imagePoster = findViewById(R.id.imagePoster);

        imagePoster.setDefaultImageResId(R.mipmap.ic_launcher);

        lExternalId = getIntent().getLongExtra(TMDBConstants.EXTRA_TMDB_ID, 0);
        int iType = getIntent().getIntExtra(DatabaseConstants.EXTRA_TYPE, 0);

        if(lExternalId == 0 || iType == 0)
            // this should error out here
            Log.d("medialist", "TYPE OR ID NOT FOUND");

        m_viewModel.GetSingleMedia(iType, lExternalId);

        m_viewModel.m_Details.observe(this, mediaDetails -> {
            if(mediaDetails.episodeTotal != 0)
                textLength.setText(StringResources.GetResources().getString(R.string.episodes, mediaDetails.episodeTotal));
            else if(mediaDetails.runtime != 0)
                textLength.setText(StringResources.GetResources().getString(R.string.format_movie_length,(int)mediaDetails.runtime/60, mediaDetails.runtime%60));
            textTitle.setText(mediaDetails.title);
            textOverview.setText(mediaDetails.overview);
            textReleased.setText(String.valueOf(mediaDetails.released));
            imagePoster.setImageUrl(TMDBConstants.ENDPOINT_TMDB_IMAGE.replace(TMDBConstants.PARAM_TMDB_IMAGE_PATH, mediaDetails.poster));
            strUrl = TMDBConstants.URL_SINGLE_MEDIA;
            strUrl = strUrl.replace(TMDBConstants.PARAM_TMDB_TYPE, DatabaseConstants.MAP_MEDIA_TYPE_TO_STRING.get(mediaDetails.type).toLowerCase());
            strUrl = strUrl.replace(TMDBConstants.PARAM_TMDB_ID, String.valueOf(lExternalId));
            Log.d("medialist", strUrl);
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.frameFragment,
                new FragmentList()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intentUp = new Intent(ActivitySingleMedia.this, ActivityMain.class);
                navigateUpTo(intentUp);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void OnButtonClick(View view) {
        Log.d("medialist", "OnButtonClick");
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.primary));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(strUrl));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("medialist", getClass().getSimpleName() + " destroyed");
    }
}
