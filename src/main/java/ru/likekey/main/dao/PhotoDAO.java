package ru.likekey.main.dao;

import ru.likekey.main.entity.Photo;

import java.util.List;

public interface PhotoDAO {
    public List<Photo> getUserPhotos(int vkId);
    public List<Photo> getAllPhoto();
    public Photo getPhoto(int id);
}
