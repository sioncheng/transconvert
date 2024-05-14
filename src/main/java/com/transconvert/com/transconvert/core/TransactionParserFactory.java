package com.transconvert.com.transconvert.core;

import com.transconvert.com.transconvert.core.impl.CvsTransactionParser;

public class TransactionParserFactory {

    public static TransactionParser buildParser(FileFormatEnum formatEnum, String filepath) throws ParseException {
        if (formatEnum == FileFormatEnum.CVS) {
            return new CvsTransactionParser(filepath);
        }

        throw new ParseException(String.format("unknown %s", formatEnum.name()));
    }
}
