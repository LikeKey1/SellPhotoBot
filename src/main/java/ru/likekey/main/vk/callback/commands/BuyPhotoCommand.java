package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.Photo;
import ru.likekey.main.entity.User;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

public class BuyPhotoCommand extends VkCommand {

    public final static int totalPhoto = 261;

    public BuyPhotoCommand(Integer vkId) {
        super(vkId);
    }
    @Override
    public void run() throws Exception {
        sendMessage("Загрузка фото, подождите, это может занять некоторое время..");

        userService.checkUserInDB(getVkId());

        User user = userService.getUser(getVkId());
        user.setPlace("BUY_PHOTO");
        userService.updateUser(user);

        Photo photo = userService.getPhoto(4);


        String msg = "Фото #" + (photo.getId()-3) +
                "\nЦена " + photo.getPrice() + "Р " +
                "\nВсего фото: " + totalPhoto;
        boolean bought = false;
        for (Photo photo1 : user.getPhotos()) {
            if (photo1.getId() == photo.getId()) {
                bought = true;
            }
        }
        if (bought) {
            sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(totalPhoto, 2), photo.getPath());
        } else {
            sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(totalPhoto, 1, 2), photo.getPath());
        }
    }
}
