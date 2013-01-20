package xebia.corentin.mower.command;

import xebia.corentin.mower.model.Grass;
import xebia.corentin.mower.model.Mower;
import xebia.corentin.mower.model.MowerPosition;

/**
 * the command move the mower one step forward according to the mower direction.
 * <P>
 * If the mower can not go outside of grass. Here are example the do not make
 * the mower move forward.
 * <P>
 * <ul>
 * <li>(0, y, W)</li>
 * <li>(width, y, E)</li>
 * <li>(x, 0, S)</li>
 * <li>(x, height, N)</li>
 * </ul>
 * 
 * @author Corentin Jechoux
 * 
 */

/*
 * 0 y W
 * max y E
 * x 0 S
 * x mxa N
 */
public class ForwardCommand implements Command {

	/**
	 * {@inheritDoc}
	 */
	public void update(final Mower mower, final Grass grass) {
		final MowerPosition position = mower.getCurrentPosition();

		if (canMove(position.getX(), position.getY(), position.getOrientation(),
				grass)) {
			stepForward(position);
		}

	}

	/**
	 * move one step forward.
	 * 
	 * @param position
	 *            mower's position
	 */
	private void stepForward(MowerPosition position) {
		if (position.getOrientation() == MowerPosition.SOUTH) {
			position.setY(position.getY() - 1);
		} else if (position.getOrientation() == MowerPosition.NORTH) {
			position.setY(position.getY() + 1);
		} else if (position.getOrientation() == MowerPosition.WEST) {
			position.setX(position.getX() - 1);
		} else if (position.getOrientation() == MowerPosition.EAST) {
			position.setX(position.getX() + 1);
		} else {
			throw new IllegalArgumentException("bad orientation: "
					+ position.getOrientation());
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
		} else if (x == grass.getWidth() && orientation == MowerPosition.EAST) {
			return false;
		} else if (y == 0 && orientation == MowerPosition.SOUTH) {
			return false;
		} else if (y == grass.getHeight() && orientation == MowerPosition.NORTH) {
			return false;
		} else {
			return true;
		}
	}
}
