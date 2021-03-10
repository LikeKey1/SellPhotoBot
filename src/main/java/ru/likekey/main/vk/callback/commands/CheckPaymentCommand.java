package ru.likekey.main.vk.callback.commands;

import com.vk.api.sdk.objects.messages.Keyboard;
import ru.likekey.main.entity.User;
import ru.likekey.main.service.UserService;
import ru.likekey.main.service.UserServiceImpl;
import ru.likekey.main.vk.callback.utils.keyboards.MyKeyboards;
import ru.likekey.main.vk.callback.utils.payment.QiwiPayment;

public class CheckPaymentCommand extends VkCommand{

    UserService userService = new UserServiceImpl();

    public CheckPaymentCommand(Integer vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        userService.checkUserInDB(getVkId());
        sendMessage("Подожди несколько секунд, оплата проверяется...");
        User user = userService.getUser(getVkId());
        String billId = user.getUserPayment().getBillId();
        QiwiPayment qiwiPayment =  QiwiPayment.getInstance();
        String result = qiwiPayment.checkPayment(billId);
        if (billId != null) {
            if (result.equals("PAID")) {

                int amount = Integer.parseInt(qiwiPayment.getAmountForPayment(billId));
                userService.addBalanceToUser(getVkId(), amount);


                userService.deleteBillId(getVkId());

                String msg = "Ты только что пополнил баланс на " + amount + " рублей!";
                sendMessage(msg, MyKeyboards.getStartKeyboard());
            } else if (result.equals("EXPIRED")) {
                String msg = "Время жизни чека истекло. Происходит удаление счета, подождите..";
                sendMessage(msg, MyKeyboards.getAddBalanceKeyboard());
                userService.deleteBillId(getVkId());
            } else if (result.equals("REJECTED")) {
                String msg = "Счет отклонен. Происходит удаление счета, подождите..";
                sendMessage(msg, MyKeyboards.getAddBalanceKeyboard());
                userService.deleteBillId(getVkId());
            } else if (result.equals("WAITING")) {
                String msg = "Счет не оплачен. Если что-то пошло не так, напишите разработчику vk.com/public200997075";
                sendMessage(msg);
            }
        } else  {
            String msg = "Ты не создал счет для оплаты! Нажми на кнопку 'Пополнить баланс'";
            sendMessage(msg, MyKeyboards.getStartKeyboard());
        }
    }
}
