package com.print.printer;

import com.print.printer.io.FileProcessor;
import com.print.printer.clients.PrintClient;
import com.print.printer.io.Logger;
import java.util.List;

public class PrinterAdminProgram {
  PrintClient printClient;
  FileProcessor fileProcessor;
  Logger logger;

  public PrinterAdminProgram(
      PrintClient printClient,
      FileProcessor fileProcessor,
      Logger logger
  ) {
    this.printClient = printClient;
    this.fileProcessor = fileProcessor;
    this.logger = logger;
  }

  public void logPrintCosts(String filePath) {
    try {
      List<PrintJob> printJobs = fileProcessor.processInputFile(filePath);
      printClient.calculatePrintCosts(printJobs);
    } catch (Exception e) {
      logger.printException(e);
    }
  }
}
