package ru.likekey.main.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Bot {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, ApiException, ClientException {
        start();
    }

    private final static String PROPERTIES_FILE = "config.properties";

    public static void start() throws ClientException, ApiException, InterruptedException, FileNotFoundException {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        Properties properties = getProperties();
        GroupActor actor = new GroupActor(Integer.parseInt(properties.getProperty("groupId")),
                properties.getProperty("token"));
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();
        while (true){
            MessagesGetLongPollHistoryQuery historyQuery =  vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = historyQuery.execute().getMessages().getItems();
            if (!messages.isEmpty()){
                messages.forEach(message -> {
                    try {
                        if (message.getText().equals("Привет")){
                            vk.messages().send(actor).message("Привет!").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                    }
                    catch (ApiException | ClientException e) {e.printStackTrace();}
                });
            }
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(500);
        }
    }

    private static Properties getProperties() throws FileNotFoundException {
        InputStream inputStream = Bot.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        if (inputStream == null)
            throw new FileNotFoundException("property file '" + PROPERTIES_FILE + "' not found in the classpath");

        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Incorrect properties file");
        }
    }
}
