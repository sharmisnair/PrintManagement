package com.print.printer.io;
import com.print.printer.PrintJob;
import java.io.FileReader;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.List;

public class CsvProcessor implements FileProcessor {

  private static final char SEPARATOR = ',';
  private static final List<String> JOBS_CSV_HEADER = new ArrayList<>(List.of(
      "Total Pages",
      "Color Pages",
      "Double Sided"
  ));

  @Override
  public List<PrintJob> processInputFile(String inputFilePath) throws Exception {

    //Create the CSVFormat object
    CSVFormat format = CSVFormat.DEFAULT
        .withHeader()
        .withDelimiter(SEPARATOR)
        .withTrim()
        .withIgnoreHeaderCase();

    //initialize the CSVParser object
    CSVParser parser = new CSVParser(new FileReader(inputFilePath), format);

    List<PrintJob> printJobs = new ArrayList<>();
    for(CSVRecord record : parser){
      PrintJob printJob = new PrintJob(
          record.get(JOBS_CSV_HEADER.get(0)),
          record.get(JOBS_CSV_HEADER.get(1)),
          record.get(JOBS_CSV_HEADER.get(2)));
      printJobs.add(printJob);
    }
    //close the parser
    parser.close();

    return printJobs;
  }
}