package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * testing study guide controller
 */
public class StudyGuideControllerTest {

  /**
   * invalid ordering flag
   */
  @Test
  public void wrongOrdering() {
    assertThrows(IllegalArgumentException.class,
        () -> Driver.main(new String[]{"sampleInput", "invalidOrder", "sampleInput/studyGuide.md"}),
        "Not a valid input");
  }
}
