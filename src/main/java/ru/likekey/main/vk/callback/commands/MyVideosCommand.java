package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.Photo;
import ru.likekey.main.entity.User;
import ru.likekey.main.entity.Video;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

import java.util.List;

public class MyVideosCommand extends VkCommand {

    public MyVideosCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Загрузка видео, подождите, это может занять некоторое время..");

        userService.checkUserInDB(getVkId());

        List<Video> videos = userService.getUserVideos(getVkId());
        if (videos.isEmpty()) {
            sendMessage("Ты еще не купил видео!");
        } else {
            Video video = videos.get(0);
            User user = userService.getUser(getVkId());
            user.setPlace("MY_VIDEO");
            userService.updateUser(user);
            String msg = "Видео #1" +
                    "\nВсего куплено видео: " + videos.size() +
                    "\nСсылка на видео: " + video.getPathReady();
            if (videos.size() == 1) {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(videos.size(), 1),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(videos.size(), 2),
                        video.getPath(), video.getPath2(), video.getPath3());
            }
        }
    }
}
