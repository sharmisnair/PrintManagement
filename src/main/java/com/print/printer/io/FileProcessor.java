package com.print.printer.io;

import com.print.printer.PrintJob;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileProcessor {
  public List<PrintJob> processInputFile(String inputFilePath) throws FileNotFoundException, IOException, Exception;
}
