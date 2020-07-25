package com.print.printer.clients;
import static org.assertj.core.api.Assertions.assertThat;

import com.print.printer.PrintJob;
import com.print.printer.io.ConsoleLogger;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TestPrintSchoolClient {

  @Test
  public void getCostPerPrintJob_testAllColoredPagesDoubleSided() {
    try {
      PrintJob printJob = new PrintJob("10", "10", "TRUE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(2);
    } catch (Exception e) {
      //Not expecting an exception
      assert(false);
    }
  }

  @Test
  public void getCostPerPrintJob_testAllBWPagesDoubleSided() {
    try {
      PrintJob printJob = new PrintJob("10", "0", "TRUE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(1);
    } catch (Exception e) {
      //Not expecting an exception
      assert(false);
    }
  }

  @Test
  public void getCostPerPrintJob_testAllMixedPagesDoubleSided() {
    try {
      PrintJob printJob = new PrintJob("10", "5", "TRUE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(1.50);
    } catch (Exception e) {
      //Not expecting an exception
      assert(false);
    }
  }

  @Test
  public void getCostPerPrintJob_testAllColoredPagesSingleSided() {
    try {
      PrintJob printJob = new PrintJob("10", "10", "FALSE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(2.5);
    } catch (Exception e) {
        //Not expecting an exception
        assert(false);
      }
  }

  @Test
  public void getCostPerPrintJob_testAllBWPagesSingleSided() {
    try {
      PrintJob printJob = new PrintJob("10", "0", "FALSE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(1.5);
    } catch (Exception e) {
      //Not expecting an exception
      assert(false);
    }
  }

  @Test
  public void getCostPerPrintJob_testAllMixedPagesSingleSided() {
    try {
      PrintJob printJob = new PrintJob("10", "5", "FALSE");
      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.getCostPerPrintJob(printJob)).isEqualTo(2);
    } catch (Exception e) {
      //Not expecting an exception
      assert(false);
    }
  }

  @Test
  public void logPrintCosts_testSumJobs() {
    try {
      List<PrintJob> printJobList = new ArrayList<>();
      printJobList.add(new PrintJob("10", "5", "TRUE"));
      printJobList.add(new PrintJob("10", "5", "FALSE"));

      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.logPrintCosts(printJobList)).isEqualTo("3.50");
    } catch (Exception e) {
      //Not expecting an exception
      assert(false);
    }
  }

  @Test
  public void logPrintCosts_testSumRounding() {
    try {
      List<PrintJob> printJobList = new ArrayList<>();
      printJobList.add(new PrintJob("25", "10", "FALSE"));
      printJobList.add(new PrintJob("55", "13", "TRUE"));
      printJobList.add(new PrintJob("502","22","TRUE"));
      printJobList.add(new PrintJob("1","0","FALSE"));

      PrintSchoolClient printSchoolClient = new PrintSchoolClient(new ConsoleLogger());
      assertThat(printSchoolClient.logPrintCosts(printJobList)).isEqualTo("64.10");
    } catch (Exception e) {
      //Not expecting an exception
      assert(false);
    }
  }
}
