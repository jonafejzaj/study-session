package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * testing main
 */
public class DriverTest {
  /**
   * delete file after test
   */
  @AfterEach
  public void afterRan() {
    // delete file
    File outPut = new File("sampleInput/studyGuide.md");
    File outPutSr = new File("sampleInput/studyGuide.sr");
    outPut.delete();
    outPutSr.delete();
  }

  /**
   * testing wrong argument - invalid path
   */
  @Test
  public void testWrongFirstArg() {
    assertThrows(FileNotFoundException.class, () -> Driver.main(new String[] {"invalidPath",
        "created", "src/test/resources/testingDir/file.md"}), "path must exist");
  }

  /**
   * testing valid input
   */
  @Test
  public void testCorrectInput() {
    String[] validArg1 = new String[] {"src/test/resources/testingDir", "filename",
        "sampleInput/studyGuide.md"};
    assertDoesNotThrow(() -> Driver.main(validArg1));

    String[] validArg2 = new String[] {"src/test/resources/testingDir", "created",
        "sampleInput/studyGuide.md"};
    assertDoesNotThrow(() -> Driver.main(validArg2));

    String[] validArg3 = new String[] {"src/test/resources/testingDir", "modified",
        "sampleInput/studyGuide.md"};
    assertDoesNotThrow(() -> Driver.main(validArg3));
  }
}
