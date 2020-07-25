package com.print.printer.io;

public class ConsoleLogger implements Logger {

  @Override
  public void printLine(String string) {
    System.out.println(string);
  }

  @Override
  public void printErr(String string) {
    System.err.println(string);
  }

  @Override
  public void printException(Exception e) {
    e.printStackTrace();
  }

}