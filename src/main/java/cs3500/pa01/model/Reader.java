package cs3500.pa01.model;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Reads contents from an input to a string
 */
public interface Reader {
  /**
   * read files in list and concat to one string
   *
   * @param pathList list of paths to get contents from
   * @return string of file contents
   * @throws FileNotFoundException if file not found
   */
  String read(ArrayList<Path> pathList) throws FileNotFoundException;
}
