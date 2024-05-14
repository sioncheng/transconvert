package com.transconvert.com.transconvert.core;

public class TransferEngine {

    public static void transfer(FileFormatEnum format, String inputFile, String outputFile) throws Exception {

        TransactionParser transactionParser = TransactionParserFactory.buildParser(format, inputFile);
        TransactionConsumer transactionConsumer = TransactionConsumerFactory.buildConsumer(format, outputFile);

        transactionParser.parse(transactionConsumer);
    }
}
