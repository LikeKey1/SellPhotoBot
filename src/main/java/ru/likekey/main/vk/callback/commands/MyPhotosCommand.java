package ru.likekey.main.vk.callback.commands;
import ru.likekey.main.entity.Photo;
import ru.likekey.main.entity.User;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

import java.util.List;

public class MyPhotosCommand extends VkCommand {

    public MyPhotosCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Загрузка фото, подождите, это может занять некоторое время..");

        userService.checkUserInDB(getVkId());

        List<Photo> photos = userService.getUserPhotos(getVkId());
        if (photos.isEmpty()) {
            sendMessage("Ты еще не купил фото!");
        } else {
            Photo photo = photos.get(0);
            User user = userService.getUser(getVkId());
            user.setPlace("MY_PHOTO");
            userService.updateUser(user);
            String msg = "Фото #1" +
                    "\nВсего куплено фото: " + photos.size();
            sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(photos.size(), 2), photo.getPathReady());
        }
    }
}
