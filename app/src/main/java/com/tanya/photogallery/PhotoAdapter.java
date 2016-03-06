package com.tanya.photogallery;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tanya.photogallery.models.Photo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private List<Photo> records;

    public PhotoAdapter(List<Photo> dataset) {
        records = dataset;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        return PhotoViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder viewHolder, int position) {
        final Photo item = records.get(position);

        PhotoViewModel photoViewModel = PhotoViewModel.from(
                item);
        viewHolder.bind(photoViewModel);

    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public Photo getItem(int position) {
        return records.get(position);
    }

    public void setData(List<Photo> data) {
        records.clear();
        records.addAll(data);
    }
}