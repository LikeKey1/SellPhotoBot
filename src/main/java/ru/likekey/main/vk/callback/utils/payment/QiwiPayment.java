package ru.likekey.main.vk.callback.utils.payment;

import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.exception.BillPaymentServiceException;
import com.qiwi.billpayments.sdk.model.MoneyAmount;
import com.qiwi.billpayments.sdk.model.in.CreateBillInfo;
import com.qiwi.billpayments.sdk.model.in.Customer;
import com.qiwi.billpayments.sdk.model.in.PaymentInfo;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.likekey.main.Application;
import ru.likekey.main.service.UserService;
import ru.likekey.main.service.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.Properties;
import java.util.UUID;

public class QiwiPayment {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private static Properties properties;

    private static String publicKey;
    private static String privateKey;
    private static String login;

    private static UserService userService = new UserServiceImpl();

    private static volatile BillPaymentClient client;

    private static QiwiPayment instance;

    private QiwiPayment() {
        properties = loadConfiguration();
        publicKey = properties.getProperty("qiwi.public_key");
        privateKey = properties.getProperty("qiwi.private_key");
        login = properties.getProperty("qiwi.login");
        client = BillPaymentClientFactory.createDefault(privateKey);
    }

    public static QiwiPayment getInstance() {
        if (instance == null) {
            instance = new QiwiPayment();
        }
        return instance;
    }

    public String getPaymentUrl(int vkId, double sumOfPayment) throws URISyntaxException {
        CreateBillInfo billInfo = new CreateBillInfo(
                UUID.randomUUID().toString(),
                new MoneyAmount(
                        BigDecimal.valueOf(sumOfPayment),
                        Currency.getInstance("RUB")
                ),
                String.valueOf(vkId),
                ZonedDateTime.now().plusDays(3),
                new Customer(
                        "testbot228bot@gmail.com",
                        UUID.randomUUID().toString(),
                        login
                ),
                "http://merchant.ru/success"
        );

        BillResponse response = client.createBill(billInfo);

        userService.saveBillId(userService.getUser(vkId).getId(), billInfo.getBillId());
        String paymentUrl = response.getPayUrl();

        return paymentUrl;
    }

    public String checkPayment(String billId) {
        BillResponse response = null;
        try {
            response = client.getBillInfo(billId);
        } catch (BillPaymentServiceException e) {
            return "MISTAKE";
        }
        if (response.getStatus().getValue().getValue().equals("PAID")) {
            return "PAID";
        } else if (response.getStatus().getValue().getValue().equals("EXPIRED")){
            return "EXPIRED";
        } else if (response.getStatus().getValue().getValue().equals("REJECTED")){
            return "REJECTED";
        } else if (response.getStatus().getValue().getValue().equals("WAITING")){
            return "WAITING";
        }
        return "MISTAKE";
    }

    public String getLinkForPayment(String billId) {
        return client.getBillInfo(billId).getPayUrl();
    }

    public String getAmountForPayment(String billId) {
        MoneyAmount moneyAmount = client.getBillInfo(billId).getAmount();
        return moneyAmount.getValue().toBigInteger().toString();
    }

    public void cancelBill(String billId) {
        client.cancelBill(billId);
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
}
