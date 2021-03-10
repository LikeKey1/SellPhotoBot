package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.User;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

public class BalanceCommand extends VkCommand {

    public BalanceCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        userService.checkUserInDB(getVkId());
        int balance = userService.getUser(getVkId()).getBalance();

        String msg = "Баланс: " + balance + "\uD83D\uDCB0\n";

        sendMessage(msg, MyKeyboards.getStartKeyboard());
    }
}
