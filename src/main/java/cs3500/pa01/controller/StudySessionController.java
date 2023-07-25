package cs3500.pa01.controller;

import cs3500.pa01.model.Difficulty;
import cs3500.pa01.model.Question;
import cs3500.pa01.model.QuestionBank;
import cs3500.pa01.model.SrFileIn;
import cs3500.pa01.model.SrFileOut;
import cs3500.pa01.model.StudySessionStats;
import cs3500.pa01.view.StudySessionView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * runs a study session if no command line arguments given
 */
public class StudySessionController implements RunProgram {
  private final Readable input;
  private final Appendable output;
  private final SrFileIn srI;
  private final SrFileOut srO;
  private final QuestionBank qb;
  private final StudySessionView view;
  private final StudySessionStats stats;
  private final MenuOptions escapeSeq;
  private final MenuOptions easy;
  private final MenuOptions hard;
  private final MenuOptions answered;
  private final Random rand;

  /**
   * random seed for testing
   *
   * @param input system in
   * @param output system out
   * @param rand random number
   */
  public StudySessionController(Readable input, Appendable output, Random rand) {
    this.rand = rand;
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
    srI = new SrFileIn();
    srO = new SrFileOut();
    qb = new QuestionBank();
    view = new StudySessionView(this.input, this.output);
    stats = new StudySessionStats();
    escapeSeq = MenuOptions.EXIT;
    easy = MenuOptions.EASY;
    hard = MenuOptions.HARD;
    answered = MenuOptions.ANSWER;
  }

  /**
   * truly random
   *
   * @param input system in
   * @param output system out
   */
  public StudySessionController(Readable input, Appendable output) {
    this(input, output, new Random());
  }

  /**
   * acts as controller for driver
   *
   * @throws IOException if io error occurs
   */
  @Override
  public void run() throws IOException {
    final String menuOptions =
        "Type 1 to mark as easy. "
            + "Type 2 to mark as hard. "
            + "Type 3 to reveal answer. "
            + "Type 4 to exit.";

    view.showPrompt("Welcome to your study session!");
    String file = view.getVal("Please enter a full path to a \".sr\" file.");
    Path filePath = Path.of(file);

    if (!filePath.toFile().exists()) {
      throw new FileNotFoundException("Invalid path, please try again.");
    }

    String numQ = view.getVal("How many questions would you like to practice? "
        + "Please enter a numerical value.");

    try {
      Integer.parseInt(numQ);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input. Please enter a numerical value.");
    }

    // read sr file
    ArrayList<Path> files = new ArrayList<>();
    files.add(filePath);
    String qa = srI.readSr(files);
    // add question from sr file to list of questions with question, answer and difficulty
    ArrayList<Question> questions = qb.questionAnswerDifficulty(qa);
    // shuffle list randomly
    ArrayList<Question> randomQ = qb.randQuestion(questions, this.rand);

    // send user questions and prompt their available options
    int num = Integer.parseInt(numQ);
    for (int i = 0; i < num && i < randomQ.size(); i++) {
      Question currentQuestion = randomQ.get(i);
      view.showPrompt(currentQuestion.getQuestion());
      String userInput = view.getVal(menuOptions);

      if (userInput.equals(easy.toString())) {
        stats.countEasy();
        currentQuestion.updateDifficulty(Difficulty.EASY);
      }

      if (userInput.equals(hard.toString())) {
        stats.countHard();
        currentQuestion.updateDifficulty(Difficulty.HARD);
      }

      if (userInput.equals(answered.toString())) {
        currentQuestion.updateDifficulty(Difficulty.HARD);
        System.out.println("Answer is: " + currentQuestion.getAnswer());
        stats.countAnswered();
      }

      if (userInput.equals(escapeSeq.toString())) {
        System.exit(0);
      }
    }

    // string builder for questions user answered
    StringBuilder sb = new StringBuilder();
    for (Question q : randomQ) {
      sb.append(q.toString()).append("\n");
    }

    // rewrite file updating user difficulty
    File studySessionFile = filePath.toFile();
    String update = sb.toString();
    srO.write(studySessionFile, update);

    // print stats
    int easyAmt = stats.countEasy();
    view.showPrompt("Total number of questions marked as easy in this session: " + easyAmt);
    int hardAmt = stats.countHard();
    view.showPrompt("Total number of questions marked as hard in this session: " + hardAmt);
    int answeredAmt = stats.countAnswered();
    view.showPrompt("Total number of questions marked as answered in this session: "
        + answeredAmt);

    int countHard = stats.countDifficulty(randomQ, Difficulty.HARD);
    view.showPrompt("Total HARD in question bank is: " + countHard);
    int countEasy = stats.countDifficulty(randomQ, Difficulty.EASY);
    view.showPrompt("Total EASY in question bank is: " + countEasy);
  }
}