package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.Photo;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

import java.util.List;

public class TheNextPhotoInShopCommand extends VkCommand {

    private int photoId;

    public TheNextPhotoInShopCommand(Integer vkId, int photoId) {
        super(vkId);
        this.photoId = photoId;
    }

    public void run() throws Exception {
        Photo photo;
        try {
            photo = userService.getPhoto(photoId + 3);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            sendMessage("Такого фото нет!");
            return;
        }
        List<Photo> boughtPhotos = userService.getUserPhotos(getVkId());
        boolean checker = true;
        for (Photo boughtPhoto : boughtPhotos) {
            if (boughtPhoto.getId() == photo.getId()) checker = false;
        }
        if (checker) {
            if (photoId == BuyPhotoCommand.totalPhoto) {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.totalPhoto;
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(photoId-1, photoId, 1), photo.getPath());
            } else if (photoId == 1) {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.totalPhoto;
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(BuyPhotoCommand.totalPhoto, photoId, 2), photo.getPath());
            } else {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.totalPhoto;
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(photoId-1, photoId, photoId+1), photo.getPath());
            }
        } else {
            if (photoId == BuyPhotoCommand.totalPhoto) {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.totalPhoto;
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(photoId-1, 1), photo.getPath());
            } else if (photoId == 1) {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.totalPhoto;
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(BuyPhotoCommand.totalPhoto, 2), photo.getPath());
            } else {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.totalPhoto;
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(photoId-1, photoId+1), photo.getPath());
            }
        }
    }
}
