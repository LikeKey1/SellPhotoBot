package ru.likekey.main.vk.callback.commands;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Keyboard;
import ru.likekey.main.Application;
import ru.likekey.main.service.UserServiceImpl;

import java.util.Random;

public abstract class VkCommand {

    protected static volatile UserServiceImpl userService = new UserServiceImpl();

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

    public void sendMessage(String msg, Keyboard keyboard) throws ClientException, ApiException {
        Application.vk().messages().send(Application.actor())
                .randomId(new Random().nextInt(10000))
                .message(msg)
                .peerId(getVkId())
                .keyboard(keyboard).execute();
    }

    public void sendMessage(String msg, Keyboard keyboard, String attachment) throws ClientException, ApiException {
        Application.vk().messages().send(Application.actor())
                .randomId(new Random().nextInt(10000))
                .message(msg)
                .peerId(getVkId())
                .keyboard(keyboard)
                .attachment(attachment).execute();
    }
    public void sendMessage(String msg, String attachment) throws ClientException, ApiException {
        Application.vk().messages().send(Application.actor())
                .randomId(new Random().nextInt(10000))
                .message(msg)
                .peerId(getVkId())
                .attachment(attachment).execute();
    }

    public void sendMessage(String msg, Keyboard keyboard, String attachment,
                            String attachment2, String attachment3) throws ClientException, ApiException {
        Application.vk().messages().send(Application.actor())
                .randomId(new Random().nextInt(10000))
                .message(msg)
                .peerId(getVkId())
                .keyboard(keyboard)
                .attachment(attachment + "," + attachment2 + "," + attachment3).execute();
    }
}
