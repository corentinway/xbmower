package xebia.corentin.mower.command;


import static xebia.corentin.mower.MowerAssertTools.*;

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
			assertMowerPosition(pos, mower.getCurrentPosition());

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
			assertMowerPosition(toPosition(expectedPositions[i]),
					mower.getCurrentPosition());

		}

	}





	/**
	 * test that the mower do not go outside the grass if we feed it with many
	 * (10 times larger than the grass size) 'step forward' (A) command.
	 */
	@Test
	public void testFarAway() {

		// inputs
		// --------
		// mower starting X position
		final int startX = grass.getMaxX() / 2;
		// mower starting Y position
		final int startY = grass.getMaxY() / 2;
		// mower orientation set
		final char[] orientations = { 'N', 'S', 'E', 'W' };
		// maximum number of commands
		final int maxCmd = Math.max(grass.getMaxX(), grass.getMaxY()) * 10;

		// expected results
		final MowerPosition[] expecteds = new MowerPosition[4];
		expecteds[0] = new MowerPosition(startX, grass.getMaxY(), 'N');
		expecteds[1] = new MowerPosition(startX, 0, 'S');
		expecteds[2] = new MowerPosition(grass.getMaxX(), startY, 'E');
		expecteds[3] = new MowerPosition(0, startY, 'W');

		for (int i = 0; i < orientations.length; i++) {
			final MowerPosition start = new MowerPosition(startX, startY,
					orientations[i]);
			mower.setCurrentPosition(start);

			// call
			// ------
			for (int j = 0; j < maxCmd; j++) {
				command.update(mower, grass);
			}

			// assertions
			// -----------
			assertMowerPosition(expecteds[i], mower.getCurrentPosition());

		}

	}

}
