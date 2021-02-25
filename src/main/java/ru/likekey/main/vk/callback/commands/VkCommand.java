package ru.likekey.main.vk.callback.commands;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.likekey.main.Application;

import java.util.Random;

public abstract class VkCommand {

    private Integer vkId;

    public VkCommand(Integer vkId) {
        this.vkId = vkId;
    }

    public abstract void run() throws Exception;

    public Integer getVkId() {
        return vkId;
    }

    public void sendMessage(String msg) throws ClientException, ApiException {
        Application.vk().messages().send(Application.actor())
                .randomId(new Random().nextInt(10000))
                .message(msg)
                .peerId(getVkId()).execute();
    }
}
