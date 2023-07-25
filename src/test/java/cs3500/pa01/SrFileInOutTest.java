package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.model.SrFileIn;
import cs3500.pa01.model.SrFileOut;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * testing sr generator
 */
public class SrFileInOutTest {
  Path p1 = (Path.of("src/test/resources/testingPA2Dir/testFile.md"));
  Path p2 = (Path.of("src/test/resources/testingPA2Dir/facts.md"));
  Path p3 = (Path.of("sampleInput/testFile.md"));
  Path p4 = (Path.of("sampleInput/testFile.sr"));
  Path p5 = (Path.of("src/test/resources/testingPA2Dir/facts.sr"));
  Path p6 = (Path.of("src/test/resources/testingPA2Dir/blank.sr"));
  SrFileIn srg = new SrFileIn();
  SrFileOut sro = new SrFileOut();

  /**
   * tests if q&a string contents are being obtained
   */
  @Test
  public void testRead() throws FileNotFoundException {
    try {
      ArrayList<Path> pathList = new ArrayList<>(Arrays.asList(p1, p2));
      String actual = srg.read(pathList);
      String expected = """
       - [[Which country is known as the Land of the Midnight Sun?:::Norway.]]HARD
       - [[What is the official language of Japan?:::The official language is Japanese.]]HARD
       - [[Which country is the driest inhabited continent on Earth?:::Australia.]]HARD
       - [[What is the largest lake in North America? ::: The largest lake is Lake Superior.]]HARD
          """;
      assertEquals(expected, actual);
    } catch (FileNotFoundException f) {
      throw new FileNotFoundException();
    }
  }

  @Test
  public void testReadSr() throws FileNotFoundException {
    ArrayList<Path> pathList = new ArrayList<>(Arrays.asList(p5, p6));
    String actual = srg.readSr(pathList);
    String expected = """
       - [[Which country is known as the Land of the Midnight Sun?:::Norway.]]HARD
       - [[What is the official language of Japan?:::The official language is Japanese.]]EASY
       - [[Which country is the driest inhabited continent on Earth?:::Australia.]]EASY
       - [[What is the largest lake in North America? ::: The largest lake is Lake Superior.]]HARD
          """;
    assertEquals(expected, actual);
  }

  /**
   * testing if string input writes to sr output with same name as .md
   */
  @Test
  public void testWrite() throws FileNotFoundException {
    try {
      // let content be written to new file with the name "studyGuide.md"
      ArrayList<Path> pathList = new ArrayList<>(Arrays.asList(p1, p2));
      String actual = srg.read(pathList);
      sro.write(p3.toFile(), actual);
      assertTrue(p3.toFile().exists());

      // sr file exists
      assertTrue(p4.toFile().exists());

    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
