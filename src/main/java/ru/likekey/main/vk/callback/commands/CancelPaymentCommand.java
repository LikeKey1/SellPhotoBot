package ru.likekey.main.vk.callback.commands;

import ru.likekey.main.entity.User;
import ru.likekey.main.service.UserService;
import ru.likekey.main.service.UserServiceImpl;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;
import ru.likekey.main.vk.callback.utils.payment.QiwiPayment;

public class CancelPaymentCommand extends VkCommand {

    UserService userService = new UserServiceImpl();

    public CancelPaymentCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        userService.checkUserInDB(getVkId());
        sendMessage("Отмена платежа, подождите немного");
        User user = userService.getUser(getVkId());
        String billId = user.getUserPayment().getBillId();
        if (billId != null) {
            if (billId != null) {
                userService.deleteBillId(getVkId());
                QiwiPayment.getInstance().cancelBill(billId);
                String msg = "Твой счет на оплату отменен!";
                sendMessage(msg, MyKeyboards.getAddBalanceKeyboard());
            }
        } else  {
            String msg = "Ты не создал счет для оплаты! Нажми на кнопку 'Пополнить баланс'";
            sendMessage(msg, MyKeyboards.getStartKeyboard());
        }
    }
}
