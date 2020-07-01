package com.aloardanil.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aloardanil.R;
import com.aloardanil.adapter.ImagesAdapter;
import com.aloardanil.view.detail.DetailActivity;
import com.aloardanil.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView rvHome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rvHome = root.findViewById(R.id.rv_home);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rvHome.setLayoutManager(linearLayoutManager);
        rvHome.setDrawingCacheEnabled(true);
        rvHome.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        rvHome.setNestedScrollingEnabled(true);
        final ImagesAdapter imagesAdapter = new ImagesAdapter(getContext(), getActivity());

        homeViewModel.getData().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> s) {
                imagesAdapter.setItem(s);
                rvHome.setAdapter(imagesAdapter);
                imagesAdapter.setImageClickListener(new ImagesAdapter.OnImageClickListener() {
                    @Override
                    public void onClick(View v, String image, int position) {
                        startActivity(new Intent(getActivity(), DetailActivity.class));
                    }
                });
            }
        });
    }
}