package ru.likekey.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import ru.likekey.main.vk.jobs.Job;
import ru.likekey.main.vk.jobs.MessagesJob;
import ru.likekey.main.vk.storage.DataStorage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private static GroupActor actor = null;

    private static String version = "1.0";

    private static Integer groupId = null;

    private static VkApiClient vk;

    private static final List<Job> jobs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        init();

        run();
    }

    private static void init() throws Exception {
        Properties properties = loadConfiguration();

        version = properties.getProperty("version");
        groupId = Integer.valueOf(properties.getProperty("vk.group.id"));

        initClients(properties);
        initData(properties);

        initJobs();
    }

    private static void initClients(Properties properties) throws IOException {
        TransportClient client = HttpTransportClient.getInstance();
        vk = new VkApiClient(client);

        actor = new GroupActor(Integer.parseInt(properties.getProperty("vk.group.id")), properties.getProperty("vk.group.token"));
    }

    private static void initJobs() throws ClientException, ApiException {
        jobs.add(new MessagesJob());
    }

    private static void initData(Properties properties) throws IOException {
        String dataDirectoryPath = properties.getProperty("data.global");
        File dataDirectory = new File(dataDirectoryPath);
        if (!dataDirectory.exists()) {
            LOG.warn("data directory not exist. Create " + dataDirectory.getPath());
            dataDirectory.mkdir();
        }

        File dataPath = new File(dataDirectoryPath + "/data.properties");
        if (!dataPath.exists()) {
            LOG.warn("data.properties not exist. Create " + dataPath.getPath());
            dataPath.createNewFile();
        }

        DataStorage dataStorage = DataStorage.getInstance();
        dataStorage.load(dataPath.getPath());
    }

    private static void run() throws Exception {
        if (jobs.isEmpty()) {
            LOG.warn("No jobs configured. Exist");
            return;
        }

        while (true) {
            for (Job job : jobs) {
                try {
                    job.doJob();
                } catch (Exception e) {
                    LOG.error("Something wrong" + e);
                }
            }

            TimeUnit.SECONDS.sleep(1);
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

    public static String getVersion() {
        return version;
    }

    public static Integer groupId() {
        return groupId;
    }
}
