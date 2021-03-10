package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.User;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

public class ExitFromShopCommand extends VkCommand {

    public ExitFromShopCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Возвращаем вас");
        User user = userService.getUser(getVkId());
        user.setPlace("MAIN");
        userService.updateUser(user);
        sendMessage("Вы вернулись в главное меню!", MyKeyboards.getStartKeyboard());
    }
}
