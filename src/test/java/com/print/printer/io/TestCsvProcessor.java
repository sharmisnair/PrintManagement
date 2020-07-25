package com.print.printer.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.print.printer.PrintJob;
import java.util.List;
import org.junit.Test;

public class TestCsvProcessor {

  private static final String DEFAULT_FILE_DIR = "src/test/resources/";

  @Test
  public void processInputFile_testValidWithSpacesCSV() {
    try {
      FileProcessor fileProcessor = new CsvProcessor();
      List<PrintJob> printJobs = fileProcessor
          .processInputFile(DEFAULT_FILE_DIR + "validTest.csv");

      assertThat(printJobs.size()).isEqualTo(4);

    } catch (Exception e) {
      //Not expecting an exception
      assert (false);
    }
  }


  @Test
  public void processInputFile_testInValidHeaderCSV() {

    FileProcessor fileProcessor = new CsvProcessor();
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> fileProcessor
            .processInputFile(DEFAULT_FILE_DIR + "invalidHeaderTest.csv"))
        .withMessageContaining("Mapping for Color Pages not found, "
            + "expected one of [Double Sided, Incorrect stuff here, Total Pages]");
  }

  @Test
  public void processInputFile_testIgnoreExtraColumnCSV() {

    try {
      FileProcessor fileProcessor = new CsvProcessor();
      List<PrintJob> printJobs = fileProcessor
          .processInputFile(DEFAULT_FILE_DIR + "validTest.csv");

      assertThat(printJobs.size()).isEqualTo(4);

    } catch (Exception e) {
      //Not expecting an exception
      assert (false);
    }
  }
}
