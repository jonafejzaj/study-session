package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.model.CompareByDateCreated;
import cs3500.pa01.model.CompareByLastModified;
import cs3500.pa01.model.Markdown;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * compare date tester
 */
public class CompareByDateCreatedTest {
  /**
   * test compare by date
   */
  String array = "sampleInput/arrays.md";
  String finalExam = "sampleInput/finalExam.md";
  Path p1 = Path.of(array);
  Path p2 = Path.of(finalExam);
  Markdown m1;
  Markdown m2;
  CompareByLastModified comp;

  /**
   * before each test set time created
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
   * comparing by created
   */
  @Test
  public void testCompare() {

    Comparator<Map.Entry<Path, Markdown>> createdComp = new CompareByDateCreated();

    // modified after
    assertEquals(-1, createdComp.compare(Map.entry(p1, m1),
        Map.entry(p2, m2)));

    // modified before
    assertEquals(1, createdComp.compare(Map.entry(p2, m2),
        Map.entry(p1, m1)));

    // same
    assertEquals(0, createdComp.compare(Map.entry(p2, m2),
        Map.entry(p2, m2)));
  }
}