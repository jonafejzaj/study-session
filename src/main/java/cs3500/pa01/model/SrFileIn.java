package cs3500.pa01.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * reads and writes sr content i.e. QA blocks in files
 */
public class SrFileIn implements Reader {

  /**
   * Gather all QA contents of single file into one string
   *
   * @param pathList content inside file
   * @return string QA contents
   */
  public String read(ArrayList<Path> pathList) throws FileNotFoundException {
    StringBuilder qaContent = new StringBuilder();

    try {
      for (Path path : pathList) {
        Scanner sc = new Scanner(new FileInputStream(path.toFile()));
        while (sc.hasNextLine()) {
          // pattern to look for
          String patternString = "\\[\\[(.*?):::(.*?)";
          String line = sc.nextLine();

          // make sure question follows [[ ::: ]] format and does not contain a heading
          if (line.matches(patternString) || !line.startsWith("#")
              && line.contains("[[") && line.contains("]]") && line.contains(":::")) {
            // append hard to initialize difficulty
            qaContent.append(line).append(Difficulty.HARD).append("\n");
          }
        }
        sc.close(); // Close the Scanner after processing the file
      }

      return qaContent.toString();

    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
  }

  /**
   * read sr files and compile contents into one sting
   *
   * @param pathList list of paths to read string from
   * @return string contents
   * @throws FileNotFoundException if file does not exist
   */
  public String readSr(ArrayList<Path> pathList) throws FileNotFoundException {
    StringBuilder qaContent = new StringBuilder();

    try {
      for (Path path : pathList) {
        Scanner sc = new Scanner(new FileInputStream(path.toFile()));
        while (sc.hasNextLine()) {
          String patternString = "\\[\\[(.*?):::(.*?)";
          String line = sc.nextLine();

          if (line.matches(patternString) || !line.startsWith("#")
              && line.contains("[[") && line.contains("]]") && line.contains(":::")) {
            // don't difficulty append since difficulty is already added
            qaContent.append(line).append("\n");
          }
        }
        sc.close(); // Close the Scanner after processing the file
      }

      return qaContent.toString();

    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
  }
}