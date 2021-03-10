package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.Photo;
import ru.likekey.main.entity.User;
import ru.likekey.main.entity.Video;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;


public class ProfileCommand extends VkCommand {

    public ProfileCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {

        userService.checkUserInDB(getVkId());

        User user = userService.getUser(getVkId());

        int balance = user.getBalance();
        int boughtPhoto = user.getPhotos().size();
        int boughtVideo = user.getVideos().size();

        String msg = "Ваш профиль:\n\n" +
                "Баланс: " + balance + "\uD83D\uDCB0\n" +
                "Куплено фото: " + boughtPhoto + "\uD83D\uDCF7\n" +
                "Куплено видео: " + boughtVideo + "\uD83C\uDFA5";

        sendMessage(msg, MyKeyboards.getStartKeyboard());
    }
}
