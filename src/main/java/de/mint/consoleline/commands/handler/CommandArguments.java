package de.mint.consoleline.commands.handler;

import org.jetbrains.annotations.NotNull;

public interface CommandArguments {
  /** This function is called when the user types a command. */
  @NotNull
  String commandName();

  default void onCommand(final String[] arguments) {}
}
