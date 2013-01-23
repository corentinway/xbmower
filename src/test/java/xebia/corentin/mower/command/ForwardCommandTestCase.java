package xebia.corentin.mower.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import xebia.corentin.mower.model.Grass;
import xebia.corentin.mower.model.Mower;
import xebia.corentin.mower.model.MowerPosition;

/**
 * Test case for ForwardCommand class.
 * 
 * @author Corentin Jechoux
 * 
 * @see ForwardCommand
 * 
 */
public class ForwardCommandTestCase {

	/**
	 * grass on which the mower run
	 */
	private Grass grass;
	/**
	 * command to test
	 */
	private Command command;
	/**
	 * mower that should be update by the command
	 */
	private Mower mower;

	@Before
	public void setup() {
		grass = new Grass();
		grass.setMaxX(5);
		grass.setMaxY(5);

		command = new ForwardCommand();

		mower = new Mower();
	}

	/**
	 * Test that a mower can not go outside the grass hedge.
	 */
	@Test
	public void testUpdateNotMove() {
		// inputs
		final String[] positions = { "0 3 W", "5 3 E", "2 0 S", "2 5 N" };

		for (final String position : positions) {
			final MowerPosition pos = toPosition(position);
			// initialization
			mower.setCurrentPosition(pos);

			// call
			command.update(mower, grass);

			// assertion
			assertPosition(pos, mower.getCurrentPosition());

		}

	}

	/**
	 * Test the mower move one step forward
	 */
	@Test
	public void testUpdateMove() {
		// inputs
		final String[] positions = { "2 2 N", "2 2 S", "2 2 E", "2 2 W" };

		// expected position
		final String[] expectedPositions = { "2 3 N", "2 1 S", "3 2 E", "1 2 W" };

		for (int i = 0; i < positions.length; i++) {
			final MowerPosition pos = toPosition(positions[i]);
			// initialization
			mower.setCurrentPosition(pos);

			// call
			command.update(mower, grass);

			// assertion
			assertPosition(toPosition(expectedPositions[i]),
					mower.getCurrentPosition());

		}

	}
	
	

	/**
	 * Assert a mower position.
	 * 
	 * @param expected
	 *            expected mower position
	 * @param actual
	 *            actual mower position
	 */
	private void assertPosition(final MowerPosition expected,
			final MowerPosition actual) {
		assertEquals(expected.getX(), actual.getX());
		assertEquals(expected.getY(), actual.getY());
		assertEquals(expected.getOrientation(), actual.getOrientation());

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
	private MowerPosition toPosition(final String pos) {
		final String[] parts = pos.split(" ");
		final int x = Integer.parseInt(parts[0]);
		final int y = Integer.parseInt(parts[1]);
		final Character orientation = parts[2].charAt(0);

		return new MowerPosition(x, y, orientation);
	}

}
