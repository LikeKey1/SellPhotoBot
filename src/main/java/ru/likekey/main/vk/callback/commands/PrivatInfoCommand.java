package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

public class PrivatInfoCommand extends VkCommand {

    public PrivatInfoCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Вы можете вступить в приватную группу, где будет доступ КО ВСЕМ" +
                " фото и видео без цензуры. Также вы будете получать все новые фото и видео," +
                " вам не придется за них доплачивать. Одни только фото стоят около 2300Р, а тут" +
                " вы получите ВСЕ фото и видео за 2000Р, оплатив один раз.", MyKeyboards.getBuyPrivateKeyboard());
    }
}
