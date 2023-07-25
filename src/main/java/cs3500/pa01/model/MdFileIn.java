package cs3500.pa01.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a class that allows for transferring information from files to another
 */
public class MdFileIn implements Reader {
  /**
   * reads all files from sorted list of paths and combines result into one formatted string
   *
   * @param pathsList list of paths denoting ".md" files in directory
   * @return string of all file contents in path list combined
   */
  public String read(ArrayList<Path> pathsList) throws FileNotFoundException {
    // efficient concatenation
    boolean textLine = false;
    StringBuilder studyGuideContent = new StringBuilder();
    try {
      // perform process for each path
      for (Path path : pathsList) {
        // scanner reads file (obtains file from path)
        Scanner sc = new Scanner(new FileInputStream(path.toFile()));
        // checking all unread lines in file
        while (sc.hasNextLine()) {
          String bracket = ".*\\[\\[.*\\]\\].*";
          String line = sc.nextLine();
          // only obtain information in lines that are headings or contain brackets
          if (line.startsWith("#") || line.matches(bracket) && !line.contains(":::")) {
            // replace bracketed contents of line with "-"
            // remove end brackets by replacing w/ empty string
            line = line.replaceAll(".*\\[\\[", "- ")
                .replaceAll("\\]\\].*", "");
            // add new line before each heading
            if (line.startsWith("#") && textLine) {
              studyGuideContent.append("\n");
            } else {
              textLine = true;
            }
            studyGuideContent.append(line).append("\n");
          }
        }
        sc.close();
      }
      // return string builder contents
      return studyGuideContent.toString();
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
  }
}