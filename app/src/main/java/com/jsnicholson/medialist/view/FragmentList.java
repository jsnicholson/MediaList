package com.jsnicholson.medialist.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jsnicholson.medialist.R;
import com.jsnicholson.medialist.view.ActivitySearch;

public class FragmentList extends Fragment {

    @Override
    // initialise non ui elements
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @NonNull
    // initialise UI elements
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        FloatingActionButton fab = view.findViewById(R.id.buttonAdd);
        fab.setOnClickListener(listenerNewMedia);
        return view;
    }

    private View.OnClickListener listenerNewMedia = v -> {
        Intent intentSearch = new Intent(getActivity(), ActivitySearch.class);
        startActivityForResult(intentSearch, 1);
    };

}
