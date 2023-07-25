package cs3500.pa01.view;

import java.io.IOException;
import java.util.Scanner;

/**
 * viewable system output
 */
public class StudySessionView {
  private final Scanner input;
  private final Appendable output;

  /**
   * constructor initializes system in scanner allowing user to type input
   *
   * @param reader system in
   * @param append system out
   */
  public StudySessionView(Readable reader, Appendable append) {
    output = append;
    input = new Scanner(reader);
  }

  /**
   * prints given welcome message
   *
   * @param prompt to be printed
   * @throws IOException if io error occurs
   */
  public void showPrompt(String prompt) throws IOException {
    output.append(prompt).append("\n");
  }

  /**
   * prints question and returns user input
   *
   * @param prompt question asked user
   * @return user input
   */
  public String getVal(String prompt) {
    System.out.println(prompt);
    return input.next();
  }
}