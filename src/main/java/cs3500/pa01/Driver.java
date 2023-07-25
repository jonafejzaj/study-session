package cs3500.pa01;

import cs3500.pa01.controller.StudyGuideController;
import cs3500.pa01.controller.StudySessionController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Driver of entire program
 */
public class Driver {
  /**
   * if given command line arguments study guide is created by gathering
   * * filtered content from ".md" files, otherwise study session begins
   *
   * @param args 0 -> relative or absolute path to directory
   *             1 -> ordering type
   *             2 -> relative or absolute path to output file
   * @throws IOException if IO error occurs
   *                     acts as the main driving point of entire program
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 3) {
      new StudyGuideController(args[0], args[1], args[2]).run();

      if (!Files.exists(Path.of(args[0]))) {
        throw new FileNotFoundException("path must exist");
      }
    }

    //no command line arguments run study session
    if (args.length == 0) {
      Readable input = new InputStreamReader(System.in);
      Appendable output = new PrintStream(System.out);
      new StudySessionController(input, output).run();
    }
  }
}