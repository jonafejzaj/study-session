package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import cs3500.pa01.model.Difficulty;
import cs3500.pa01.model.Question;
import org.junit.jupiter.api.Test;

/**
 * testing question class
 */
public class QuestionTest {

  Question q1 = new Question("What is the largest lake in North America?",
      "The largest lake is Lake Superior", Difficulty.HARD);
  Question q2 = new Question("Which country is known as the Land of the Midnight Sun?",
      "Norway", Difficulty.EASY);
  Question q3 = new Question("What is the largest lake in North America?",
      "The largest lake is Lake Superior", Difficulty.HARD);

  /**
   * testing get question
   */
  @Test
  public void testGetQuestion() {
    String actual = q1.getQuestion();
    assertEquals("What is the largest lake in North America?", actual);
  }

  /**
   * testing get difficulty
   */
  @Test
  public void testGetAnswer() {
    String actual = q2.getAnswer();
    assertEquals("Norway", actual);
  }

  /**
   * testing difficulty
   */
  @Test
  public void testGetDifficulty() {
    assertEquals(Difficulty.HARD, q1.getDifficulty());
    assertEquals(Difficulty.EASY, q2.getDifficulty());
  }

  /**
   * testing equality
   */
  @Test
  public void testEquality() {
    assertEquals(q1, q3);
    assertNotEquals(q1, q2);
  }
}
