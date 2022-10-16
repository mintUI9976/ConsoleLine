package de.mint.consoleline.service;

import org.jetbrains.annotations.NotNull;
import org.jline.reader.Completer;
import org.jline.reader.Highlighter;
import org.jline.reader.History;

import java.nio.charset.Charset;

public class JlineBuilder {

  private static final JlineBuilder jlineBuilder = new JlineBuilder();
  private final JlineExecutor jlineConfiguration = new JlineExecutor();

  public static JlineBuilder getJlineBuilder() {
    return JlineBuilder.jlineBuilder;
  }

  public JlineBuilder setName(@NotNull final String name) {
    this.jlineConfiguration.setName(name);
    return this;
  }

  public JlineBuilder setHistory(@NotNull final History history) {
    this.jlineConfiguration.setHistory(history);
    return this;
  }

  public JlineBuilder setCharset(@NotNull final Charset charset) {
    this.jlineConfiguration.setCharset(charset);
    return this;
  }

  public JlineBuilder setHighlighter(@NotNull final Highlighter highlighter) {
    this.jlineConfiguration.setHighlighter(highlighter);
    return this;
  }

  public JlineBuilder setCompleter(@NotNull final Completer completer) {
    this.jlineConfiguration.setCompleter(completer);
    return this;
  }

  public JlineBuilder setDumb(final boolean dumb) {
    this.jlineConfiguration.setDumb(dumb);
    return this;
  }

  public JlineBuilder setSystem(final boolean system) {
    this.jlineConfiguration.setSystem(system);
    return this;
  }

  public JlineBuilder setAnsi(final boolean ansi) {
    this.jlineConfiguration.setAnsi(ansi);
    return this;
  }

  public JlineBuilder setJna(final boolean jna) {
    this.jlineConfiguration.setJna(jna);
    return this;
  }

  public JlineBuilder setExec(final boolean exec) {
    this.jlineConfiguration.setExec(exec);
    return this;
  }

  public JlineBuilder setLog(final boolean log) {
    this.jlineConfiguration.setLog(log);
    return this;
  }

  public JlineBuilder setLogPath(final String logPath) {
    this.jlineConfiguration.setLogPath(logPath);
    return this;
  }

  public JlineBuilder setLogFilename(final String logPath) {
    this.jlineConfiguration.setLogPath(logPath);
    return this;
  }

  public JlineBuilder setWithCommandSystem(final boolean withCommandSystem) {
    this.jlineConfiguration.setWithCommandSystem(withCommandSystem);
    return this;
  }

  public JlineBuilder setCommandArgumentNotAvailable(final String commandArgumentNotAvailable) {
    this.jlineConfiguration.setCommandArgumentNotAvailable(commandArgumentNotAvailable);
    return this;
  }

  public JlineBuilder setCommandNotAvailable(final String commandNotAvailable) {
    this.jlineConfiguration.setCommandNotAvailable(commandNotAvailable);
    return this;
  }

  public JlineBuilder setPrompt(final String prompt) {
    this.jlineConfiguration.setPrompt(prompt);
    return this;
  }

  public JlineBuilder setThreadSize(final int setThreadSize) {
    this.jlineConfiguration.setThreadSize(setThreadSize);
    return this;
  }

  public JlineBuilder setPeriod(final long period) {
    this.jlineConfiguration.setPeriod(period);
    return this;
  }

  public JlineExecutor build() {
    return this.jlineConfiguration;
  }
}
