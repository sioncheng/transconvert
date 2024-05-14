package com.transconvert.com.transconvert.core;

import com.transconvert.com.transconvert.core.impl.CvsTransactionConsumer;

public class TransactionConsumerFactory {

    public static TransactionConsumer buildConsumer(FileFormatEnum formatEnum, String filepath) throws ParseException {
        if (formatEnum == FileFormatEnum.CVS) {
            return new CvsTransactionConsumer(filepath);
        }

        throw new ParseException(String.format("unknown %s", formatEnum.name()));
    }
}
