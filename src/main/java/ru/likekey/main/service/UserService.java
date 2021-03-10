package ru.likekey.main.service;

import ru.likekey.main.entity.Photo;
import ru.likekey.main.entity.User;
import ru.likekey.main.entity.Video;

import java.util.List;

public interface UserService {

    // UserDAO
    public void saveUser(User user);
    public void updateUser(User user);
    public User getUser(int vkId);
    public void saveBillId(int id, String billId);
    public String getBillId(int id);
    public void deleteBillId(int vkId);
    public void addBalanceToUser(int vkId, int amount);
    public void checkUserInDB(int vkId);

    // VideoDAO
    public List<Video> getUserVideos(int vkId);
    public void addVideoToUser();
    public Video getVideo(int id);

    // PhotoDAO
    public List<Photo> getUserPhotos(int vkId);
    public List<Photo> getAllPhoto();
    public Photo getPhoto(int id);
}
