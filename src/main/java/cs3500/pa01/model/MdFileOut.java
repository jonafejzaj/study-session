package cs3500.pa01.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *  for writing files
 */
public class MdFileOut implements Writer {
  /**
   * writes string content to md file
   *
   * @param file     file to write contents to
   * @param contents string of all contents contained in list of paths
   * @throws IOException if IO error occurs
   */
  public void write(File file, String contents) throws IOException {
    Path path = Path.of(file.getPath());

    // convert string to data ("raw" byte data)
    byte[] data = contents.getBytes();

    if (file.exists()) {
      // overwrites if file already exists
      FileWriter sg = new FileWriter(file, false);
      sg.flush();
      sg.write(contents);
      sg.close();
    } else {
      try {
        // write to new path in directory
        Files.write(path, data);
        //Files.write(sr, srData);
      } catch (IOException e) {
        throw new IOException(e);
      }
    }
  }
}