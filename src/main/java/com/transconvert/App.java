package com.transconvert;

import com.transconvert.com.transconvert.core.FileFormatEnum;
import com.transconvert.com.transconvert.core.TransferEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);

        if (args.length < 3) {
            System.out.println(args[0]);
            System.out.println("please enter format, input file path and output file path");
            return;
        }

        FileFormatEnum formatEnum = null;
        if (FileFormatEnum.CVS.name().equals(args[0])) {
            formatEnum = FileFormatEnum.CVS;
        } else if (FileFormatEnum.JSON.name().equals(args[1])) {
            formatEnum = FileFormatEnum.JSON;
        } else if (FileFormatEnum.XML.name().equals(args[2])) {
            formatEnum = FileFormatEnum.XML;
        }

        try {
            TransferEngine.transfer(formatEnum, args[1], args[2]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
