package xebia.corentin.mower;

import xebia.corentin.mower.command.Command;
import xebia.corentin.mower.command.CommandManager;
import xebia.corentin.mower.model.Grass;
import xebia.corentin.mower.model.Mower;
import xebia.corentin.mower.model.MowerPosition;

/**
 * This is the mower controller.
 * 
 * @author Corentin Jechoux
 * 
 */
public class MowerController {
	/**
	 * error message: passed as a parameter of an IllegalArgumentException when
	 * a command if not found
	 */
	private static final String COMMAND_NOT_FOUND = "Command not found with the key: ";
	/**
	 * Mower to control
	 */
	private Mower mower;
	/**
	 * command manager (command store)
	 */
	private CommandManager commandManager;

	/**
	 * Constructor
	 * 
	 * @param mower
	 *            mower to bind to this controller.
	 */
	public MowerController(final Mower mower) {
		this.mower = mower;
		this.commandManager = new CommandManager();
	}

	/**
	 * Control this mower on the grass and return the last mower position.
	 * <P>
	 * Each character of the command sequence lead to a change of a command
	 * sequence.
	 * 
	 * @param commandSequence
	 * @param grass
	 * @return the last mower position.
	 */
	public MowerPosition control(final String commandSequence, final Grass grass) {
		if (commandSequence != null) {

			for (int i = 0; i < commandSequence.length(); i++) {
				final char commandKey = commandSequence.charAt(i);
				final Command command = this.commandManager
						.getCommand(commandKey);
				if (command != null) {
					command.update(mower, grass);
				} else {
					throw new IllegalArgumentException(COMMAND_NOT_FOUND
							+ commandKey);
				}
			}
		}

		return mower.getCurrentPosition();
	}

}
