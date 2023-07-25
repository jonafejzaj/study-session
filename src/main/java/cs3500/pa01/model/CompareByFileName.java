package cs3500.pa01.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;

/**
 * represents a file name comparator
 */
public class CompareByFileName implements Comparator<Map.Entry<Path, Markdown>> {
  /**
   * compare by file name
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return negative, pos, or 0 depending on file name
   */
  @Override
  public int compare(Map.Entry<Path, Markdown> o1, Map.Entry<Path, Markdown> o2) {
    return o1.getValue().getName().toLowerCase().compareTo(o2.getValue().getName().toLowerCase());
  }
}
