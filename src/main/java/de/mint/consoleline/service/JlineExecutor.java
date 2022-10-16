package de.mint.consoleline.service;

import de.mint.consoleline.commands.handler.CommandArguments;
import de.mint.consoleline.commands.handler.CommandHandler;
import de.mint.consoleline.event.error.ErrorPrintStream;
import de.mint.consoleline.event.input.InputRunnable;
import de.mint.consoleline.event.output.OutputPrintStream;
import de.mint.consoleline.exception.ConsoleLineException;
import de.mint.consoleline.utils.Utils;
import de.mint.consoleline.write.LogWriter;
import org.jline.builtins.Completers;
import org.jline.reader.*;
import org.jline.reader.impl.DefaultHighlighter;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JlineExecutor {

  private ScheduledExecutorService scheduledExecutorService;
  private Terminal terminal;
  private LineReader lineReader;
  private CommandHandler commandHandler;
  private LogWriter logWriter = null;
  private String name = UUID.randomUUID().toString();
  private History history = new DefaultHistory();
  private InputRunnable inputRunnable;
  private Charset charset = StandardCharsets.UTF_8;
  private Highlighter highlighter = new DefaultHighlighter();
  private Completer completer = new Completers.TreeCompleter();
  private String logPath = "consoleLine/";
  private String logFilename = "consoleLine.log";

  private String commandArgumentNotAvailable;

  private String commandNotAvailable;

  private String prompt;

  private boolean withCommandSystem = true;
  private boolean log = false;
  private boolean dumb = true;
  private boolean system = false;
  private boolean ansi = true;
  private boolean jna = false;
  private boolean exec = false;

  private int threadSize = 3;
  private long period = 1L;

  public Terminal getTerminal() {
    return this.terminal;
  }

  public LineReader getLineReader() {
    return this.lineReader;
  }

  public CommandHandler getCommandHandler() {
    return this.commandHandler;
  }

  public LogWriter getLogWriter() {
    return this.logWriter;
  }

  public String getName() {
    return this.name;
  }

  /**
   * This function sets the name of the object to the value of the name parameter.
   *
   * @param name The name of the parameter.
   */
  void setName(final String name) {
    this.name = name;
  }

  public History getHistory() {
    return this.history;
  }

  /**
   * This function sets the history of the current object to the history passed in as a parameter.
   *
   * @param history The history object that is passed to the component.
   */
  void setHistory(final History history) {
    this.history = history;
  }

  public Charset getCharset() {
    return this.charset;
  }

  /**
   * > Sets the charset for this request
   *
   * @param charset The character set to use when converting bytes to characters.
   */
  void setCharset(final Charset charset) {
    this.charset = charset;
  }

  public Highlighter getHighlighter() {
    return this.highlighter;
  }

  /**
   * > Sets the highlighter used by this renderer to change the color of the text
   *
   * @param highlighter The highlighter to use for highlighting the text.
   */
  void setHighlighter(final Highlighter highlighter) {
    this.highlighter = highlighter;
  }

  public Completer getCompleter() {
    return this.completer;
  }

  /**
   * This function sets the completer to the given completer.
   *
   * @param completer The completer to use for this command.
   */
  void setCompleter(final Completer completer) {
    this.completer = completer;
  }

  public boolean isDumb() {
    return this.dumb;
  }

  /**
   * This function sets the value of the dumb variable to the value of the dumb parameter.
   *
   * @param dumb If true, the bot will not respond to any messages.
   */
  void setDumb(final boolean dumb) {
    this.dumb = dumb;
  }

  public boolean isSystem() {
    return this.system;
  }

  /**
   * Sets the system property to the given value.
   *
   * @param system If true, the user is a system user.
   */
  void setSystem(final boolean system) {
    this.system = system;
  }

  public boolean isAnsi() {
    return this.ansi;
  }

  /**
   * > Sets the ANSI flag
   *
   * @param ansi If true, ANSI escape codes will be used to colorize the output.
   */
  void setAnsi(final boolean ansi) {
    this.ansi = ansi;
  }

  public boolean isJna() {
    return this.jna;
  }

  /**
   * This function sets the value of the jna variable to the value of the jna parameter.
   *
   * @param jna If true, the JNA library will be used to access the native library. If false, the
   *     JNI library will be used.
   */
  void setJna(final boolean jna) {
    this.jna = jna;
  }

  public boolean isExec() {
    return this.exec;
  }

  /**
   * This function sets the value of the variable 'exec' to the value of the parameter 'exec'.
   *
   * @param exec If true, the command will be executed.
   */
  void setExec(final boolean exec) {
    this.exec = exec;
  }

  public boolean isLog() {
    return this.log;
  }

  /**
   * Sets the log flag to the given value.
   *
   * @param log If true, the logger will log the request and response to the console.
   */
  void setLog(final boolean log) {
    this.log = log;
  }

  public boolean isWithCommandSystem() {
    return this.withCommandSystem;
  }

  /**
   * Sets the value of the withCommandSystem variable to the value of the withCommandSystem
   * parameter.
   *
   * @param withCommandSystem If true, the command system will be enabled.
   */
  public void setWithCommandSystem(final boolean withCommandSystem) {
    this.withCommandSystem = withCommandSystem;
  }

  public String getLogPath() {
    return this.logPath;
  }

  /**
   * Sets the logPath variable to the value of the logPath parameter.
   *
   * @param logPath The path to the log file.
   */
  void setLogPath(final String logPath) {
    this.logPath = logPath;
  }

  public String getLogFilename() {
    return this.logFilename;
  }

  /**
   * Sets the logFilename variable to the value of the logFilename parameter.
   *
   * @param logFilename The name of the log file.
   */
  void setLogFilename(final String logFilename) {
    this.logFilename = logFilename;
  }

  public String getCommandArgumentNotAvailable() {
    return this.commandArgumentNotAvailable;
  }

  /**
   * Sets the command argument not available message.
   *
   * @param commandArgumentNotAvailable This is the message that will be displayed when the user
   *     enters a command that is not available.
   */
  void setCommandArgumentNotAvailable(final String commandArgumentNotAvailable) {
    this.commandArgumentNotAvailable = commandArgumentNotAvailable;
  }

  public String getCommandNotAvailable() {
    return this.commandNotAvailable;
  }

  /**
   * Sets the commandNotAvailable variable to the value of the commandNotAvailable parameter.
   *
   * @param commandNotAvailable The message to display when the command is not available.
   */
  void setCommandNotAvailable(final String commandNotAvailable) {
    this.commandNotAvailable = commandNotAvailable;
  }

  public String getPrompt() {
    return this.prompt;
  }

  /**
   * Sets the prompt to the given string.
   *
   * @param prompt The prompt to display to the user.
   */
  void setPrompt(final String prompt) {
    this.prompt = prompt;
  }

  public int getThreadSize() {
    return this.threadSize;
  }

  /**
   * Sets the thread size.
   *
   * @param threadSize The number of threads to use for the test.
   */
  void setThreadSize(final int threadSize) {
    this.threadSize = threadSize;
  }

  public long getPeriod() {
    return this.period;
  }

  /**
   * This function sets the period of the timer to the value of the parameter.
   *
   * @param period The period of the timer in milliseconds.
   */
  void setPeriod(final long period) {
    this.period = period;
  }

  /**
   * It creates a terminal, a line reader, a log, a command handler, output streams, and an input
   * stream
   */
  public void buildTerminal() {
    try {
      if (this.system) {
        this.terminal =
            TerminalBuilder.builder()
                .jna(this.jna)
                .encoding(this.charset)
                .jansi(this.ansi)
                .name(this.name)
                .system(true)
                .build();
      } else {
        this.terminal =
            TerminalBuilder.builder()
                .jna(this.jna)
                .encoding(this.charset)
                .jansi(this.ansi)
                .name(this.name)
                .dumb(this.dumb)
                .build();
      }

      this.lineReader =
          LineReaderBuilder.builder()
              .terminal(this.terminal)
              .highlighter(this.highlighter)
              .history(this.history)
              .completer(this.completer)
              .build();

      this.createLog();
      this.createCommandHandler();
      this.createInputStream();
      this.createOutputStream();
      this.createErrorStream();
    } catch (final IOException exception) {
      throw new ConsoleLineException(
          "The terminal with name: " + this.name + " can not be created!", exception);
    }
  }

  /** Create an error stream that will print to the log. */
  private void createErrorStream() {
    final ErrorPrintStream errorPrintStream = new ErrorPrintStream(this.charset);
    errorPrintStream.stream(this.log, this.lineReader, this.logWriter);
  }

  /** Create an output stream that will print the output of the line reader to the log. */
  private void createOutputStream() {
    final OutputPrintStream outputPrintStream = new OutputPrintStream(this.charset);
    outputPrintStream.stream(this.log, this.lineReader, this.logWriter);
  }

  /** > This function creates a log file if the user has specified that they want one */
  private void createLog() {
    if (this.log) {
      Utils.createFolder(this.logPath);
      try {
        this.logWriter = new LogWriter(this.logPath + this.logFilename);
      } catch (final IOException exception) {
        throw new ConsoleLineException("The file can not be created!", exception);
      }
    }
  }

  /**
   * > This function creates a new CommandHandler object and assigns it to the commandHandler
   * variable
   */
  private void createCommandHandler() {
    this.commandHandler = new CommandHandler();

    final Reflections reflections = new Reflections("de.mint.consoleline");
    final Set<Class<? extends CommandArguments>> classes =
        reflections.getSubTypesOf(CommandArguments.class);
    for (final Class<? extends CommandArguments> commandArgumentReferences : classes) {
      try {
        final Method method = commandArgumentReferences.getMethod("commandName");
        final Object value = method.invoke(commandArgumentReferences.newInstance());
        System.out.println(value);
        this.commandHandler.registerCommand(
            String.valueOf(value), commandArgumentReferences.newInstance());
      } catch (final NoSuchMethodException
          | InvocationTargetException
          | IllegalAccessException
          | InstantiationException e) {
        throw new RuntimeException(e);
      }
    }
    System.out.println(this.commandHandler.getCommands().values());
  }

  /**
   * If the command system is enabled, create a new input runnable and create a scheduled executor
   * service
   */
  private void createInputStream() {
    if (this.withCommandSystem) {
      this.inputRunnable =
          new InputRunnable(
              this.lineReader,
              this.logWriter,
              this.commandHandler,
              this.scheduledExecutorService,
              this.prompt,
              this.commandNotAvailable,
              this.commandArgumentNotAvailable);
      this.createInputRunnable();
    }
  }

  /** Create a new input runnable and schedule it to run every millisecond. */
  private void createInputRunnable() {
    this.scheduledExecutorService = Executors.newScheduledThreadPool(this.threadSize);
    this.scheduledExecutorService.scheduleAtFixedRate(
        this.inputRunnable, 0, this.period, TimeUnit.MILLISECONDS);
  }

  /**
   * "If the scheduled executor service is not shutdown, then shutdown the scheduled executor
   * service and return true."
   *
   * <p>The scheduled executor service is a Java class that allows you to schedule tasks to run at a
   * later time. In this case, the scheduled executor service is used to schedule the task of
   * reading the input stream
   *
   * @return A boolean value.
   */
  public boolean pauseInputStream() {
    if (!this.scheduledExecutorService.isShutdown()) {
      this.scheduledExecutorService.shutdown();
      return true;
    } else {
      throw new ConsoleLineException("The input stream is already paused!");
    }
  }

  /**
   * If the scheduled executor service is shutdown, create a new one and return true. Otherwise,
   * throw a ConsoleLineException
   *
   * @return A boolean value.
   */
  public boolean playInputStream() {
    if (this.scheduledExecutorService.isShutdown()) {
      this.createInputRunnable();
      return true;
    } else {
      throw new ConsoleLineException("The input stream is already running!");
    }
  }
}
