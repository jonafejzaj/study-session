package cs3500.pa01.model;

import java.io.File;
import java.io.IOException;

/**
 * writes contents to file
 */
public interface Writer {
  /**
   * writes given input to file
   *
   * @param file     file to write to
   * @param contents what to write to file
   * @throws IOException if io error occurs
   */
  void write(File file, String contents) throws IOException;
}
