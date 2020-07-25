package com.print.printer.clients;

import com.print.printer.PrintJob;
import java.util.List;

public interface PrintClient {

  Double getCostPerPrintJob(PrintJob printJob);

  String logPrintCosts(List<PrintJob> printJobs);
}
