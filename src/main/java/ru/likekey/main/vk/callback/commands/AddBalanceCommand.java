package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.Payment;
import ru.likekey.main.service.UserService;
import ru.likekey.main.service.UserServiceImpl;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;
import ru.likekey.main.vk.callback.utils.payment.QiwiPayment;

public class AddBalanceCommand extends VkCommand {

    UserService userService = new UserServiceImpl();

    public AddBalanceCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Запрос выполняется..");

        userService.checkUserInDB(getVkId());

        Payment payment = userService.getUser(getVkId()).getUserPayment();

        if (payment.getBillId() == null) {
            String msg = "Нажмите на сумму, на которую хотите пополнить баланс.\n " +
                    "Пополнить можно будет с карты, Qiwi, баланса телефона. \n";

            sendMessage(msg, MyKeyboards.getAddBalanceKeyboard());
        } else {
            String link = QiwiPayment.getInstance().getLinkForPayment(payment.getBillId());
            String amount = QiwiPayment.getInstance().getAmountForPayment(payment.getBillId());
            String msg = "У вас уже создан счет к оплате на сумму " + amount + "Р, по ссылке " + link + " \n\n" +
                    "Чтобы проверить оплату нажмите на кнопку 'Проверить оплату'\n" +
                    "Чтобы отменить счет и создать новый нажмите на кнопку 'Отменить счет'";
            sendMessage(msg, MyKeyboards.getAddBalanceCheckPaymentKeyboard());
        }
    }
}
