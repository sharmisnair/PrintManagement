package com.print.printer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.print.printer.properties.PrintJobType;
import com.print.printer.properties.PrintPageSize;
import org.junit.Test;

public class TestPrintJob {

  @Test
  public void PrintJob_testSingleSidedConstruction() throws Exception {
      PrintJob printJob  = new PrintJob("10", "5", "FALSE");
      assertThat(printJob.getPageSize()).isEqualTo(PrintPageSize.A4);
      assertThat(printJob.getJobType()).isEqualTo(PrintJobType.SINGLE_SIDED);
      assertThat(printJob.getNumColorPages()).isEqualTo(5);
      assertThat(printJob.getNumBlackAndWhitePages()).isEqualTo(5);
  }

  @Test
  public void PrintJob_testDoubleSidedConstruction() throws Exception {
      PrintJob printJob  = new PrintJob("10", "5", "TRUE");
      assertThat(printJob.getPageSize()).isEqualTo(PrintPageSize.A4);
      assertThat(printJob.getJobType()).isEqualTo(PrintJobType.DOUBLE_SIDED);
      assertThat(printJob.getNumColorPages()).isEqualTo(5);
      assertThat(printJob.getNumBlackAndWhitePages()).isEqualTo(5);
  }

  @Test
  public void PrintJob_InvalidNumConstruction() throws Exception {

    assertThatExceptionOfType(Exception.class)
        .isThrownBy(() -> new PrintJob("10s", "5", "FALSE"))
        .withMessageContaining("Error processing print job:");
  }

  @Test
  public void PrintJob_InvalidColorNumConstruction() throws Exception {

    assertThatExceptionOfType(Exception.class)
        .isThrownBy(() -> new PrintJob("10", "x", "FALSE"))
        .withMessageContaining("Error processing print job:");
  }

  @Test
  public void PrintJob_InvalidDoubleSidedConstruction() throws Exception {

    assertThatExceptionOfType(Exception.class)
        .isThrownBy(() -> new PrintJob("10", "5", "YES"))
        .withMessageContaining("Error processing print job:");
  }

}
