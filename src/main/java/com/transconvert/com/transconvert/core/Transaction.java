package com.transconvert.com.transconvert.core;

import java.math.BigDecimal;

public class Transaction {

    private BigDecimal amount;

    private String originalCurrency;

    private String targetCurrency;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Transaction(BigDecimal amount, String originalCurrency, String targetCurrency) {
        this.amount = amount;
        this.originalCurrency = originalCurrency;
        this.targetCurrency = targetCurrency;
    }
}
