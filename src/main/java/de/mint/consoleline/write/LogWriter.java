package de.mint.consoleline.write;

import de.mint.consoleline.exception.ConsoleLineException;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;

public class LogWriter extends FileWriter {

  public LogWriter(@NotNull final String fileName) throws IOException {
    super(fileName);
  }

  /**
   * This function takes a string and writes it to the file
   *
   * @param message The message to be written to the file.
   */
  public void insert(@NotNull final String message) {
    try {
      this.write(message + "\n");
      this.flush();
    } catch (final IOException exception) {
      throw new ConsoleLineException("File can not be edit!", exception);
    }
  }
}
