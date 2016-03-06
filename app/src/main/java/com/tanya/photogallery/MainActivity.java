package com.tanya.photogallery;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tanya.photogallery.api.PhotoGalleryApi;
import com.tanya.photogallery.api.dto.PaginatedPhoto;
import com.tanya.photogallery.databinding.ActivityMainBinding;
import com.tanya.photogallery.models.Photo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private final ExecutorService mExecutor = Executors.newCachedThreadPool();
    private ActivityMainBinding mBinding;
    private Context mContext;
    private List<Photo> mPhotos;

    private RecyclerView mPhotoRecycleView;
    private PhotoAdapter mPhotoAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        GetDataTask getDataTask = new GetDataTask();
        getDataTask.execute();
    }

    private void setLayout() {
        mPhotoRecycleView = mBinding.contentRecyclerView;

        mPhotoRecycleView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(mContext, 2);
        mPhotoRecycleView.setLayoutManager(mLayoutManager);

        mPhotoAdapter = new PhotoAdapter(mPhotos);
        mPhotoRecycleView.setAdapter(mPhotoAdapter);

    }

//    private void getPhotosData(){
//        mExecutor.execute(() -> {
//            PhotoGalleryApi api = new PhotoGalleryApi();
//            PaginatedPhoto paginatedPhoto = null;
//            try {
//                paginatedPhoto = api.getPopularPhotos(2).execute().body();
//                mPhotos = paginatedPhoto.photos;
//
//                setLayout();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            PhotoGalleryApi api = new PhotoGalleryApi();
            PaginatedPhoto paginatedPhoto = null;
            try {
                paginatedPhoto = api.getPopularPhotos(2).execute().body();
                mPhotos = paginatedPhoto.photos;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            setLayout();
        }
    }
}
