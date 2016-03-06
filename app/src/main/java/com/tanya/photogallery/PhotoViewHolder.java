package com.tanya.photogallery;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanya.photogallery.databinding.ViewPhotoBinding;
import com.tanya.photogallery.models.Photo;
import com.tanya.photogallery.viewmodels.PhotoViewModel;

import java.util.List;

public class PhotoViewHolder extends RecyclerView.ViewHolder {

    private final ViewPhotoBinding mBinding;
    private PhotoViewModel mPhotoViewModel;
    private List<Photo> mPhotos;

    private PhotoViewHolder(View itemView, List<Photo> photos) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
        mPhotos = photos;
    }

    public static PhotoViewHolder inflate(ViewGroup parent, List<Photo> photos) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_photo, parent, false);
        return new PhotoViewHolder(view, photos);
    }

    public void bind(PhotoViewModel mediaElementViewModel) {
        mPhotoViewModel = mediaElementViewModel;

        onBind(mediaElementViewModel);
    }

    protected void onBind(PhotoViewModel photoViewModel) {
        mBinding.setVm(photoViewModel);
        itemView.setOnClickListener(v -> viewSlide(v, getAdapterPosition()));
    }

    private void viewSlide(View view, int position) {
        PhotoGalleryActivity.start(view, mPhotos, position);
    }

}
