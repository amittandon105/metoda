
package com.example.Metoda.util;
import com.example.Metoda.entity.Txn;
import java.util.*;

class DtC {

    private static final String[] STATUS = {"SUCCESS", "FAIL"};
    private static final String[] PAYMENT = {"DOMESTIC", "CROSS"};
    private static final String[] CHANNEL = {"ONLINE", "OFFLINE"};
    private static final String[] RISK = {"LOW", "HIGH"};
    private static final String[] CURRENCY = {"INR", "USD"};

    public static List<Txn> generate(int count) {

        List<Txn> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= count; i++) {

            Txn txn = new Txn();

            txn.setId(i);
            txn.setStatus(STATUS[random.nextInt(2)]);
            txn.setPaymentType(PAYMENT[random.nextInt(2)]);
            txn.setChannel(CHANNEL[random.nextInt(2)]);
            txn.setRisk(RISK[random.nextInt(2)]);
            txn.setCurrency(CURRENCY[random.nextInt(2)]);
            txn.setAmount(100 + random.nextInt(5000));

            list.add(txn);
        }

        return list;
    }
}