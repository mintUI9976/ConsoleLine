package de.mint.consoleline.event.input;

import de.mint.consoleline.Format.CustomFormat;
import de.mint.consoleline.command.CommandHandler;
import de.mint.consoleline.exception.ConsoleLineException;
import de.mint.consoleline.service.JlineUtils;
import de.mint.consoleline.write.LogWriter;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.UserInterruptException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;

public record InputRunnable(
    LineReader lineReader,
    LogWriter logWriter,
    CommandHandler commandHandler,
    ScheduledExecutorService scheduledExecutorService,
    String prompt,
    String commandNotAvailable,
    String commandArgumentNotAvailable,
    CustomFormat commandNotAvailableFormat,
    CustomFormat commandArgumentNotAvailableFormat)
    implements Runnable {

  @Override
  public void run() {
    try {
      final String message = this.lineReader.readLine(this.prompt);
      if (!message.isEmpty()) {
        if (this.logWriter != null) {
          this.logWriter.insert(JlineUtils.cleanAnsiString(this.prompt + message));
        }
        final String[] strings = message.split(" ");
        final String command = strings[0];
        if (!command.isEmpty()) {
          if (this.commandHandler.getCommands().containsKey(command)) {
            final String[] stringsToArray = Arrays.copyOfRange(strings, 1, strings.length);
            if (stringsToArray.length > 0) {
              try {
                final Class<?> obj = this.commandHandler.getCommands().get(command).getClass();
                final Method method = obj.getMethod("onCommand", String[].class);
                method.invoke(obj.newInstance(), (Object) stringsToArray);
              } catch (final InvocationTargetException
                  | InstantiationException
                  | NoSuchMethodException
                  | IllegalAccessException exception) {
                throw new ConsoleLineException(
                    "instance creation error - parameter or object error", exception);
              }
            } else {

              if (this.commandArgumentNotAvailableFormat() != null) {
                System.out.println(commandArgumentNotAvailableFormat.format());
              } else {
                if (this.commandArgumentNotAvailable() != null) {
                  System.out.println(this.commandArgumentNotAvailable());
                }
              }
            }
          } else {

            if (this.commandNotAvailableFormat() != null) {
                System.out.println(commandNotAvailableFormat.format());
            } else {
              if (this.commandNotAvailable() != null) {
                System.out.println(this.commandNotAvailable());
              }
            }
          }
        }
      }
    } catch (final UserInterruptException ignored) {
      Runtime.getRuntime().exit(0);
    } catch (final EndOfFileException ignored) {
    }
  }
}
