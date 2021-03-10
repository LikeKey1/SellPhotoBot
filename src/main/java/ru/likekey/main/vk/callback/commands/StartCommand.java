package ru.likekey.main.vk.callback.commands;

public class StartCommand extends VkCommand {


    public StartCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        userService.checkUserInDB(getVkId());
        new HelpCommand(getVkId()).run();
    }
}
