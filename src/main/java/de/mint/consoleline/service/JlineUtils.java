package de.mint.consoleline.service;

import org.jetbrains.annotations.NotNull;
import org.jline.utils.AttributedStringBuilder;

public class JlineUtils {

  /**
   * It takes a string with ANSI escape codes and returns a string without ANSI escape codes
   *
   * @param message The message to be cleaned.
   * @return A string with ANSI escape codes removed.
   */
  public static String cleanAnsiString(@NotNull final String message) {
    final AttributedStringBuilder attributedStringBuilder = new AttributedStringBuilder();
    return attributedStringBuilder.ansiAppend(message).toString();
  }
}
