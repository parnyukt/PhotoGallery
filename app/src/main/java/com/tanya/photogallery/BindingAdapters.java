package com.tanya.photogallery;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public final class BindingAdapters {

    private BindingAdapters() {
    }

    @BindingAdapter("resource")
    public static void setImageResource(ImageView view, int res) {
        if (res != 0) {
            view.setImageResource(res);
        }
    }

    @BindingAdapter(value = {"imageUrl", "placeholderImage"},
            requireAll = false)
    public static void setImageUrl(ImageView imageView, String url, int placeholder) {
        Context context = imageView.getContext();
        DrawableRequestBuilder builder = Glide.with(context).load(url).animate(R.anim.cross_fade);

        if (placeholder != 0) {
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            builder.placeholder(placeholder).into(
                    new GlideDrawableImageViewTarget(imageView) {
                        @Override
                        public void onResourceReady(
                                GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {

                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            super.onResourceReady(resource, animation);
                        }
                    });
        } else {
            builder.into(imageView);
        }
    }

}
