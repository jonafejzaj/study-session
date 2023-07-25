package cs3500.pa01.model;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a file walker class
 */
public class FileTreeWalkerVisitor implements FileVisitor<Path> {
  private final ArrayList<Path> mdFile;
  private final ArrayList<Path> finalList = new ArrayList<>();
  private final HashMap<Path, Markdown> pathFileTimeHashMap;
  private StringBuilder outString;

  /**
   * constructor for walking file system
   */
  public FileTreeWalkerVisitor() {
    this.mdFile = new ArrayList<>();
    this.pathFileTimeHashMap = new HashMap<>();
  }

  /**
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return CONTINUE
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    String fileName = file.toString();
    if (fileName.endsWith(".md")) {
      mdFile.add(Path.of(fileName));
    }
    return CONTINUE;
  }

  /**
   * @return a list of md paths
   */
  public ArrayList<Path> getMarkDownPaths() {
    return mdFile;
  }

  /**
   * @param comp type of comparator
   * @return list of paths sorted by comparator
   * @throws IOException if IO error occurs
   */
  public ArrayList<Path> sortBy(Comparator<Map.Entry<Path,
      Markdown>> comp) throws IOException {
    for (Path filePath : mdFile) {
      BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
      // put modifed or created value in hashmap depening on ordering flag
      pathFileTimeHashMap.put(filePath, new Markdown(filePath, attr.creationTime().toMillis(),
          attr.lastModifiedTime().toMillis()));
    }
    // sort
    ArrayList<Map.Entry<Path, Markdown>> mapToList =
        new ArrayList<>(pathFileTimeHashMap.entrySet());
    mapToList.sort(comp);

    // add sorted keys of hashmap to new list
    for (Map.Entry<Path, Markdown> hm : mapToList) {
      finalList.add(hm.getKey());
    }
    return finalList;
  }

  /**
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return filevisitresult
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return CONTINUE;
  }

  /**
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   * @return filevisitresult
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    return CONTINUE;
  }

  /**
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return filevisitresult
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    this.handleException();
    return CONTINUE;
  }

  /**
   * handle exception for testing purposes
   */
  @SuppressWarnings("ResultOfMethodCallIgnored")
  private void handleException() {
    outString = new StringBuilder();
    outString.append("error: invalid directory or file").toString();
  }

  /**
   * @return error message
   */
  public String getError() {
    return this.outString.toString();
  }
}