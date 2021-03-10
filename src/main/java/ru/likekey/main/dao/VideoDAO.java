package ru.likekey.main.dao;

import ru.likekey.main.entity.Video;

import java.util.List;

public interface VideoDAO {
    public List<Video> getUserVideos(int vkId);
    public void addVideoToUser();
    public Video getVideo(int id);
}
