package com.print.printer.clients;

import com.print.printer.PrintJob;
import com.print.printer.io.Logger;
import com.print.printer.properties.PrintInkType;
import com.print.printer.properties.PrintJobType;
import com.print.printer.properties.PrintPageSize;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PrintSchoolClient implements PrintClient {

  Logger logger;
  private static final String DEFAULT_CURRENCY = "$";

  private static final Map<PrintPageSize, Map<PrintJobType, Map<PrintInkType, Double>>> costMap = Map.of(
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
  public String calculatePrintCosts(List<PrintJob> printJobs) {

    Map<PrintJob, String> printJobsCostMap = new HashMap<>();
    Double totalCostAllJobs = 0.0;
    String totalCostAsString;

    for (PrintJob printJob : printJobs) {
      Double jobCost = getCostPerPrintJob(printJob);
      printJobsCostMap.put(printJob, formatMoney(jobCost));
      totalCostAllJobs += jobCost;
    }
    totalCostAsString = formatMoney(totalCostAllJobs);
    logPrintJobsForClient(printJobsCostMap, totalCostAsString);
    return totalCostAsString;
  }

  private void logPrintJobsForClient(Map<PrintJob, String> printJobsCostMap, String totalCost) {
    printJobsCostMap.forEach((printJob, jobCost) -> {
      logger.print("Printing Cost: " + jobCost + " FOR" +
          " pageSize= " + printJob.getPageSize() +
          " jobType= " + printJob.getJobType() +
          " color= " + printJob.getNumColorPages() +
          " B&W=" + printJob.getNumBlackAndWhitePages() + "\n");
    });
    logger.printLine("Total cost of all print jobs:" + totalCost);
  }

  private String formatMoney(Double amount) {
    return String.format(DEFAULT_CURRENCY + "%.2f", amount);
  }

}