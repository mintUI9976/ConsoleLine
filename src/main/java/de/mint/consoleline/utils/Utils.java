package de.mint.consoleline.utils;

import de.mint.consoleline.exception.ConsoleLineException;

import java.io.File;

public class Utils {

  /**
   * It creates a folder if it doesn't exist
   *
   * @param folderName The name of the folder to be created.
   */
  public static void createFolder(final String folderName) {
    final File directory = new File(folderName);
    if (!directory.exists()) {
      try {
        directory.mkdir();
      } catch (final SecurityException exception) {
        throw new ConsoleLineException("Folder can not be created!", exception);
      }
    }
  }
}
