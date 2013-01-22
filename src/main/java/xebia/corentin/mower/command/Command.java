package xebia.corentin.mower.command;

import xebia.corentin.mower.model.Grass;
import xebia.corentin.mower.model.Mower;

/**
 * A command to update the mower.
 * 
 * @author Corentin Jechoux
 * 
 */
public interface Command {

	/**
	 * Update the mower state.
	 * 
	 * @param mower
	 *            mower to update
	 * @param grass
	 *            grass where the mower is on
	 */
	public void update(final Mower mower, final Grass grass);

}
