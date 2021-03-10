package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

public class ShopCommand extends VkCommand {

    public ShopCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Магазин", MyKeyboards.getShopKeyboard());
    }
}
