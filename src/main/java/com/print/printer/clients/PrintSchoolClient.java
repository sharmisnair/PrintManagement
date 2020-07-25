package com.print.printer.clients;

import com.print.printer.PrintJob;
import com.print.printer.io.Logger;
import com.print.printer.properties.PrintInkType;
import com.print.printer.properties.PrintJobType;
import com.print.printer.properties.PrintPageSize;
import java.util.List;
import java.util.Map;


public class PrintSchoolClient implements PrintClient {

  Logger logger;
  Double totalCostAllJobs;

  final Map<PrintPageSize, Map<PrintJobType, Map<PrintInkType, Double>>> costMap = Map.of(
      PrintPageSize.A4, Map.of(
          PrintJobType.SINGLE_SIDED, Map.of(
              PrintInkType.BLACK_AND_WHITE, 0.15,
              PrintInkType.COLOR, 0.25
          ),
          PrintJobType.DOUBLE_SIDED, Map.of(
              PrintInkType.BLACK_AND_WHITE, 0.10,
              PrintInkType.COLOR, 0.20
          )
      )
  );

  public PrintSchoolClient(Logger logger) {
    this.logger = logger;
    totalCostAllJobs = 0.0;
  }

  @Override
  public Double getCostPerPrintJob(PrintJob printJob) {
    Double costPerJob = 0.0;

    // Sum the color pages cost
    costPerJob += printJob.getNumColorPages() * (costMap
        .get(printJob.getPageSize())
        .get(printJob.getJobType())
        .get(PrintInkType.COLOR));

    // Sum the black and white pages cost
    costPerJob += printJob.getNumBlackAndWhitePages() * (costMap
        .get(printJob.getPageSize())
        .get(printJob.getJobType())
        .get(PrintInkType.BLACK_AND_WHITE));

    return costPerJob;
  }

  @Override
  public String logPrintCosts(List<PrintJob> printJobs) {
    String totalCostString;
    for (PrintJob printJob : printJobs) {
      Double jobCost = getCostPerPrintJob(printJob);
      logger.printLine(formatMoney(jobCost));
      totalCostAllJobs += jobCost;
    }
    totalCostString = formatMoney(totalCostAllJobs);

    logger.printLine("Total cost of all print jobs:" + totalCostString);

    return totalCostString;
  }

  private String formatMoney(Double amount) {
    return String.format("%.2f", amount);
  }

}