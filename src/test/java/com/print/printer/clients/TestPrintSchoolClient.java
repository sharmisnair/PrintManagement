package com.print.printer.clients;
import static org.assertj.core.api.Assertions.assertThat;

import com.print.printer.PrintJob;
import com.print.printer.io.ConsoleLogger;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TestPrintSchoolClient {

  @Test
  public void getCostPerPrintJob_testAllColoredPagesDoubleSided() throws Exception {
      PrintJob printJob = new PrintJob("10", "10", "TRUE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(2);
  }

  @Test
  public void getCostPerPrintJob_testAllBWPagesDoubleSided() throws Exception {
      PrintJob printJob = new PrintJob("10", "0", "TRUE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(1);
  }

  @Test
  public void getCostPerPrintJob_testAllMixedPagesDoubleSided() throws Exception {
      PrintJob printJob = new PrintJob("10", "5", "TRUE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(1.50);
  }

  @Test
  public void getCostPerPrintJob_testAllColoredPagesSingleSided() throws Exception {
      PrintJob printJob = new PrintJob("10", "10", "FALSE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(2.5);
  }

  @Test
  public void getCostPerPrintJob_testAllBWPagesSingleSided() throws Exception {
      PrintJob printJob = new PrintJob("10", "0", "FALSE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(1.5);
  }

  @Test
  public void getCostPerPrintJob_testAllMixedPagesSingleSided() throws Exception {
      PrintJob printJob = new PrintJob("10", "5", "FALSE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(2);
  }

  @Test
  public void calculatePrintCosts_testSumJobs() throws Exception {
      List<PrintJob> printJobList = new ArrayList<>();
      printJobList.add(new PrintJob("10", "5", "TRUE"));
      printJobList.add(new PrintJob("10", "5", "FALSE"));

      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.calculatePrintCosts(printJobList)).isEqualTo("$3.50");
  }

  @Test
  public void calculatePrintCosts_testSumRounding() throws Exception {
      List<PrintJob> printJobList = new ArrayList<>();
      printJobList.add(new PrintJob("25", "10", "FALSE"));
      printJobList.add(new PrintJob("55", "13", "TRUE"));
      printJobList.add(new PrintJob("502","22","TRUE"));
      printJobList.add(new PrintJob("1","0","FALSE"));

      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.calculatePrintCosts(printJobList)).isEqualTo("$64.10");
  }
}
