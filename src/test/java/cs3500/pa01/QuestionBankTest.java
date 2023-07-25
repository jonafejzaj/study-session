package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import cs3500.pa01.model.Difficulty;
import cs3500.pa01.model.Question;
import cs3500.pa01.model.QuestionBank;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing question bank methods
 */


public class QuestionBankTest {
  QuestionBank qb = new QuestionBank();
  ArrayList<Question> quesAnsDiff;

  String srContents =
      """
          - [[What is the largest lake in North America?:::The largest lake is Lake Superior.]]HARD
          - [[Which country is known as the Land of the Midnight Sun?:::Norway.]]HARD
          - [[What is the official language of Japan?:::The official language is Japanese.]]HARD
          - [[Which country is the driest inhabited continent on Earth?:::Australia.]]HARD""";
  ArrayList<Question> actual = qb.questionAnswerDifficulty(srContents);

  /**
   * testing if given string results in question arrayList
   */

  @Test
  public void testQuestionAnswerDifficulty() {
    ArrayList<Question> q = new ArrayList<>();
    q.add(new Question("What is the largest lake in North America?",
        "The largest lake is Lake Superior", Difficulty.HARD));
    q.add(new Question("Which country is known as the Land of the Midnight Sun?",
        "Norway", Difficulty.HARD));
    q.add(new Question("What is the official language of Japan?",
        "The official language is Japanese", Difficulty.HARD));
    q.add(new Question("Which country is the driest inhabited "
        + "continent on Earth?", "Australia", Difficulty.HARD));

    assertArrayEquals(q.toArray(), actual.toArray());
  }

  /**
   * setup
   */
  @BeforeEach
  void setup() {
    quesAnsDiff = new ArrayList<>();
    quesAnsDiff.add(new Question("Which country is the driest inhabited "
        + "continent on Earth?", "Australia", Difficulty.HARD));
    quesAnsDiff.add(new Question("What is the largest lake in North America?",
        "The largest lake is Lake Superior", Difficulty.HARD));
    quesAnsDiff.add(new Question("Which country is known as the Land of the Midnight Sun?",
        "Norway", Difficulty.HARD));
    quesAnsDiff.add(new Question("What is the official language of Japan?",
        "The official language is Japanese", Difficulty.HARD));
  }

  /**
   * testing random question generator
   */
  @Test
  public void testRandQuestion() {
    qb.randQuestion(actual, new Random(1));
    assertArrayEquals(quesAnsDiff.toArray(), actual.toArray());
  }
}
