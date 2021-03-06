package xebia.corentin.mower.command;

import java.util.HashMap;
import java.util.Map;


/**
 * store all available commands for the mower.
 * 
 * @author Corentin Jechoux
 * 
 */
public class CommandManager {

	/**
	 * Map to store all allowed command on the mower.
	 */
	private Map<Character, Command> commands;

	/**
	 * Constructor.
	 */
	public CommandManager() {
		commands = new HashMap<Character, Command>();

		commands.put(MowerCommand.LEFT, new TurnLeftCommand());
		commands.put(MowerCommand.RIGHT, new TurnRightCommand());
		commands.put(MowerCommand.FORWARD, new ForwardCommand());

	}

	/**
	 * return a registered command
	 * 
	 * @param key
	 *            key to identify the command
	 * @return an instance of a mower command
	 */
	public Command getCommand(final char key) {
		return commands.get(key);
	}

}
