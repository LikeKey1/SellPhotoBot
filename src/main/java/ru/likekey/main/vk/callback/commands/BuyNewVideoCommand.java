package ru.likekey.main.vk.callback.commands;

import com.vk.api.sdk.objects.messages.Keyboard;
import ru.likekey.main.entity.Photo;
import ru.likekey.main.entity.User;
import ru.likekey.main.entity.Video;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

import java.util.List;

public class BuyNewVideoCommand extends VkCommand {

    private int videoId;

    public BuyNewVideoCommand(Integer vkId, int videoId) {
        super(vkId);
        this.videoId = videoId;
    }

    @Override
    public void run() throws Exception {
        sendMessage("Видео приобретается, подождите несколько секунд..");
        User user = userService.getUser(getVkId());
        Video video = userService.getVideo(videoId);
        List<Video> boughtVideos = userService.getUserVideos(getVkId());
        boolean checker = true;
        for (Video boughtPhoto : boughtVideos) {
            if (boughtPhoto.getId() == video.getId()) checker = false;
        }
        if (checker) {
            int balance = user.getBalance();
            int price = video.getPrice();
            if (balance >= price) {
                user.addVideoToUser(video);
                user.setBalance(balance - price);
                userService.updateUser(user);

                Keyboard keyboard;

                if (videoId == 1) {
                    keyboard = MyKeyboards.getBoughtPhotoKeyboard(BuyVideoCommand.totalVideo, 2);
                } else if (videoId == BuyVideoCommand.totalVideo) {
                    keyboard = MyKeyboards.getBoughtPhotoKeyboard(BuyVideoCommand.totalVideo-1, 1);
                } else {
                    keyboard = MyKeyboards.getBoughtPhotoKeyboard(videoId-1, videoId+1);
                }

                sendMessage("Спасибо за покупку:)\n" +
                                "Ссылка на видео: " + video.getPathReady() +
                                "\nВесь купленный контент доступен в разделах " +
                                "'Мои фото/Мои видео'",
                        keyboard);
            } else {
                sendMessage("Тебе не хватает " + (price - balance) + "Р :( Чтобы пополнить баланс вернись" +
                        " в главное меню и нажми на 'Пополнить баланс'");
            }
        } else {
            user.setPlace("MAIN");
            userService.updateUser(user);
            sendMessage("Ошибка! Ты уже купил это видео..", MyKeyboards.getStartKeyboard());
        }
    }
}
