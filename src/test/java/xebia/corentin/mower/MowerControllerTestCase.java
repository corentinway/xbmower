package xebia.corentin.mower;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import xebia.corentin.mower.model.Grass;
import xebia.corentin.mower.model.Mower;
import xebia.corentin.mower.model.MowerPosition;

/**
 * Test case for MowerController
 * 
 * @author Corentin Jechoux
 * 
 */
public class MowerControllerTestCase {
	/**
	 * mower to pilote
	 */
	private Mower mower;
	/**
	 * grass that will host the mower
	 */
	private Grass grass;
	/**
	 * "brain" that control the mower.
	 */
	private MowerController controller;

	@Before
	public void setup() {
		mower = new Mower();

		grass = new Grass();
		grass.setMaxY(5);
		grass.setMaxX(5);

		controller = new MowerController(mower);

	}

	/**
	 * Test the mower is reaching the expected position.
	 * 
	 * <pre>
	 * 		starting position: 	1 2 N
	 * 		commands:			GAGAGAGAA
	 * 		expected positions:	3 3 E
	 * </pre>
	 */
	@Test
	public void testMower1() {

		// prerequisite
		mower.setCurrentPosition(new MowerPosition(1, 2, 'N'));

		// input
		final String cmds = "GAGAGAGAA";

		// call
		final MowerPosition lastPos = controller.control(cmds, grass);

		// assertions. Expected: 1 3 N
		assertMowerPosition(1, 3, MowerPosition.NORTH, lastPos);
	}

	/**
	 * Test the mower is reaching the expected position.
	 * 
	 * <pre>
	 * 		starting position: 	3 3 E
	 * 		commands:			AADAADADDA
	 * 		expected positions:	5 1 E
	 * </pre>
	 */
	@Test
	public void testMower2() {

		// prerequisite
		mower.setCurrentPosition(new MowerPosition(3, 3, 'E'));

		// input
		final String cmds = "AADAADADDA";

		// call
		final MowerPosition lastPos = controller.control(cmds, grass);

		// assertions. Expected: 5 1 E
		assertMowerPosition(5, 1, MowerPosition.EAST, lastPos);
	}

	/**
	 * test the mower do not move if the command sequence is null.
	 */
	@Test
	public void testNoCommandSequence() {
		// prerequisite
		mower.setCurrentPosition(new MowerPosition(3, 3, 'E'));

		// input
		final String cmds = null;

		// call
		final MowerPosition lastPos = controller.control(cmds, grass);

		// 5 1 E
		assertMowerPosition(3, 3, MowerPosition.EAST, lastPos);
	}

	/**
	 * test the mower do not move if the command sequence is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalCommandKey() {
		// prerequisite
		mower.setCurrentPosition(new MowerPosition(3, 3, 'E'));

		// input
		final String cmds = "AGDZAGD";

		// call
		controller.control(cmds, grass);

	}

	/**
	 * assert a mower position.
	 * 
	 * @param x
	 *            expected X position on the grass
	 * @param y
	 *            expected Y position on the grass
	 * @param orientation
	 *            expected mower orientation
	 * @param actual
	 *            actual mower position
	 */
	private void assertMowerPosition(final int x, final int y,
			final char orientation, final MowerPosition actual) {

		assertEquals(x, actual.getX());
		assertEquals(y, actual.getY());
		assertEquals(orientation, actual.getOrientation());
	}

}
