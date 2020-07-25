package com.print;

import com.print.printer.PrinterAdminProgram;
import com.print.printer.io.ConsoleLogger;
import com.print.printer.io.CsvProcessor;
import com.print.printer.clients.PrintSchoolClient;

public class Main {

    private static final String DEFAULT_FILE_PATH = "src/main/resources/samplePrintJobs.csv";

    public static void main(String[] args) {

        String filePath = args.length != 1 ? DEFAULT_FILE_PATH : args[0];

        printSchoolCosts(filePath);
    }

    public static void printSchoolCosts(String filePath) {
        CsvProcessor csvProcessor = new CsvProcessor();
        ConsoleLogger consoleLogger = new ConsoleLogger();
        PrintSchoolClient schoolClient = new PrintSchoolClient(consoleLogger);

        PrinterAdminProgram printerAdminProgram =
            new PrinterAdminProgram(schoolClient, csvProcessor, consoleLogger);

        printerAdminProgram.logPrintCosts(filePath);
    }
}