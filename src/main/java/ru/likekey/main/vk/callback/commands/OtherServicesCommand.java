package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

public class OtherServicesCommand extends VkCommand {

    public OtherServicesCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Выберите интересующую вас услугу", MyKeyboards.getOtherServicesKeyBoard());
    }
}
