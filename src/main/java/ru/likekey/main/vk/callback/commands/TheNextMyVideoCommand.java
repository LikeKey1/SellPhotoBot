package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.Video;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

import java.util.List;

public class TheNextMyVideoCommand extends VkCommand {

    private int videoId;

    public TheNextMyVideoCommand(Integer vkId, int videoId) {
        super(vkId);
        this.videoId = videoId;
    }

    @Override
    public void run() throws Exception {
        List<Video> videos = userService.getUserVideos(getVkId());
        int maxVideoId = videos.size();
        if (videoId < 1 || videoId > maxVideoId) {
            sendMessage("Такого видео нет!");
        } else  {
            String msg = "Видео #" + videoId +
                    "\nВсего куплено видео: " + maxVideoId +
                    "\nСсылка на видео: " + videos.get(videoId-1).getPathReady();
            Video video = videos.get(videoId-1);
            if (videoId == maxVideoId) {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(videoId-1, 1),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else if (videoId == 1) {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(videoId, 2),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(videoId-1, videoId+1),
                        video.getPath(), video.getPath2(), video.getPath3());
            }
        }
    }
}
