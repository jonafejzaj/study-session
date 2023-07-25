package cs3500.pa01.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * class for handling questions inside sr file
 */
public class QuestionBank {
  private final ArrayList<Question> questions;

  /**
   * question bank constructor
   */
  public QuestionBank() {
    questions = new ArrayList<>();
  }

  /**
   * takes in content of sr file and adds each element to an arrayList of questions
   * i.e. split up by question, answer, difficulty
   *
   * @param content sr file contents
   *
   * @return list of Question
   */
  public ArrayList<Question> questionAnswerDifficulty(String content) {
    String[] contents = content.split("\n");
    for (String s : contents) {
      String ques = s.substring(4, s.indexOf(":"));
      String ans = s.substring((s.lastIndexOf(":") + 1), s.lastIndexOf("."));
      String hardOrEasy = s.substring(s.lastIndexOf("]") + 1);
      Difficulty difficulty = Difficulty.valueOf(hardOrEasy);

      questions.add(new Question(ques, ans, difficulty));
    }
    return questions;
  }

  /**
   * shuffle list of questions randomly
   *
   * @param questions list of questions
   * @param rand random number
   * @return random list of questions
   */
  public ArrayList<Question> randQuestion(ArrayList<Question> questions, Random rand) {
    Collections.shuffle(questions, rand);
    return questions;
  }
}