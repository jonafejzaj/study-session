package cs3500.pa01.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;

/**
 * Represents a file date last modified comparator
 */
public class CompareByLastModified implements Comparator<Map.Entry<Path, Markdown>> {
  /**
   * compare files by date last modified
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return negative, pos, or 0 depending on file time
   */
  @Override
  public int compare(Map.Entry<Path, Markdown> o1, Map.Entry<Path, Markdown> o2) {
    return o1.getValue().getMod().compareTo(o2.getValue().getMod());
  }
}
