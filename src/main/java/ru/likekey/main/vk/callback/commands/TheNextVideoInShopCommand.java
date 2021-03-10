package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.Video;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

import java.util.List;

public class TheNextVideoInShopCommand extends VkCommand {

    private int videoId;

    public TheNextVideoInShopCommand(Integer vkId, int videoId) {
        super(vkId);
        this.videoId = videoId;
    }

    @Override
    public void run() throws Exception {
        Video video;
        try {
            video = userService.getVideo(videoId);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            sendMessage("Такого фото нет!");
            return;
        }
        List<Video> boughtVideos = userService.getUserVideos(getVkId());
        boolean checker = true;
        for (Video video1 : boughtVideos) {
            if (video.getId() == video1.getId()) checker = false;
        }
        String msg = "Видео #" + (video.getId()) +
                "\nЦена " + video.getPrice() + "Р " +
                "\nДлительность видео: " + video.getDuration() +
                "\nВсего видео: " + BuyVideoCommand.totalVideo +
                "\n\nОписание видео: " + video.getDescription();
        if (checker) {
            if (videoId == BuyVideoCommand.totalVideo) {
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(videoId-1, videoId, 1),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else if (videoId == 1) {
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(BuyVideoCommand.totalVideo, videoId, 2),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else {
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(videoId-1, videoId, videoId+1),
                        video.getPath(), video.getPath2(), video.getPath3());
            }
        } else {

            if (videoId == BuyVideoCommand.totalVideo) {
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(videoId-1, 1),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else if (videoId == 1) {
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(BuyVideoCommand.totalVideo, 2),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else {
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(videoId-1, videoId+1),
                        video.getPath(), video.getPath2(), video.getPath3());
            }
        }
    }
}
