package com.tanya.photogallery.models;

import java.io.Serializable;

public class Photo implements Serializable {
    public long id;
    public long userId;
    public String name;
    public String description;
    public String camera;
    public String imageUrl;
    public User user;
}
