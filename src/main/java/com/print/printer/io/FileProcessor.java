package com.print.printer.io;

import com.print.printer.PrintJob;
import java.util.List;

public interface FileProcessor {
  List<PrintJob> processInputFile(String inputFilePath) throws Exception;
}
