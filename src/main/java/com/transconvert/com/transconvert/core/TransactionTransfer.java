package com.transconvert.com.transconvert.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TransactionTransfer {

    private static final Map<String, BigDecimal> transMap = new HashMap<>();

    static {
        transMap.put("USD-EUR", new BigDecimal("0.94"));
        transMap.put("EUR-GBP", new BigDecimal("0.86"));
        transMap.put("GBP-INR", new BigDecimal("103.98"));
        transMap.put("AU-CAD", new BigDecimal("103.98"));
        transMap.put("CAD-USD", new BigDecimal("0.73"));
        transMap.put("CHF-AUD", new BigDecimal("1.69"));
        transMap.put("USD-CHF", new BigDecimal("0.91"));
        transMap.put("JPY-USD", new BigDecimal("0.0065"));
    }


    public static BigDecimal trans(Transaction transaction)  {
        String key = transaction.getOriginalCurrency() + "-" + transaction.getTargetCurrency();
        BigDecimal trans = transMap.get(key);
        if (Objects.nonNull(trans)) {
            return transaction.getAmount().multiply(trans);
        }

        key = transaction.getTargetCurrency() + "-" + transaction.getOriginalCurrency();
        trans = transMap.get(key);
        if (Objects.nonNull(trans)) {
            return transaction.getAmount().divide(trans, RoundingMode.HALF_UP);
        }

        return null;
    }
}
