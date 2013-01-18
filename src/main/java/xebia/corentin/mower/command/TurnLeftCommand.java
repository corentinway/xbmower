package xebia.corentin.mower.command;

import xebia.corentin.mower.model.Grass;
import xebia.corentin.mower.model.Mower;
import xebia.corentin.mower.model.MowerPosition;

/**
 * Turn the mower 90° to the left.
 * <P>
 * Here are all possible states:
 * 
 * <pre>
 * 		mower initial direction		mower new direction
 * 		-----------------------		--------------------
 * 				North					West
 * 				West					South
 * 				South					East
 * 				East					North
 * </pre>
 * 
 * @author Corentin Jechoux
 * 
 */
public class TurnLeftCommand implements Command {

	/**
	 * {@inheritDoc}
	 */
	public void update(final Mower mower, final Grass grass) {
		final char direction = mower.getCurrentPosition().getOrientation();
		
		if (direction == MowerPosition.NORTH) {
			mower.getCurrentPosition().setOrientation(MowerPosition.WEST);
		} else if (direction == MowerPosition.WEST) {
			mower.getCurrentPosition().setOrientation(MowerPosition.SOUTH);
		} else if (direction == MowerPosition.SOUTH) {
			mower.getCurrentPosition().setOrientation(MowerPosition.EAST);
		} else if (direction == MowerPosition.EAST) {
			mower.getCurrentPosition().setOrientation(MowerPosition.NORTH);
		}
	}

}
