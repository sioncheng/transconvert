package com.transconvert.com.transconvert.core;

public interface TransactionConsumer {

    void onData(Transaction transaction);

    void onError(String line, ParseException parseException);

    void onEnd();
}
