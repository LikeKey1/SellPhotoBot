package ru.likekey.main.vk.callback.commands;

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
                "Мои фото/Мои видео\n\n" +
                "Чтобы вернуться на главный экран нажмите на 'Назад' или отправьте любое сообщение";
        sendMessage(msg);
    }
}
