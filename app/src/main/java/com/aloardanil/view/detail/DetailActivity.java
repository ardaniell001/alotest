package com.aloardanil.view.detail;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aloardanil.R;
import com.aloardanil.viewmodel.DetailViewModel;
import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private CarouselView carouselView;
    private DetailViewModel detailViewModel;
    private ArrayList<String> sampleImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailViewModel =
                ViewModelProviders.of(this).get(DetailViewModel.class);

        carouselView = findViewById(R.id.carouselView);

        detailViewModel.getData().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> s) {
                sampleImages = s;
                carouselView.setPageCount(s.size());
                carouselView.setImageListener(imageListener);
            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);
            Glide.with(DetailActivity.this)
                    .load(sampleImages.get(position))
                    .placeholder(getResources().getDrawable(R.drawable.default_image))
                    .into(imageView);
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}