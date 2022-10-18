package de.mint.consoleline.commands.handler;

import org.jetbrains.annotations.NotNull;

public interface CommandArguments {
  /**
   * Returns the name of the command.
   *
   * @return The name of the command.
   */
  @NotNull
  String commandName();

  /**
   * "This function is called when the user types a command in the console."
   *
   * <p>The first thing you'll notice is that the function is marked as `default`. This means that
   * you don't have to implement it if you don't want to
   *
   * @param arguments The arguments passed to the command.
   */
  default void onCommand(final String[] arguments) {}
}
