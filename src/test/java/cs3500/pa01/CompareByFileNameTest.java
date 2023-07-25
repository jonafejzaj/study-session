package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.model.CompareByFileName;
import cs3500.pa01.model.CompareByLastModified;
import cs3500.pa01.model.Markdown;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * compare by name
 */
public class CompareByFileNameTest {
  String array = "sampleInput/arrays.md";
  String finalExam = "sampleInput/finalExam.md";
  Path p1 = Path.of(array);
  Path p2 = Path.of(finalExam);
  Markdown m1;
  Markdown m2;
  CompareByLastModified comp;

  /**
   * before each test set time modified
   */

  @BeforeEach
  public void setUp() {
    p1 = Path.of("sampleInput/cs3500/Arrays.md");
    p2 = Path.of("sampleInput/cs3500/Vectors.md");

    m1 = new Markdown(p1, 1L, 1L);
    m2 = new Markdown(p2, 2L, 2L);

    comp = new CompareByLastModified();
  }

  /**
   * testing compare method
   */
  @Test
  public void testCompare() {
    Path p = Path.of("sampleInput/Arrays.md");
    Path q = Path.of("sampleInput/finalExam.md");

    Comparator<Map.Entry<Path, Markdown>> fileNameComp = new CompareByFileName();

    // comes before
    assertEquals(-21, fileNameComp.compare(Map.entry(p, m1), Map.entry(q,
        m2)));

    // comes after
    assertEquals(21, fileNameComp.compare(Map.entry(q, m2), Map.entry(p,
        m1)));

    // same
    assertEquals(0, fileNameComp.compare(Map.entry(q, m2), Map.entry(q,
        m2)));
  }
}
