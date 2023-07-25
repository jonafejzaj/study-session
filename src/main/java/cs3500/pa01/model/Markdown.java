package cs3500.pa01.model;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * aid in file times
 */
public class Markdown {
  private final Path path;
  private final FileTime created;
  private final FileTime mod;

  /**
   *
   * @param p path
   * @param c created time
   * @param m modified time
   */
  public Markdown(Path p, Long c, Long m) {
    path = p;
    created = FileTime.fromMillis(c);
    mod = FileTime.fromMillis(m);
  }

  /**
   *
   * @return created time
   */
  public FileTime getCreated() {
    return created;
  }

  /**
   *
   * @return modified time
   */
  public FileTime getMod() {
    return mod;
  }

  /**
   *
   * @return file name
   */
  public String getName() {
    return path.getFileName().toString();
  }
}
