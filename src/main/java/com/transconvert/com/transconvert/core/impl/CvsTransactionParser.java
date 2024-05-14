package com.transconvert.com.transconvert.core.impl;

import com.transconvert.com.transconvert.core.TransactionParser;
import com.transconvert.com.transconvert.core.ParseException;
import com.transconvert.com.transconvert.core.Transaction;
import com.transconvert.com.transconvert.core.TransactionConsumer;

import java.io.*;
import java.math.BigDecimal;

public class CvsTransactionParser implements TransactionParser {

    private final String filepath;

    public CvsTransactionParser(String filepath) {
        this.filepath = filepath;

    }

    @Override
    public void parse(TransactionConsumer consumer) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    consumer.onData(parseTransaction(line));
                } catch (ParseException pe) {
                    consumer.onError(line, pe);
                }
            }
        } catch (IOException e) {
            consumer.onError(null, new ParseException("file exception " + e.getMessage()));
        }

        consumer.onEnd();
    }

    private Transaction parseTransaction(String line) throws ParseException {
        try {
            String[] arr = line.split(",");
            BigDecimal amount = new BigDecimal(arr[0]);
            String originalCurrency = arr[1].trim();
            String targetCurrency = arr[2].trim();
            return new Transaction(amount, originalCurrency, targetCurrency);
        } catch (Exception ex) {
            throw new ParseException("parse line exception " + ex.getMessage());
        }
    }
}
