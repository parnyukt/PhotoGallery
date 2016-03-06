package com.tanya.photogallery;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.tanya.photogallery.databinding.ActivityPhotosGalleryBinding;
import com.tanya.photogallery.models.Photo;

import java.io.Serializable;
import java.util.List;

public class PhotoGalleryActivity extends AppCompatActivity {

    private static final String ARG_PHOTOS = "Photos";
    private static final String ARG_CURRENT_ITEM = "CurrentItem";

    private ActivityPhotosGalleryBinding mBinding;

    private PhotoGalleryPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private List<Photo> photos;

    public static void start(
            View view,
            List<Photo> photos,
            int currentItem) {

        Context context = view.getContext();
        Intent photoGalleryIntent = new Intent(context, PhotoGalleryActivity.class);
        photoGalleryIntent.putExtra(ARG_PHOTOS, (Serializable) photos);
        photoGalleryIntent.putExtra(ARG_CURRENT_ITEM, currentItem);

        context.startActivity(photoGalleryIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photos_gallery);

        photos = (List<Photo>) getIntent().getExtras().getSerializable(ARG_PHOTOS);

        mPagerAdapter = new PhotoGalleryPagerAdapter(getSupportFragmentManager(), this);
        mPagerAdapter.setData(photos);

        int currentItem = getIntent().getIntExtra(ARG_CURRENT_ITEM, 0);

        mViewPager = mBinding.imagesViewPager;
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(currentItem);
    }
}
