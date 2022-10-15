package de.mint.consoleline.commands.handler;

import vlsi.utils.CompactHashMap;

/**
 * @author Niklas Griese
 * @see CompactHashMap
 */
public class CommandHandler {

  /** command mapper - init the commands map */
  private final CompactHashMap<String, Object> commands = new CompactHashMap<>();

  /**
   * @param commandName set the command name
   * @param type set the object reference to identify your custom command
   */
  public void registerCommand(final String commandName, final Object type) {
    this.commands.put(commandName, type);
  }

  /**
   * @return all registered commands as map
   */
  public CompactHashMap<String, Object> getCommands() {
    return this.commands;
  }
}
