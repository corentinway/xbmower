package xebia.corentin.mower;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import xebia.corentin.mower.MowerController;
import xebia.corentin.mower.model.Grass;
import xebia.corentin.mower.model.Mower;
import xebia.corentin.mower.model.MowerPosition;

public class MowerControllerTestCase {

	private Mower mower;
	private Grass grass;
	private MowerController controller;

	@Before
	public void setup() {
		mower = new Mower();

		grass = new Grass();
		grass.setHeight(5);
		grass.setWidth(5);
		
		controller = new MowerController(mower);
		
	}

	@Test
	public void testMower1() {

		// prerequisite
		mower.setCurrentPosition(new MowerPosition(1, 2, 'N'));

		// input
		final String cmds = "GAGAGAGAA";

		// call
		final MowerPosition lastPos = controller.control(cmds, grass);

		// 1 3 N
		assertMowerPosition(1, 3, MowerPosition.NORTH, lastPos);
	}

	@Test
	public void testMower2() {

		// prerequisite
		mower.setCurrentPosition(new MowerPosition(3, 3, 'E'));

		// input
		final String cmds = "AADAADADDA";

		// call
		final MowerPosition lastPos = controller.control(cmds, grass);

		// 5 1 E
		assertMowerPosition(5, 1, MowerPosition.EAST, lastPos);
	}

	/**
	 * assert a mower positino
	 * 
	 * @param x
	 *            X position on the grass
	 * @param y
	 *            Y position on the grass
	 * @param orientation
	 *            mower orientation
	 * @param actual
	 *            position to assert.
	 */
	private void assertMowerPosition(final int x, final int y,
			final char orientation, final MowerPosition actual) {

		assertEquals(x, actual.getX());
		assertEquals(y, actual.getY());
		assertEquals(orientation, actual.getOrientation());
	}

}
