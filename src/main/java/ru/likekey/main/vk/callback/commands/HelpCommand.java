package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;

public class HelpCommand extends VkCommand {
    public HelpCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        String msg = "Список доступных команд:\n\n" +
                "Профиль - информация о вас\n" +
                "Баланс - ваш баланс\n" +
                "Магазин - покупка контента\n" +
                "Пополнить баланс - пополнение баланса\n\n" +
                "Команды для магазина:\n" +
                "Купить фото/Купить видео\n" +
                "Мои фото/Мои видео\n" +
                "Другие услуги\n\n" +
                "Чтобы вернуться на главный экран нажмите на 'Назад' или напишите 'Начать'";
        sendMessage(msg, MyKeyboards.getStartKeyboard());
    }
}
