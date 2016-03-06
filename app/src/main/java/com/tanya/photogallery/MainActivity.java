package com.tanya.photogallery;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tanya.photogallery.api.PhotoGalleryApi;
import com.tanya.photogallery.api.dto.PaginatedPhoto;
import com.tanya.photogallery.databinding.ActivityMainBinding;
import com.tanya.photogallery.models.Photo;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private final ExecutorService mExecutor = Executors.newCachedThreadPool();
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mExecutor.execute(() -> {
            PhotoGalleryApi api = new PhotoGalleryApi();
            PaginatedPhoto photo = null;
            try {
                photo = api.getPopularPhotos(2).execute().body();
                Photo[] photos = photo.photos;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



    }
}
