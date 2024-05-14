package com.transconvert.com.transconvert.core.impl;

import com.transconvert.com.transconvert.core.ParseException;
import com.transconvert.com.transconvert.core.Transaction;
import com.transconvert.com.transconvert.core.TransactionConsumer;
import com.transconvert.com.transconvert.core.TransactionTransfer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Objects;

public class CvsTransactionConsumer implements TransactionConsumer {

    private final String filepath;
    private BufferedWriter writer;
    private boolean closed;

    public CvsTransactionConsumer(String filepath) {
        this.filepath = filepath;
        this.writer = null;
        this.closed = false;
    }

    @Override
    public void onData(Transaction transaction) {
        if (closed) {
            return;
        }

        if (writer == null) {
            try {
                writer = new BufferedWriter(new FileWriter(this.filepath));
            } catch (Exception ex) {
                ex.printStackTrace();
                closed = true;
            }
        }

        try {
            writer.write(toLine(transaction));
        } catch (Exception ex) {
            ex.printStackTrace();
            this.close();
        }
    }

    @Override
    public void onError(String line, ParseException parseException) {
        if (Objects.isNull(line)) {
            return;
        }
        try {
            writer.write(line + "," + parseException.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            this.close();
        }
    }

    @Override
    public void onEnd() {
        this.close();
    }

    private String toLine(Transaction transaction) {
        BigDecimal result = TransactionTransfer.trans(transaction);
        return String.format("%s,%s,%s,%s\r\n",
                transaction.getAmount(),
                transaction.getOriginalCurrency(),
                transaction.getTargetCurrency(),
                result);
    }

    private void close() {
        closed = true;
        try {
            this.writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
