package de.mint.consoleline.commands;

import de.mint.consoleline.commands.handler.CommandArguments;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class TestCommand implements CommandArguments {

  public TestCommand() {}

  @Override
  public @NotNull String commandName() {
    return "test";
  }

  @Override
  public void onCommand(final String[] arguments) {
    switch (arguments[0]) {
      case "print":
        final String output = arguments[1];
        final String[] message = Arrays.copyOfRange(arguments, 1, arguments.length);
        System.out.println("TestCommand: " + String.join(" ", message));
    }
  }
}
