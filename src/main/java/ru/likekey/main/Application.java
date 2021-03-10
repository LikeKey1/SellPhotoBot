package ru.likekey.main;

import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import ru.likekey.main.vk.callback.MessagesHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private static GroupActor actor = null;

    private static String version = "1.0";

    private static Integer groupId = null;

    private static VkApiClient vk;

    private static Integer ts;

    public static void main(String[] args) throws Exception {
        init();

        run();
    }

    private static void init() throws Exception {
        Properties properties = loadConfiguration();

        version = properties.getProperty("version");
        groupId = Integer.valueOf(properties.getProperty("vk.group.id"));

        initClients(properties);
    }

    private static void initClients(Properties properties) throws IOException, ClientException, ApiException {
        TransportClient client = HttpTransportClient.getInstance();
        vk = new VkApiClient(client);

        actor = new GroupActor(Integer.parseInt(properties.getProperty("vk.group.id")), properties.getProperty("vk.group.token"));
        ts = vk.messages().getLongPollServer(actor).execute().getTs();
    }

    private static void run() throws Exception {
        while (true) {
            MessagesGetLongPollHistoryQuery historyQuery =  vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages;
            try {
                messages = historyQuery.execute().getMessages().getItems();
            } catch (Exception e) {
                System.out.println("\n---Ошибка 10 VkApi---\n");
                Thread.sleep(30000);
                continue;
            }

            if (!messages.isEmpty()) {
                Iterator<Message> iterator = messages.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().getFromId() < 1) iterator.remove();
                }
                messages.forEach(message -> {
                    MessagesHandler.parseMessage(groupId ,message);
                });
            }

            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(500);
        }
    }

    private static Properties loadConfiguration() {
        Properties properties = new Properties();
        try (InputStream is = Application.class.getResourceAsStream("/config.properties")) {
            properties.load(is);
        } catch (IOException e) {
            LOG.error("Can't load properties file", e);
            throw new IllegalStateException(e);
        }

        return properties;
    }

    public static GroupActor actor() {
        return actor;
    }

    public static VkApiClient vk() {
        return vk;
    }

    public static Integer ts() {return ts; }

    public static String getVersion() {
        return version;
    }

    public static Integer groupId() {
        return groupId;
    }
}
