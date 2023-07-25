package cs3500.pa01.model;

import java.util.Objects;

/**
 * question class
 */
public class Question {
  private final String question;
  private final String answer;
  private Difficulty difficulty;

  /**
   *
   * @param question
   *         question in sr file
   * @param answer
   *         answer in sr file
   * @param difficulty
   *         difficulty in sr file
   */
  public Question(String question, String answer, Difficulty difficulty) {
    this.question = question;
    this.answer = answer;
    this.difficulty = difficulty;
  }

  /**
   *  gets answer
   *
   * @return string answer
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * gets question
   *
   * @return string question
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * gets difficulty
   *
   * @return string difficulty
   */
  public Difficulty getDifficulty() {
    return this.difficulty;
  }

  /**
   * changes difficulty to given difficulty
   *
   * @param replaceTo new difficulty
   */
  public void updateDifficulty(Difficulty replaceTo) {
    this.difficulty = replaceTo;
  }

  /**
   * string representation of a question
   *
   * @return string
   */
  public String toString() {
    return "- [[" + question + ":::" + answer + ".]]" + difficulty;
  }

  /**
   * overriding equals for the purpose of testing
   *
   * @param obj given object
   * @return true if elements contain same content even if they are not the same obj in memory
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Question other)) {
      return false;
    }
    // Compare the content of the questions
    return Objects.equals(this.question, other.question)
        && Objects.equals(this.answer, other.answer)
        && this.difficulty == other.difficulty;
  }
}
