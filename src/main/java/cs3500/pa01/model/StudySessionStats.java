package cs3500.pa01.model;

import static java.lang.String.valueOf;

import java.util.ArrayList;

/**
 * counters for study session statistics
 */
public class StudySessionStats {
  int countEasy = 0;

  /**
   * counts number of questions changed from hard to easy
   *
   * @return number easy
   */
  public int countEasy() {
    return countEasy++;
  }

  int countHard = 0;

  /**
   * counts number of questions changed from easy to hard
   *
   * @return number hard
   */
  public int countHard() {
    return countHard++;
  }

  int countAnswered = 0;

  /**
   * counts number of questions user saw answer to
   *
   * @return number of answers
   */
  public int countAnswered() {
    return countAnswered++;
  }

  /**
   * count number of instances of given difficulty marker in list of questions
   *
   * @param questions list of questions
   * @param difficulty difficulty marker
   *
   * @return count
   */
  public int countDifficulty(ArrayList<Question> questions, Difficulty difficulty) {
    int count = 0;
    for (Question q : questions) {
      String d = q.getDifficulty().toString();
      if (d.equals(valueOf(difficulty))) {
        count++;
      }
    }
    return count;
  }
}
