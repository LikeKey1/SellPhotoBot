package ru.likekey.main.vk.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.responses.GetByIdResponse;
import ru.likekey.main.Application;
import ru.likekey.main.vk.callback.MessagesHandler;
import ru.likekey.main.vk.storage.DataStorage;

public class MessagesJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(MessagesJob.class);
    private static final String LAST_MESSAGE_ID_KEY = "vk.bot.lastMessageId";

    private Integer lastMessageId = 0;
    private Integer groupId = 0;

    public MessagesJob() throws ClientException, ApiException {
        lastMessageId = DataStorage.getInstance().getInt(LAST_MESSAGE_ID_KEY);
        if (lastMessageId == 0) {
            GetByIdResponse getResponse = Application.vk().messages().getById(Application.actor(), lastMessageId).execute();

            if (!getResponse.getItems().isEmpty()) {
                lastMessageId = getResponse.getItems().get(0).getId();
                DataStorage.getInstance().add(LAST_MESSAGE_ID_KEY, String.valueOf(lastMessageId));
            }
        }

        groupId = Application.groupId();
    }

    @Override
    public void doJob() throws Exception {
        GetByIdResponse getResponse = Application.vk().messages()
                .getById(Application.actor(), lastMessageId)
                .execute();

        for (Message message : getResponse.getItems()) {
            MessagesHandler.parseMessage(groupId, message);

            if (lastMessageId < message.getId()) {
                lastMessageId = message.getId();
                DataStorage.getInstance().add(LAST_MESSAGE_ID_KEY, lastMessageId);
            }
        }
    }
}
