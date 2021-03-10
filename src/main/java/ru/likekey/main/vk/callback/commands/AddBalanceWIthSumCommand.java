package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;
import ru.likekey.main.vk.callback.utils.payment.QiwiPayment;

public class AddBalanceWIthSumCommand extends VkCommand {

    private int sumOfPayment;

    public AddBalanceWIthSumCommand(Integer vkId, int sumOfPayment) {
        super(vkId);
        this.sumOfPayment = sumOfPayment;
    }

    @Override
    public void run() throws Exception {
        userService.checkUserInDB(getVkId());
        sendMessage("Генерация ссылки для пополнения баланса...");
        if (sumOfPayment <= 0) {
            sendMessage("Введено неверно значение. Вы не можете пополнить баланс на сумму меньшую или равную нулю!");
        } else if (sumOfPayment > 10000) {
            sendMessage("Максимальная сумма для пополнения баланса 10.000 рублей!");
        } else {
            if (userService.getUser(getVkId()).getUserPayment().getBillId() == null) {
                String link = QiwiPayment.getInstance().getPaymentUrl(getVkId(), sumOfPayment);
                String msg = "Ссылка для пополнения баланса: \n" + link;
                sendMessage(msg, MyKeyboards.getAddBalanceCheckPaymentKeyboard());
            } else {
                new AddBalanceCommand(getVkId()).run();
            }
        }

    }
}
