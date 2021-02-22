package com.jsnicholson.medialist.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsnicholson.medialist.AdapterSearchResult;
import com.jsnicholson.medialist.R;
import com.jsnicholson.medialist.database.DatabaseConstants;
import com.jsnicholson.medialist.database.TMDBConstants;
import com.jsnicholson.medialist.viewmodel.ViewModelSearch;
import com.jsnicholson.medialist.database.MediaSearchResult;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySearch extends AppCompatActivity {

    private ViewModelSearch m_viewModel;
    private AdapterSearchResult m_adapter;
    private TextView m_textNoResults;
    private ProgressBar m_Progress;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        m_viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ViewModelSearch.class);

        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText editSearch = findViewById(R.id.editSearch);
        editSearch.requestFocus();
        editSearch.addTextChangedListener(textSearchWatcher);

        m_textNoResults = findViewById(R.id.textNoResults);
        m_Progress = findViewById(R.id.progressSearching);

        RecyclerView listExercise = findViewById(R.id.listSearchResults);
        m_adapter = new AdapterSearchResult(this, this::OnMediaSearchResultClicked);
        listExercise.setLayoutManager(new LinearLayoutManager(this));
        listExercise.setAdapter(m_adapter);

        m_viewModel.m_listMediaSearchResult.observe(this, mediaSearchResult -> {
            m_Progress.setVisibility(View.GONE);
            m_adapter.SetData(mediaSearchResult);
        });

        m_viewModel.m_strNoResults.observe(this, string -> {
            m_textNoResults.setText(string);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intentUp = new Intent(ActivitySearch.this, ActivityMain.class);
                navigateUpTo(intentUp);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void OnMediaSearchResultClicked(MediaSearchResult exercise) {
        Intent intentReturn = new Intent();
        intentReturn.putExtra(TMDBConstants.EXTRA_TMDB_ID, exercise.idExternal);
        intentReturn.putExtra(DatabaseConstants.EXTRA_TYPE, exercise.type);
        setResult(Activity.RESULT_OK, intentReturn);
        finish();
    }

    TextWatcher textSearchWatcher = new TextWatcher() {
        private Timer timer = new Timer();
        private final long DELAY = 500; // Milliseconds

        @Override public void afterTextChanged(Editable s) {
            m_adapter.ClearData();
            m_Progress.setVisibility(View.VISIBLE);
            timer.cancel();
            timer = new Timer();
            timer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            m_viewModel.GetAllSearchResults(s.toString());
                        }
                    },
                    DELAY
            );
        }

        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };
}