package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.User;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

public class BuyPrivateCommand extends VkCommand {

    public BuyPrivateCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        userService.checkUserInDB(getVkId());
        sendMessage("Покупка привата, подождите..");

        User user = userService.getUser(getVkId());
        int balance = user.getBalance();

        if (balance >= 2000) {
            user.setBalance(balance-2000);
            userService.updateUser(user);
            sendMessage("Спасибо за покупку:) А теперь отправь заявку в приват vk.com/club202272223," +
                    " тебя примут в ближайшее время", MyKeyboards.getStartKeyboard());
        } else {
            sendMessage("Тебе не хватает " + (2000 - balance) + "Р, пополни баланс", MyKeyboards.getStartKeyboard());
        }
    }
}
