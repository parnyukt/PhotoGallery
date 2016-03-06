package com.tanya.photogallery.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ShareCompat;
import android.view.View;

import com.tanya.photogallery.models.Photo;

import java.io.Serializable;

public class PhotoViewModel implements Serializable {

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

    public void share(View view) {
        Context context = view.getContext();

        ShareCompat.IntentBuilder intentBuilder = ShareCompat.IntentBuilder.from((Activity) context)
                .setText(imageUrl)
                .setSubject(description)
                .setType("text/plain")
                .setChooserTitle("Send a message via:");
        intentBuilder.startChooser();
    }
}
