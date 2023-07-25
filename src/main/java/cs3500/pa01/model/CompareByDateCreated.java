package cs3500.pa01.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;

/**
 * Represents a file creation date comparator
 */
public class CompareByDateCreated implements Comparator<Map.Entry<Path, Markdown>> {
  /**
   * compare by date created
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return  negative, pos, or 0 depending on file time
   */
  @Override
  public int compare(Map.Entry<Path, Markdown> o1, Map.Entry<Path, Markdown> o2) {
    return o1.getValue().getCreated().compareTo(o2.getValue().getCreated());
  }
}