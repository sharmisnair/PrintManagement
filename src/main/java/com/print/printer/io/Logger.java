package com.print.printer.io;

public interface Logger {
  public void printLine(String string);
  public void printErr(String string);
  public void printException(Exception e);
}
