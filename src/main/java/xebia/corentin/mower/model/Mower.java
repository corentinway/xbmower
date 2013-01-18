package xebia.corentin.mower.model;

import xebia.corentin.mower.command.Command;
import xebia.corentin.mower.command.CommandManager;

/**
 * a mower
 * 
 * @author Corentin Jechoux
 * 
 */
public class Mower {

	/**
	 * Current position of the mower
	 */
	private MowerPosition currentPosition;

	/**
	 * manager for all commands
	 */
	private CommandManager commandManager;

	/**
	 * constructor
	 * 
	 * @param
	 */
	public Mower() {
		commandManager = new CommandManager();
	}

	/**
	 * Get the current position of the mower
	 * 
	 * @return
	 */
	public MowerPosition getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * Set the current position of the mower
	 * 
	 * @param currentPosition
	 *            mower position
	 */
	public void setCurrentPosition(final MowerPosition currentPosition) {
		this.currentPosition = currentPosition;
	}

	/**
	 * control this mower on the grass.
	 * <P>
	 * Each character of the command sequence lead to a change of a command
	 * sequence
	 * 
	 * @param commandSequence
	 * @param grass
	 * @return
	 */
	public MowerPosition control(final String commandSequence, final Grass grass) {
		if (commandSequence != null) {

			for (int i = 0; i < commandSequence.length(); i++) {
				final char commandKey = commandSequence.charAt(i);
				final Command command = commandManager.getCommand(commandKey);
				if (command != null) {
					command.update(this, grass);
				} else {
					throw new IllegalArgumentException(
							"Command not found with the key: " + commandKey);
				}
			}
		}

		return currentPosition;
	}

}
