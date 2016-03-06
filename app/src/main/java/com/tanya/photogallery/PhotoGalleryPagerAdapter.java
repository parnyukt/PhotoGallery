package com.tanya.photogallery;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanya.photogallery.databinding.ViewPhotoGalleryItemBinding;
import com.tanya.photogallery.models.Photo;
import com.tanya.photogallery.viewmodels.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryPagerAdapter extends FragmentStatePagerAdapter {

    final Context mContext;
    final List<PhotoViewModel> mCollection = new ArrayList<>();
    List<Photo> photos;
    private SlideItemFragment mCurrentFragment;

    public PhotoGalleryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mCollection.size();
    }


    @Override
    public Fragment getItem(int position) {
        SlideItemFragment fragment = new SlideItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(SlideItemFragment.SLIDE_VIEW_MODEL, mCollection.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentFragment = (SlideItemFragment) object;
    }

    public void setData(List<Photo> photos) {
        this.photos = photos;

        for (Photo slide : photos) {
            mCollection.add(PhotoViewModel.from(slide));
        }
    }

    public SlideItemFragment getCurrentFragment() {
        return mCurrentFragment;
    }

    public static class SlideItemFragment extends Fragment {

        public static final String SLIDE_VIEW_MODEL = "slide_view_model";

        private ViewPhotoGalleryItemBinding mBinding;

        @Nullable
        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            mBinding = ViewPhotoGalleryItemBinding
                    .inflate(LayoutInflater.from(getContext()), null);
            View itemView = mBinding.getRoot();


            PhotoViewModel viewModel = (PhotoViewModel) getArguments().getSerializable(
                    SLIDE_VIEW_MODEL);
            mBinding.setVm(viewModel);

            return itemView;
        }

    }

}
