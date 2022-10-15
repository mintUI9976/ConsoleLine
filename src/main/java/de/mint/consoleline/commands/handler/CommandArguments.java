package de.mint.consoleline.commands.handler;

import java.util.List;

public abstract class CommandArguments {

  /** This function is called when the user types a command. */
  public void onCommand(final List<String> arguments) {}
}
