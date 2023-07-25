package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.controller.StudySessionController;
import cs3500.pa01.model.SrFileOut;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * test for study session
 */
public class StudySessionControllerTest {

  /**
   * reset file
   *
   * @throws IOException if io error occurs
   */
  @AfterEach
  public void afterRan() throws IOException {
    String contents =
        """
          - [[What is the largest lake in North America?:::The largest lake is Lake Superior.]]HARD
          - [[Which country is known as the Land of the Midnight Sun?:::Norway.]]HARD
          - [[What is the official language of Japan?:::The official language is Japanese.]]HARD
          - [[Which country is the driest inhabited continent on Earth?:::Australia.]]HARD
            """;

    File outPut = new File("src/test/resources/testingPA2Dir/testFile.sr");
    new SrFileOut().write(outPut, contents);
  }

  @Test
  public void testRun() throws IOException {
    String testInput = """
        src/test/resources/testingPA2Dir/testFile.sr
        4
        1
        2
        3
        1""";

    String test = """
        Welcome to your study session!
        Which country is the driest inhabited continent on Earth?
        What is the largest lake in North America?
        Which country is known as the Land of the Midnight Sun?
        What is the official language of Japan?
        Total number of questions marked as easy in this session: 2
        Total number of questions marked as hard in this session: 1
        Total number of questions marked as answered in this session: 1
        Total HARD in question bank is: 2
        Total EASY in question bank is: 2
        """;

    Reader input = new StringReader(testInput);
    Appendable output = new StringBuilder();
    StudySessionController controller = new StudySessionController(input, output, new Random(1));
    assertEquals("", output.toString());
    controller.run();
    assertEquals(test, output.toString());
  }
}
