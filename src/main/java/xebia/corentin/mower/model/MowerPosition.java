package xebia.corentin.mower.model;

import java.util.regex.Pattern;

/**
 * Position of the mower in the grass.
 * 
 * @author Corentin Jechoux
 * 
 */
public class MowerPosition {
	private static final String ORIENTATION_ERROR = "Values can be 'N', 'E', 'W' or 'S', corresponding to the cardinal orientation north, east, west and south.";
	private static final String NEGATIVE_COORDINATE_ERROR = "Coordinates should be greater or equal to zero";
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
	 * orientation of the mower.
	 * <P>
	 * When the mower move one step forward, it will go in this direction.
	 * <P>
	 * Values can be 'N', 'E', 'W' or 'S', corresponding to the cardinal
	 * orientation north, east, west and south.
	 */
	private char orientation;
	private static final Pattern ORIENTATION_REGEXP = Pattern.compile("[NWSE]");
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
	 * @param orientation
	 *            mower orientation
	 * 
	 * @throws IllegalArgumentException
	 *             see bellow
	 * 
	 * @see MowerPosition#setX(int)
	 * @see MowerPosition#setY(int)
	 * @see MowerPosition#setOrientation(char)
	 */
	public MowerPosition(final int x, final int y, final char orientation) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException(NEGATIVE_COORDINATE_ERROR);
		}
		if (!ORIENTATION_REGEXP.matcher(orientation + "").matches()) {
			throw new IllegalArgumentException(ORIENTATION_ERROR);
		}
		this.x = x;
		this.y = y;
		this.orientation = orientation;
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
			throw new IllegalArgumentException(NEGATIVE_COORDINATE_ERROR);
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
			throw new IllegalArgumentException(NEGATIVE_COORDINATE_ERROR);
		}
		this.y = y;
	}

	/**
	 * Get the mower orientation.
	 * <P>
	 * Values can be 'N', 'E', 'W' or 'S', corresponding to the cardinal
	 * orientation north, east, west and south.
	 * 
	 * @return the mower orientation.
	 */
	public char getOrientation() {
		return orientation;
	}

	/**
	 * Set the mower orientation
	 * <P>
	 * Values can be 'N', 'E', 'W' or 'S', corresponding to the cardinal
	 * orientation north, east, west and south.
	 * 
	 * @param orientation
	 *            mower orientation
	 */
	public void setOrientation(char orientation) {
		if (!ORIENTATION_REGEXP.matcher(orientation + "").matches()) {
			throw new IllegalArgumentException(ORIENTATION_ERROR);
		}
		this.orientation = orientation;

	}

}
