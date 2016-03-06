package com.tanya.photogallery;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanya.photogallery.databinding.ViewPhotoBinding;

public class PhotoViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private final ViewPhotoBinding mBinding;
    private PhotoViewModel mPhotoViewModel;

    private PhotoViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mBinding = DataBindingUtil.bind(itemView);
    }

    public static PhotoViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    public void bind(PhotoViewModel mediaElementViewModel) {
        mPhotoViewModel = mediaElementViewModel;

        onBind(mediaElementViewModel);
    }

    @Override
    public void onClick(View v) {

        if (mPhotoViewModel != null) {
            mPhotoViewModel.openViewer(v.getContext());
        }
    }

    protected void onBind(PhotoViewModel photoViewModel) {
        mBinding.setVm(photoViewModel);

//        mBinding.mediumIcon.setImageResource(photoViewModel.mediumTypeIconId);
//
//        mBinding.ratingBar.setRating(photoViewModel.ratingPercentage);
//        mBinding.ratingBar.showStaticProgress(false);
    }


}
