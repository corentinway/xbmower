package xebia.corentin.mower.model;

/**
 * Position of the mower in the grass.
 * 
 * @author Corentin Jechoux
 * 
 */
public class MowerPosition {
	/**
	 * X coordinate of the mower in the grass.
	 * <P>
	 * This number must be positive or equals to zero.
	 */
	private int x;
	/**
	 * Y coordinate of the mower in the grass.
	 * <P>
	 * This number must be positive or equals to zero.
	 */
	private int y;
	/**
	 * direction of the mower.
	 * <P>
	 * When the mower move one step forward, it will go in this direction.
	 * <P>
	 * Values can be 'N', 'E', 'W' or 'S', corresponding to the cardinal
	 * direction north, east, west and south.
	 */
	private char orientation;
	public static final char NORTH = 'N';
	public static final char WEST = 'W';
	public static final char SOUTH = 'S';
	public static final char EAST = 'E';

	/**
	 * Create a new position for the mower.
	 * <P>
	 * 
	 * @param x
	 *            X mower position
	 * @param y
	 *            Y mower position
	 * @param direction
	 *            mower direction
	 * 
	 * @throws IllegalArgumentException
	 *             see bellow
	 * 
	 * @see MowerPosition#setX(int)
	 * @see MowerPosition#setY(int)
	 * @see MowerPosition#setOrientation(char)
	 */
	public MowerPosition(final int x, final int y, final char direction) {
		setX(x);
		setY(y);
		setOrientation(direction);
	}

	/**
	 * return the mower X coordinate
	 * 
	 * @return x X coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the mower X coordinate
	 * 
	 * @param x
	 *            X coordinate
	 * @throws IllegalArgumentException
	 *             if the x parameter is negative.
	 */
	public void setX(int x) {
		if (x < 0) {
			throw new IllegalArgumentException(
					"The X coordinate shoule be positive");
		}
		this.x = x;
	}

	/**
	 * get the mower Y coordinate
	 * 
	 * @return mower Y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the mower Y coordinate
	 * 
	 * @param y
	 *            Y coordinate
	 * @throws IllegalArgumentException
	 *             if the y parameter is negrative.
	 */
	public void setY(int y) {
		if (y < 0) {
			throw new IllegalArgumentException(
					"The Y coordinate shoule be positive");
		}
		this.y = y;
	}

	/**
	 * Get the mower direction.
	 * <P>
	 * Values can be 'N', 'E', 'W' or 'S', corresponding to the cardinal
	 * direction north, east, west and south.
	 * 
	 * @return the mower direction.
	 */
	public char getOrientation() {
		return orientation;
	}

	/**
	 * Set the mower direction
	 * <P>
	 * Values can be 'N', 'E', 'W' or 'S', corresponding to the cardinal
	 * direction north, east, west and south.
	 * 
	 * @param orientation
	 *            mower direction
	 */
	public void setOrientation(char orientation) {
		if (orientation == NORTH || orientation == EAST	 || orientation == WEST
				|| orientation == SOUTH) {
			this.orientation = orientation;
		} else {
			throw new IllegalArgumentException(
					"Values can be 'N', 'E', 'W' or 'S', corresponding to the cardinal direction north, east, west and south."
					+ " Orientation not allowed: \"" + orientation + "\"");
		}
	}

}
