package com.print.printer.clients;

import com.print.printer.PrintJob;
import java.util.List;

public interface PrintClient {

  Double getCostPerPrintJob(PrintJob printJob);

  // Returning total cost as String which is formatted to currency format
  String calculatePrintCosts(List<PrintJob> printJobs);
}
