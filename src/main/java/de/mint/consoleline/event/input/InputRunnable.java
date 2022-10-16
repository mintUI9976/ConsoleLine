package de.mint.consoleline.event.input;

import de.mint.consoleline.commands.handler.CommandHandler;
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
import java.util.stream.Collectors;
public record InputRunnable(LineReader lineReader, LogWriter logWriter, CommandHandler commandHandler, ScheduledExecutorService scheduledExecutorService, String prompt, String commandNotAvailable, String commandArgumentNotAvailable) implements Runnable {

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
        if (command != null && !command.isEmpty()) {
          for (final String key : this.commandHandler.getCommands().keySet()) {
            if (command.equalsIgnoreCase(key)) {
              final String finalString;
              final String[] stringsToArray = Arrays.copyOfRange(strings, 1, strings.length);
              finalString =
                      Arrays.stream(stringsToArray).map(args -> args + "|").collect(Collectors.joining());
              if (finalString.length() != 0 && stringsToArray.length != 0) {
                this.scheduledExecutorService.execute(() -> {
                  try {
                    final Class<?> obj = this.commandHandler.getCommands().get(key).getClass();
                    final Method method = obj.getMethod("onCommand", String[].class);
                    method.invoke(obj.newInstance(), (Object) finalString.split("\\|"));
                  } catch (final InvocationTargetException
                                 | InstantiationException
                                 | NoSuchMethodException
                                 | IllegalAccessException exception) {
                    throw new ConsoleLineException(
                            "instance creation error - parameter or object error", exception);
                  }
                });
              } else {
                if (this.commandNotAvailable() != null){
                  System.out.println(this.commandNotAvailable());
                }
              }
            }
          }
        } else {
          if (this.commandArgumentNotAvailable() != null){
            System.out.println(this.commandArgumentNotAvailable());
          }
        }
      }
    }catch (final UserInterruptException ignored) {
      Runtime.getRuntime().exit(0);
    } catch (final EndOfFileException ignored) {
    }
  }
}
