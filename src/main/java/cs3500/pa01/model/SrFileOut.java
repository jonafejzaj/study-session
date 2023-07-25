package cs3500.pa01.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * write to sr file
 */
public class SrFileOut implements Writer {
  /**
   * write qa content to ".sr" file with same name as given md file
   *
   * @param file     output file
   * @param contents string written to file
   * @throws IOException if io error occurs
   */
  public void write(File file, String contents) throws IOException {
    Path path = Path.of(file.getPath());
    String fileName = file.getName();
    String fileNameNoExt = fileName.substring(0, fileName.lastIndexOf("."));
    Path sr = path.resolveSibling(fileNameNoExt + ".sr");

    byte[] srData = contents.getBytes();

    try {
      Files.write(sr, srData);
    } catch (IOException e) {
      throw new IOException(e);
    }
  }
}