package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa01.model.CompareByFileName;
import cs3500.pa01.model.FileTreeWalkerVisitor;
import cs3500.pa01.model.MdFileIn;
import cs3500.pa01.model.MdFileOut;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * testing file read & write
 */
public class FileInOutTest {
  static final String SAMPLE_INPUTS_DIRECTORY = "src/test/resources/testingDir";
  static final Path PATH = Path.of(SAMPLE_INPUTS_DIRECTORY);
  static final FileTreeWalkerVisitor tfwv = new FileTreeWalkerVisitor();
  MdFileIn fi = new MdFileIn();
  MdFileOut fo = new MdFileOut();

  /**
   *
   */
  @AfterEach
  public void afterRan() {
    // delete file
    File outPut = new File("sampleInput/studySheet.md");
    outPut.delete();
  }

  /**
   * test to see if string is written to file
   */
  @Test
  public void testWriteToFile() {
    try {
      File outPut = new File("sampleInput/studySheet.md");
      assertFalse(outPut.exists());

      Files.walkFileTree(PATH, tfwv);
      ArrayList<Path> sorted = tfwv.sortBy(new CompareByFileName());
      String sortedByFileNameString = fi.read(sorted);
      fo.write(outPut, sortedByFileNameString);

      assertTrue(outPut.exists());
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * test for writing to existing file
   */
  @Test
  public void writeToExistingFile() {
    try {
      File outPut = new File("sampleInput/testFile.md");
      assertTrue(outPut.exists());

      Files.walkFileTree(PATH, tfwv);
      ArrayList<Path> sorted = tfwv.sortBy(new CompareByFileName());
      String sortedByFileNameString = fi.read(sorted);
      fo.write(outPut, sortedByFileNameString);

      assertTrue(sortedByFileNameString.contains("Arrays"));

    } catch (IOException e) {
      fail();
    }
  }

  /**
   * read frm file test
   */
  @Test
  public void testReadFromFileSortedByName() {
    try {
      // reading from sorted arrayList of path sorted by file name
      FileTreeWalkerVisitor tfwv = new FileTreeWalkerVisitor();
      Files.walkFileTree(PATH, tfwv);

      ArrayList<Path> expectedFiles = new ArrayList<>();
      expectedFiles.add(PATH.resolve(Path.of("arrays.md")));
      expectedFiles.add(Path.of("src/test/resources/testingDir/cs3500/lecture3.md"));
      expectedFiles.add(Path.of("src/test/resources/testingDir/cs3500/SOLID/solidPrinciples."
          + "md"));
      expectedFiles.add(PATH.resolve(Path.of("vectors.md")));

      ArrayList<Path> sorted = tfwv.sortBy(new CompareByFileName());

      assertEquals(fi.read(expectedFiles), fi.read(sorted));
    } catch (IOException e) {
      fail();
    }
  }
}