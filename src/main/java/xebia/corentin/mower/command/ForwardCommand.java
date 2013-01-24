package xebia.corentin.mower.command;

import xebia.corentin.mower.model.Grass;
import xebia.corentin.mower.model.Mower;
import xebia.corentin.mower.model.MowerPosition;

/**
 * the command move the mower one step forward according to the mower direction.
 * <P>
 * The mower can not go outside of grass. If the mower current position is at
 * the hedge of the grass, then it can not move outside. Here are example the do
 * not make the mower move forward.
 * <P>
 * <ul>
 * <li><code>(0, y, W)</code></li>
 * <li><code>(width, y, E)</code></li>
 * <li><code>(x, 0, S)</code></li>
 * <li><code>(x, height, N)</code></li>
 * </ul>
 * <P>
 * The mower movement follow the rules below:
 * <UL>
 * <li>If the mower orientation is <em>south</em>, then the mower will go
 * <em>down</em></li>
 * <li>If the mower orientation is <em>north</em>, then the mower will go
 * <em>up</em></li>
 * <li>If the mower orientation is <em>west</em>, then the mower will move to
 * the <em>left</em></li>
 * <li>If the mower orientation is <em>east</em>, then the mower will move to
 * the <em>right</em></li>
 * </UL>
 * 
 * @author Corentin Jechoux
 * 
 */
public class ForwardCommand implements Command {

	/**
	 * {@inheritDoc}
	 */
	public void update(final Mower mower, final Grass grass) {
		// get the current mower position
		final MowerPosition position = mower.getCurrentPosition();
		// check if the mower can move
		if (canMove(position.getX(), position.getY(),
				position.getOrientation(), grass)) {
			// move one step forward
			stepForward(position);
		}

	}

	/**
	 * move one step forward.
	 * 
	 * @param position
	 *            mower's position
	 */
	private void stepForward(final MowerPosition position) {
		if (position.getOrientation() == MowerPosition.SOUTH) {
			position.setY(position.getY() - 1);
		} else if (position.getOrientation() == MowerPosition.NORTH) {
			position.setY(position.getY() + 1);
		} else if (position.getOrientation() == MowerPosition.WEST) {
			position.setX(position.getX() - 1);
		} else if (position.getOrientation() == MowerPosition.EAST) {
			position.setX(position.getX() + 1);
		}

	}

	/**
	 * Check if the mower can move one step forward.
	 * <P>
	 * We check if the mower current position is on the grass hedge and then
	 * return false. Otherwise, we return false and the mower can move one step
	 * forward.
	 * 
	 * @param x
	 *            move X position
	 * @param y
	 *            mower Y position
	 * @param orientation
	 *            mower cardinal direction
	 * @param grass
	 *            grass on which the mower is on.
	 * @return true if the mower can move. Otherwise false.
	 */
	private boolean canMove(final int x, final int y, final char orientation,
			final Grass grass) {

		if (x == 0 && orientation == MowerPosition.WEST) {
			return false;
		} else if (x == grass.getMaxX() && orientation == MowerPosition.EAST) {
			return false;
		} else if (y == 0 && orientation == MowerPosition.SOUTH) {
			return false;
		} else if (y == grass.getMaxY() && orientation == MowerPosition.NORTH) {
			return false;
		} else {
			return true;
		}
	}
}
