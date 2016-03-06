package com.tanya.photogallery;

import android.content.Context;

import com.tanya.photogallery.models.Photo;

public class PhotoViewModel {

    public String name;
    public String description;
    public String camera;
    public String userName;
    public String imageUrl;

    public static PhotoViewModel from(Photo photo) {
        PhotoViewModel viewModel = new PhotoViewModel();
        viewModel.name = photo.name;
        viewModel.description = photo.description;
        viewModel.camera = photo.camera;
        viewModel.userName = photo.user.fullname;
        viewModel.imageUrl = photo.imageUrl;
        return viewModel;
    }

    public void openViewer(Context context) {
//        MediaViewerActivity.start(context, mMediaGroup, mViewKey);
    }

}
