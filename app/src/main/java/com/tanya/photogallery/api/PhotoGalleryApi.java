package com.tanya.photogallery.api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.tanya.photogallery.api.dto.PaginatedPhoto;
import com.tanya.photogallery.util.Mappers;

import retrofit.Call;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

public class PhotoGalleryApi {

    private static final String PARAMETER_DEVICE = "device";

    public Call<PaginatedPhoto> getPopularPhotos(int page) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.interceptors().add(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.500px.com/v1/")
                .addConverterFactory(JacksonConverterFactory.create(Mappers.DEFAULT))
                .client(httpClient)
                .build();
        PhotoGalleryService service = retrofit.create(PhotoGalleryService.class);

        return service.getPhotos("popular", "wB4ozJxTijCwNuggJvPGtBGCRqaZVcF6jsrzUadF", page);
    }

    private OkHttpClient newHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.networkInterceptors().add(
                chain -> {
                    Request request = chain.request();
                    request.httpUrl()
                            .newBuilder()
                            .addQueryParameter(PARAMETER_DEVICE, "android")
                            .build();
                    return chain.proceed(request);
                });
        return okHttpClient;
    }
}
