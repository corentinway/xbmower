package xebia.corentin.mower.model;

/**
 * Define the grass on which the mower will run.
 * <P>
 * Its minimum coordinate are (0, 0) corresponding to the bottom left corner.
 * Its maximum coordinate are (X, Y) corresponding to the top right corner
 * coordinate.
 * 
 * 
 * @author Corentin Jechoux
 * 
 */
public class Grass {

	// error messages
	private static final String NEGATIVE_X_ERROR = "X must be a positive integer";
	private static final String NEGATIVE_Y_ERROR = "Y must be a positive integer";
	/**
	 * maximum X limit (inclusive)
	 */
	private int maxX;
	/**
	 * maximum Y limit (inclusive)
	 */
	private int maxY;

	/**
	 * get the maximum X limit.
	 * 
	 * @return x coordinate
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * Set the maximum X limit.
	 * 
	 * @param x
	 *            positive integer
	 */
	public void setMaxX(int x) {
		if (x < 0) {
			throw new IllegalArgumentException(NEGATIVE_X_ERROR);
		}
		this.maxX = x;
	}

	/**
	 * Get the maximum Y limit
	 * 
	 * @return y positive coordinate
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * Set the maximum Y limit
	 * 
	 * @param y
	 *            y positive coordinate
	 */
	public void setMaxY(int y) {
		if (y < 0) {
			throw new IllegalArgumentException(NEGATIVE_Y_ERROR);
		}
		this.maxY = y;
	}

}
