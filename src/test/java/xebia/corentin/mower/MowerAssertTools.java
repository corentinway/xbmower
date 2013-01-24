package xebia.corentin.mower;

import static org.junit.Assert.assertEquals;
import xebia.corentin.mower.model.MowerPosition;

/**
 * tools for test cases.
 * 
 * @author Corentin Jechoux
 * 
 */
public class MowerAssertTools {

	/**
	 * Assert a mower position
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param orientation
	 *            cardinal orientation
	 * @param actual
	 *            value to test
	 */
	public static void assertMowerPosition(final int x, final int y,
			final char orientation, final MowerPosition actual) {

		assertEquals(x, actual.getX());
		assertEquals(y, actual.getY());
		assertEquals(orientation, actual.getOrientation());
	}

	/**
	 * Assert a mower position
	 * 
	 * @param expected
	 *            expected value
	 * @param actual
	 *            actual value to compare to
	 */
	public static void assertMowerPosition(final MowerPosition expected,
			final MowerPosition actual) {
		assertMowerPosition(expected.getX(), expected.getY(),
				expected.getOrientation(), actual);
	}

	/**
	 * Transform a string like "1 2 N" into a mower position.
	 * <P>
	 * The format is <code>x y orientationChar</code>
	 * 
	 * @param pos
	 *            position string
	 * @return new mower position
	 */
	public static MowerPosition toPosition(final String pos) {
		final String[] parts = pos.split(" ");
		final int x = Integer.parseInt(parts[0]);
		final int y = Integer.parseInt(parts[1]);
		final Character orientation = parts[2].charAt(0);

		return new MowerPosition(x, y, orientation);
	}
}
