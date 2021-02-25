package ru.likekey.main.vk.callback;

import com.vk.api.sdk.objects.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.likekey.main.vk.callback.commands.HelpCommand;
import ru.likekey.main.vk.jobs.MessagesJob;

public class MessagesHandler {
    private static final Logger LOG = LoggerFactory.getLogger(MessagesJob.class);

    private static Integer parseInt(String[] args, int index, int defaultValue) {
        if (args.length <= index) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(args[index]);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static void parseMessage(Integer groupId, Message message) {
        Integer vkId = message.getFromId();
        String[] args = message.getText().split(" ");
        String command = args[0];

        try {
            switch (command.toLowerCase()) {
                case "профиль":
//                    new HelpCommand(vkId).run();
//                    break;

//                case "баланс":
//                    if (args.length != 2) {
//                        new HelpCommand(vkId).run();
//                        break;
//                    }
//
//                    String ytLogin = args[1];
//                    new LoginCommand(vkId, ytLogin).run();
//                    break;
//
//                case "магазин":
//                    new LogoutCommand(vkId).run();
//                    break;
//
//                case "пополнить":
//                    new MineTasksCommand(vkId, parseInt(args, 1, 10)).run();
//                    break;
//
//                case "купить":
//                    String filter = message.getText().substring(command.length() + 1);
//                    new SearchTasksCommand(vkId, filter, 10).run();
//                    break;
//
//                case "мои":
//                    new GetConfigCommand(vkId).run();
//                    break;
//
//                case "назад":
//                    if (args.length != 3) {
//                        new HelpCommand(vkId).run();
//                        break;
//                    }
//
//                    String name = args[1];
//                    String value = args[2];
//
//                    new SetConfigCommand(vkId, name, value).run();
//                    break;

                default:
                    new HelpCommand(vkId).run();
            }
        } catch (Exception e) {
            LOG.error("Can't execute command", e);
        }
    }
}
