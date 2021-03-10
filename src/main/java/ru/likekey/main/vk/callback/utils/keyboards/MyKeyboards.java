package ru.likekey.main.vk.callback.utils.keyboards;

import com.vk.api.sdk.objects.messages.*;

import java.util.ArrayList;
import java.util.List;

public class MyKeyboards {

    private static Keyboard startKeyboard;
    private static Keyboard addBalanceKeyboard;
    private static Keyboard addBalanceCheckPaymentKeyboard;
    private static Keyboard shopKeyboard;
    private static Keyboard otherServicesKeyBoard;
    private static Keyboard buyPrivateKeyboard;


    public static Keyboard getStartKeyboard() {
        if (startKeyboard == null) {
            startKeyboard = new Keyboard();
            List<List<KeyboardButton>> allKey = new ArrayList<>();
            List<KeyboardButton> line1 = new ArrayList<>();
            List<KeyboardButton> line2 = new ArrayList<>();
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Профиль").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Баланс").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Магазин").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
            line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Пополнить баланс").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            allKey.add(line1);
            allKey.add(line2);
            startKeyboard.setButtons(allKey);
        }
        return startKeyboard;
    }

    public static Keyboard getAddBalanceKeyboard() {
        if (addBalanceKeyboard == null) {
            addBalanceKeyboard = new Keyboard();
            List<List<KeyboardButton>> allKey = new ArrayList<>();
            List<KeyboardButton> line1 = new ArrayList<>();
            List<KeyboardButton> line2 = new ArrayList<>();
            List<KeyboardButton> line3 = new ArrayList<>();
            List<KeyboardButton> line4 = new ArrayList<>();
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("На 10Р").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("На 50Р").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("На 100Р").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));

            line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("На 250Р").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("На 450Р").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("На 800Р").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));

            line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("На 1000Р").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("На 1300Р").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("На 2000Р").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));

            line4.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Назад").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
            allKey.add(line1);
            allKey.add(line2);
            allKey.add(line3);
            allKey.add(line4);
            addBalanceKeyboard.setButtons(allKey);
        }
        return addBalanceKeyboard;
    }

    public static Keyboard getAddBalanceCheckPaymentKeyboard() {
        if (addBalanceCheckPaymentKeyboard == null) {
            addBalanceCheckPaymentKeyboard = new Keyboard();
            List<List<KeyboardButton>> allKey = new ArrayList<>();
            List<KeyboardButton> line1 = new ArrayList<>();
            List<KeyboardButton> line2 = new ArrayList<>();
            List<KeyboardButton> line3 = new ArrayList<>();
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Проверить оплату").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Отменить счет").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.NEGATIVE));
            line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Назад").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
            allKey.add(line1);
            allKey.add(line2);
            allKey.add(line3);
            addBalanceCheckPaymentKeyboard.setButtons(allKey);
        }
        return addBalanceCheckPaymentKeyboard;
    }

    public static Keyboard getShopKeyboard() {
        if (shopKeyboard == null) {
            shopKeyboard = new Keyboard();
            List<List<KeyboardButton>> allKey = new ArrayList<>();
            List<KeyboardButton> line1 = new ArrayList<>();
            List<KeyboardButton> line2 = new ArrayList<>();
            List<KeyboardButton> line3 = new ArrayList<>();
            List<KeyboardButton> line4 = new ArrayList<>();
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Купить фото").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Купить видео").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Мои фото").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Мои видео").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Другие услуги").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line4.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Назад").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
            allKey.add(line1);
            allKey.add(line2);
            allKey.add(line3);
            allKey.add(line4);
            shopKeyboard.setButtons(allKey);
        }
        return shopKeyboard;
    }

    public static Keyboard getNotBoughtPhotoKeyboard(int previous, int present, int next) {
        Keyboard buyingPhotoKeyboard = new Keyboard();
        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        List<KeyboardButton> line2 = new ArrayList<>();
        List<KeyboardButton> line3 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Предыдущее " + previous).setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Следующее " + next).setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Приобрести " + present).setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Выйти").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
        allKey.add(line1);
        allKey.add(line2);
        allKey.add(line3);
        buyingPhotoKeyboard.setButtons(allKey);

        return buyingPhotoKeyboard;
    }

    public static Keyboard getBoughtPhotoKeyboard(int previous, int next) {
        Keyboard buyingPhotoKeyboard = new Keyboard();
        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        List<KeyboardButton> line2 = new ArrayList<>();
        List<KeyboardButton> line3 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Предыдущее " + previous).setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Следующее " + next).setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Уже куплено").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.NEGATIVE));
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Выйти").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
        allKey.add(line1);
        allKey.add(line2);
        allKey.add(line3);
        buyingPhotoKeyboard.setButtons(allKey);

        return buyingPhotoKeyboard;
    }

    public static Keyboard getMyPhotoKeyboard(int previous, int next) {
        Keyboard myPhotoKeyboard = new Keyboard();

        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        List<KeyboardButton> line2 = new ArrayList<>();

        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Предыдущее " + previous).setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Следующее " + next).setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Выйти").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));

        allKey.add(line1);
        allKey.add(line2);

        myPhotoKeyboard.setButtons(allKey);

        return myPhotoKeyboard;
    }

    public static Keyboard getOtherServicesKeyBoard() {
        if (otherServicesKeyBoard == null) {
            otherServicesKeyBoard = new Keyboard();

            List<List<KeyboardButton>> allKey = new ArrayList<>();
            List<KeyboardButton> line1 = new ArrayList<>();
            List<KeyboardButton> line2 = new ArrayList<>();

            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Приватная группа").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Назад").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));

            allKey.add(line1);
            allKey.add(line2);

            otherServicesKeyBoard.setButtons(allKey);
        }
        return otherServicesKeyBoard;
    }

    public static Keyboard getBuyPrivateKeyboard() {
        if (buyPrivateKeyboard == null) {
            buyPrivateKeyboard = new Keyboard();

            List<List<KeyboardButton>> allKey = new ArrayList<>();
            List<KeyboardButton> line1 = new ArrayList<>();
            List<KeyboardButton> line2 = new ArrayList<>();

            line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Купить приват").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));
            line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Назад").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));

            allKey.add(line1);
            allKey.add(line2);

            buyPrivateKeyboard.setButtons(allKey);
        }
        return buyPrivateKeyboard;
    }
}
