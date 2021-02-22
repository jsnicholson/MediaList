package com.jsnicholson.medialist.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsnicholson.medialist.viewmodel.FragmentStatusViewModel;
import com.jsnicholson.medialist.R;

public class FragmentStatus extends Fragment {

    private FragmentStatusViewModel mViewModel;

    public static FragmentStatus newInstance() {
        return new FragmentStatus();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentStatusViewModel.class);
        // TODO: Use the ViewModel
    }

}