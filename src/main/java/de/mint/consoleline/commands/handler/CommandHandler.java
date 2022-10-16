package de.mint.consoleline.commands.handler;

import vlsi.utils.CompactHashMap;

import java.util.Map;

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
   * It takes a map of strings to object, and adds all the objects to the commands map
   *
   * @param map The map of commands to register.
   */
  public void registerCommands(final Map<? extends String, ?> map) {
    this.commands.putAll(map);
  }

  /**
   * @return all registered commands as map
   */
  public CompactHashMap<String, Object> getCommands() {
    return this.commands;
  }
}
