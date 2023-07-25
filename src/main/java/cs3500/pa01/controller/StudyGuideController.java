package cs3500.pa01.controller;

import cs3500.pa01.model.CompareByDateCreated;
import cs3500.pa01.model.CompareByFileName;
import cs3500.pa01.model.CompareByLastModified;
import cs3500.pa01.model.FileTreeWalkerVisitor;
import cs3500.pa01.model.Markdown;
import cs3500.pa01.model.MdFileIn;
import cs3500.pa01.model.MdFileOut;
import cs3500.pa01.model.OrderingFlag;
import cs3500.pa01.model.SrFileIn;
import cs3500.pa01.model.SrFileOut;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 * aids driver
 */
public class StudyGuideController implements RunProgram {
  private final String path;
  private final String orderType;
  private final String output;

  /**
   *
   * @param path name
   * @param orderType for ordering based on file name, created, modified
   * @param output path to write file to
   */
  public StudyGuideController(String path, String orderType, String output) {
    this.path = path;
    this.orderType = orderType;
    this.output = output;
  }

  /**
   * Runs program
   *
   * @throws IOException if IO error occurs
   */
  public void run() throws IOException {
    // try catch invalid path
    Path inputPath;

    inputPath = Path.of(path);

    if (!inputPath.toFile().exists()) {
      throw new FileNotFoundException("path must exist");
    }

    // set order flag based on "sort by" input
    OrderingFlag flag;

    switch (orderType) {
      case "filename" -> flag = OrderingFlag.FILE_NAME;
      case "created" -> flag = OrderingFlag.CREATED;
      case "modified" ->  flag = OrderingFlag.MODIFIED;
      default -> throw new IllegalArgumentException("Not a valid input");
    }

    try {
      // Begin walking along directory
      FileTreeWalkerVisitor ftwv = new FileTreeWalkerVisitor();
      Files.walkFileTree(inputPath, ftwv);

      // sort based on ordering flag
      ArrayList<Path> sorted;

      Comparator<Map.Entry<Path, Markdown>> compByDate = new CompareByDateCreated();
      Comparator<Map.Entry<Path, Markdown>> compByModified = new CompareByLastModified();

      switch (flag) {
        case FILE_NAME -> sorted = ftwv.sortBy(new CompareByFileName());
        case CREATED -> sorted = ftwv.sortBy(compByDate);

        case MODIFIED -> sorted = ftwv.sortBy(compByModified);
        default -> throw new IllegalArgumentException("not valid input");
      }

      String outputPath = output;

      MdFileIn fi = new MdFileIn();
      MdFileOut fo = new MdFileOut();
      SrFileIn srI = new SrFileIn();
      SrFileOut srO = new SrFileOut();

      // read from sorted list of paths
      String contents = fi.read(sorted);
      // write to file
      fo.write(new File(outputPath), contents);

      String srContent = srI.read(sorted);
      srO.write(new File(outputPath), srContent);

    } catch (IOException e) {
      throw new IOException();
    }
  }
}