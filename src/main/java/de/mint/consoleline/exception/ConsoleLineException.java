package de.mint.consoleline.exception;

public class ConsoleLineException extends RuntimeException {

  public ConsoleLineException() {
    super();
  }

  public ConsoleLineException(final String message) {
    super(message);
  }

  public ConsoleLineException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ConsoleLineException(final Throwable cause) {
    super(cause);
  }

  protected ConsoleLineException(
      final String message,
      final Throwable cause,
      final boolean enableSuppression,
      final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
