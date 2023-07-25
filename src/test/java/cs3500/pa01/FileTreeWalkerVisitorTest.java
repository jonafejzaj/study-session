package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa01.model.CompareByFileName;
import cs3500.pa01.model.FileTreeWalkerVisitor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * tests for file tree walker class
 */
public class FileTreeWalkerVisitorTest {
  static final String SAMPLE_INPUTS_DIRECTORY = "src/test/resources/testingDir";
  static final Path PATH = Path.of(SAMPLE_INPUTS_DIRECTORY);

  // build list of expected file paths
  ArrayList<Path> expectedFiles = new ArrayList<>();

  /**
   * tests sorting by file name
   */
  @Test
  public void testSortByName() {
    try {
      FileTreeWalkerVisitor tfwv = new FileTreeWalkerVisitor();
      Files.walkFileTree(PATH, tfwv);
      // expected files alphabetical list
      expectedFiles.add(PATH.resolve(Path.of("arrays.md")));
      expectedFiles.add(Path.of("src/test/resources/testingDir/cs3500/lecture3.md"));
      expectedFiles.add(Path.of("src/test/resources/testingDir/cs3500/SOLID/solidPrinciples"
          + ".md"));
      expectedFiles.add(PATH.resolve(Path.of("vectors.md")));

      // actual sorted list
      ArrayList<Path> sorted = tfwv.sortBy(new CompareByFileName());

      assertEquals(4, sorted.size());
      assertArrayEquals(expectedFiles.toArray(), sorted.toArray());
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * tests building a list of all valid Markdown paths in a directory; directory includes sub
   * folders and files that are not ".md"
   */
  @Test
  public void testGetMarkdownPaths() {
    try {
      FileTreeWalkerVisitor tfwv = new FileTreeWalkerVisitor();
      Files.walkFileTree(PATH, tfwv);
      expectedFiles.add(Path.of(SAMPLE_INPUTS_DIRECTORY + "/cs3500/SOLID/solidPrinciples.md"));
      expectedFiles.add(Path.of(SAMPLE_INPUTS_DIRECTORY + "/cs3500/lecture3.md"));
      expectedFiles.add(Path.of(SAMPLE_INPUTS_DIRECTORY + "/arrays.md"));
      expectedFiles.add(Path.of(SAMPLE_INPUTS_DIRECTORY + "/vectors.md"));

      // get list of traversed Markdown file paths
      ArrayList<Path> actualFiles = tfwv.getMarkDownPaths();

      // compare both lists
      assertEquals(4, actualFiles.size());
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * test if file visit failed
   */
  @Test
  public void testVisitFileFailed() {
    try {
      String fakeDir = "fakeDir";
      Path pathFakeDir = Path.of(fakeDir);
      FileTreeWalkerVisitor tfwv = new FileTreeWalkerVisitor();
      Files.walkFileTree(pathFakeDir, tfwv);
      IOException ioe = new IOException("invalid file or directory");
      tfwv.visitFileFailed(pathFakeDir, ioe);

      assertEquals(tfwv.getError(), "error: invalid directory or file");
    } catch (IOException e) {
      fail();
    }
  }
}