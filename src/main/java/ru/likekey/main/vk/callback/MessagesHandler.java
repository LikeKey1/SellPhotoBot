package ru.likekey.main.vk.callback;

import com.vk.api.sdk.callback.CallbackApi;
import com.vk.api.sdk.objects.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.likekey.main.Application;
import ru.likekey.main.entity.User;
import ru.likekey.main.service.UserService;
import ru.likekey.main.service.UserServiceImpl;
import ru.likekey.main.vk.callback.commands.*;
import ru.likekey.main.vk.callback.utils.SomeUtils;

public class MessagesHandler extends CallbackApi {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private static final UserService userService = new UserServiceImpl();

    @Override
    public void messageNew(Integer groupId, Message message) {
        parseMessage(groupId, message);
    }

    public static void parseMessage(Integer groupId, Message message) {
        Integer vkId = message.getFromId();
        String[] args = message.getText().split(" ");
        String command = args[0];
        User user = userService.getUser(vkId);
        if (user != null) {
            String place = user.getPlace();
            try {
                switch (place) {
                    case "MAIN":
                        switch (command.toLowerCase()) {
                            case "профиль":
                                new ProfileCommand(vkId).run();
                                break;

                            case "баланс":
                                new BalanceCommand(vkId).run();
                                break;


                            case "магазин":
                                new ShopCommand(vkId).run();
                                break;

                            case "пополнить":
                                new AddBalanceCommand(vkId).run();
                                break;

                            case "купить":
                                if (args.length > 1) {
                                    switch (args[1].toLowerCase()) {
                                        case "фото":
                                            new BuyPhotoCommand(vkId).run();
                                            break;
                                        case "видео":
                                            new BuyVideoCommand(vkId).run();
                                            break;
                                        case "приват":
                                            new BuyPrivateCommand(vkId).run();
                                            break;
                                    }
                                }
                                break;

                            case "мои":
                                if (args.length > 1) {
                                    switch (args[1].toLowerCase()) {
                                        case "фото":
                                            new MyPhotosCommand(vkId).run();
                                            break;
                                        case "видео":
                                            new MyVideosCommand(vkId).run();
                                            break;
                                    }
                                }
                                break;

                            case "другие":
                                new OtherServicesCommand(vkId).run();
                                break;

                            case "на":
                                if (args.length > 1) {
                                    String sum = args[1].substring(0, args[1].length() - 1);
                                    if (SomeUtils.isDigit(sum)) {
                                        new AddBalanceWIthSumCommand(vkId, Integer.parseInt(sum)).run();
                                    }
                                }
                                break;

                            case "отменить":
                                if (args.length > 1 && args[1].equals("счет")) {
                                    new CancelPaymentCommand(vkId).run();
                                }
                                break;

                            case "проверить":
                                if (args.length > 1 && args[1].equals("оплату")) {
                                    new CheckPaymentCommand(vkId).run();
                                }
                                break;

                            case "приватная":
                                new PrivatInfoCommand(vkId).run();
                                break;

                            case "назад":
                            case "начать":
                            default:
                                new StartCommand(vkId).run();
                                break;
                        }
                        break;
                    case "BUY_PHOTO":
                        switch (command.toLowerCase()) {
                            case "предыдущее":
                            case "следующее":
                                if (args.length > 1) {
                                    new TheNextPhotoInShopCommand(vkId, Integer.parseInt(args[1])).run();
                                }
                                break;
                            case "приобрести":
                                if (args.length > 1) new BuyNewPhotoCommand(vkId, Integer.parseInt(args[1])).run();
                                break;
                            case "выйти":
                                new ExitFromShopCommand(vkId).run();
                                break;
                        }
                        break;
                    case "MY_PHOTO":
                        switch (command.toLowerCase()) {
                            case "следующее":
                            case "предыдущее":
                                if (args.length > 1) new TheNextMyPhotoCommand(vkId, Integer.parseInt(args[1])).run();
                                break;
                            case "выйти":
                                new ExitFromShopCommand(vkId).run();
                                break;
                        }
                        break;
                    case "BUY_VIDEO":
                        switch (command.toLowerCase()) {
                            case "следующее":
                            case "предыдущее":
                                if (args.length > 1) new TheNextVideoInShopCommand(vkId, Integer.parseInt(args[1])).run();
                                break;
                            case "приобрести":
                                if (args.length > 1) new BuyNewVideoCommand(vkId, Integer.parseInt(args[1])).run();
                                break;
                            case "выйти":
                                new ExitFromShopCommand(vkId).run();
                                break;
                        }
                        break;
                    case "MY_VIDEO":
                        switch (command.toLowerCase()) {
                            case "следующее":
                            case "предыдущее":
                                if (args.length > 1) new TheNextMyVideoCommand(vkId, Integer.parseInt(args[1])).run();
                                break;
                            case "выйти":
                                new ExitFromShopCommand(vkId).run();
                                break;
                        }
                        break;
                }
            } catch (Exception e) {
                LOG.error("Can't execute command", e);
            }
        } else {
            if (command.toLowerCase().equals("начать")) {
                try {
                    new StartCommand(vkId).run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
