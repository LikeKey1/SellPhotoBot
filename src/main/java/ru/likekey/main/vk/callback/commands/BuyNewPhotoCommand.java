package ru.likekey.main.vk.callback.commands;

import com.vk.api.sdk.objects.messages.Keyboard;
import ru.likekey.main.entity.Photo;
import ru.likekey.main.entity.User;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

import java.util.List;

public class BuyNewPhotoCommand extends VkCommand {

    private int photoId;

    public BuyNewPhotoCommand(Integer vkId, int photoId) {
        super(vkId);
        this.photoId = photoId;
    }

    @Override
    public void run() throws Exception {
        sendMessage("Фото приобретается, подождите несколько секунд..");
        User user = userService.getUser(getVkId());
        Photo photo = userService.getPhoto(photoId+3);
        List<Photo> boughtPhotos = userService.getUserPhotos(getVkId());
        boolean checker = true;
        for (Photo boughtPhoto : boughtPhotos) {
            if (boughtPhoto.getId() == photo.getId()) checker = false;
        }
        if (checker) {
            int balance = user.getBalance();
            int price = photo.getPrice();
            if (balance >= price) {
                user.addPhotoToUser(photo);
                user.setBalance(balance - price);
                userService.updateUser(user);

                Keyboard keyboard;

                if (photoId == 1) {
                    keyboard = MyKeyboards.getBoughtPhotoKeyboard(BuyPhotoCommand.totalPhoto, 2);
                } else if (photoId == BuyPhotoCommand.totalPhoto) {
                    keyboard = MyKeyboards.getBoughtPhotoKeyboard(BuyPhotoCommand.totalPhoto-1, 1);
                } else {
                    keyboard = MyKeyboards.getBoughtPhotoKeyboard(photoId-1, photoId+1);
                }

                sendMessage("Спасибо за покупку:) Весь купленный контент доступен в разделах " +
                        "'Мои фото/Мои видео'",
                        keyboard,
                        photo.getPathReady());
            } else {
                sendMessage("Тебе не хватает " + (price - balance) + "Р :( Чтобы пополнить баланс вернись" +
                        " в главное меню и нажми на 'Пополнить баланс'");
            }
        } else {
            user.setPlace("MAIN");
            userService.updateUser(user);
            sendMessage("Ошибка! Ты уже купил это фото..", MyKeyboards.getStartKeyboard());
        }
    }
}
