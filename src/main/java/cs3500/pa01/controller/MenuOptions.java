package cs3500.pa01.controller;

/**
 * enum for valid options a user can enter through system.in
 */
public enum MenuOptions {
  /**
   * easy if user enters 1
   */
  EASY(1),
  /**
   * hard if user enters 2
   */
  HARD(2),
  /**
   * answer if user enters 3
   */
  ANSWER(3),
  /**
   * exit if user enters 4
   */
  EXIT(4);

  private final int userInput;

  MenuOptions(int userInput) {
    this.userInput = userInput;
  }

  public String toString() {
    return Integer.toString(this.userInput);
  }
}
