package com.tanya.photogallery.api;

import com.tanya.photogallery.api.dto.PaginatedPhoto;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface PhotoGalleryService {

    @GET("photos")
    Call<PaginatedPhoto> getPhotos(
            @Query("feature") String feature,
            @Query("consumer_key") String consumerKey,
            @Query("page") int page);
}
