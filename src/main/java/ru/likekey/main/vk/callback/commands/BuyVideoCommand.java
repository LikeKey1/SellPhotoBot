package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.User;
import ru.likekey.main.entity.Video;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

public class BuyVideoCommand extends VkCommand {

    public final static int totalVideo = 21;

    public BuyVideoCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Загрузка видео, подождите, это может занять некоторое время..");

        userService.checkUserInDB(getVkId());

        User user = userService.getUser(getVkId());
        user.setPlace("BUY_VIDEO");
        userService.updateUser(user);

        Video video = userService.getVideo(1);


        String msg = "Видео #" + (video.getId()) +
                "\nЦена " + video.getPrice() + "Р " +
                "\nДлительность видео: " + video.getDuration() +
                "\nВсего видео: " + totalVideo +
                "\n\nОписание видео: " + video.getDescription();
        boolean bought = false;
        for (Video video1 : user.getVideos()) {
            if (video1.getId() == video.getId()) {
                bought = true;
            }
        }
        if (bought) {
            sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(totalVideo, 2), video.getPath(),
                    video.getPath2(), video.getPath3());
        } else {
            sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(totalVideo, 1, 2), video.getPath(),
                    video.getPath2(), video.getPath3());
        }
    }
}
