package com.print.printer;

import com.print.printer.properties.PrintJobType;
import com.print.printer.properties.PrintPageSize;
import java.util.stream.Stream;

public class PrintJob {

  private static final String DEFAULT_PAGE_SIZE = "A4";

  private PrintPageSize pageSize;
  private PrintJobType jobType;
  private Integer numColorPages;
  private Integer numBlackAndWhitePages;

  public PrintPageSize getPageSize() {
    return pageSize;
  }

  public PrintJobType getJobType() {
    return jobType;
  }

  public Integer getNumColorPages() {
    return numColorPages;
  }

  public Integer getNumBlackAndWhitePages() {
    return numBlackAndWhitePages;
  }

  public PrintJob(String numTotalPagesString, String numColorPagesString, String doubleSided)
      throws Exception {

    try {
      this.pageSize = PrintPageSize.valueOf(DEFAULT_PAGE_SIZE);

      if (!Stream.of("true","false").anyMatch(doubleSided::equalsIgnoreCase)) {
        throw new Exception("Incorrect value for doubleSided (expecting TRUE or FALSE)= "
            + doubleSided);
      }
      this.jobType = Boolean.parseBoolean(doubleSided) ?
          PrintJobType.DOUBLE_SIDED :
          PrintJobType.SINGLE_SIDED;

      Integer numTotalPages = Integer.parseInt(numTotalPagesString);
      this.numColorPages = Integer.parseInt(numColorPagesString);
      this.numBlackAndWhitePages = numTotalPages - this.numColorPages;

    } catch (Exception e) {
      throw new Exception("Error processing print job:"
          + " numTotalPagesString=" + numTotalPagesString
          + " numColorPagesString=" + numColorPagesString
          + " doubleSided=" + doubleSided
          + " : " + e.getMessage());
    }
  }

}
