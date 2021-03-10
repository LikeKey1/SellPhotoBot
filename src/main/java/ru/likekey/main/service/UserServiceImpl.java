package ru.likekey.main.service;

import ru.likekey.main.dao.*;
import ru.likekey.main.entity.Photo;
import ru.likekey.main.entity.User;
import ru.likekey.main.entity.Video;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private PhotoDAO photoDAO;
    private VideoDAO videoDAO;

    public UserServiceImpl() {
        userDAO = new UserDAOImpl();
        photoDAO = new PhotoDAOImpl();
        videoDAO = new VideoDAOImpl();
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User getUser(int vkId) {
        return userDAO.getUser(vkId);
    }

    @Override
    public void saveBillId(int id, String billId) {
        userDAO.saveBillId(id, billId);
    }

    @Override
    public String getBillId(int id) {
        return userDAO.getBillId(id);
    }

    @Override
    public void deleteBillId(int vkId) {
        userDAO.deleteBillId(vkId);
    }

    @Override
    public void addBalanceToUser(int vkId, int amount) {
        userDAO.addBalanceToUser(vkId, amount);
    }

    @Override
    public void checkUserInDB(int vkId) {
        userDAO.checkUserInDB(vkId);
    }

    @Override
    public List<Video> getUserVideos(int vkId) {
        return videoDAO.getUserVideos(vkId);
    }

    @Override
    public void addVideoToUser() {
        videoDAO.addVideoToUser();
    }

    @Override
    public Video getVideo(int id) {
        return videoDAO.getVideo(id);
    }

    @Override
    public List<Photo> getUserPhotos(int vkId) {
        return photoDAO.getUserPhotos(vkId);
    }



    @Override
    public List<Photo> getAllPhoto() {
        return photoDAO.getAllPhoto();
    }

    @Override
    public Photo getPhoto(int id) {
        return photoDAO.getPhoto(id);
    }
}
