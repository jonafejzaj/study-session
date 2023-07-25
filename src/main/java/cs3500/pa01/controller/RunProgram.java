package cs3500.pa01.controller;

import java.io.IOException;

/**
 * interface for running the program based on study guide or study session
 */
public interface RunProgram {
  /**
   * job is to connect pieces of program together to run properly in main
   *
   * @throws IOException if io error occurs
   */
  void run() throws IOException;
}
