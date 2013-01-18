package xebia.corentin.mower.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import xebia.corentin.mower.model.MowerPosition;

/**
 * Test case for the class MowerPosition
 * 
 * @author Corentin Jechoux
 * 
 * @see MowerPosition
 * 
 */
public class MowerPositionTestCase {

	private MowerPosition defaultPosition;

	@Before
	public void setup() {
		defaultPosition = new MowerPosition(10, 20, 'N');
	}

	/**
	 * Test the default constructor
	 * @throws Exception
	 */
	@Test
	public void testMowerPosition() throws Exception {
		final MowerPosition position = new MowerPosition(2, 5,
				MowerPosition.NORTH);

		assertEquals(2, position.getX());
		assertEquals(5, position.getY());
		assertEquals('N', position.getOrientation());
	}

	/**
	 * test an exception is raised if the MowerPosition X value is not
	 * permitted.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMowerPosition_setXException() {
		new MowerPosition(-2, 7, 'N');
	}

	/**
	 * test an exception is raised if the MowerPosition Y value is not
	 * permitted.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMowerPosition_setYException() {
		new MowerPosition(2, -7, 'N');
	}

	/**
	 * test an exception is raised if the MowerPosition direction value is not
	 * permitted.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMowerPosition_setDirectionException() {
		new MowerPosition(2, 7, 'Z');
	}

	/**
	 * Test the MowerPosition X setter and getter
	 */
	@Test
	public void testX() {
		// default assertions
		assertEquals(10, defaultPosition.getX());

		// call
		defaultPosition.setX(4);

		// assertions
		assertEquals(4, defaultPosition.getX());
	}

	/**
	 * test an exception is raised if the MowerPosition X value is not
	 * permitted.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testXException() {
		defaultPosition.setX(-99);
	}

	/**
	 * Test the MowerPosition Y setter and getter
	 */
	@Test
	public void testY() {
		// default assertions
		assertEquals(20, defaultPosition.getY());

		// call
		defaultPosition.setY(4);

		// assertions
		assertEquals(4, defaultPosition.getY());
	}

	/**
	 * test an exception is raised if the MowerPosition Y value is not
	 * permitted.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testYException() {
		defaultPosition.setY(-399);
	}

	@Test
	public void testDirection() {
		// defautlt assertions
		assertEquals('N', defaultPosition.getOrientation());

		// call
		defaultPosition.setOrientation('W');

		// assertions
		assertEquals('W', defaultPosition.getOrientation());
	}

	/**
	 * test an exception is raised if the MowerPosition direction value is not
	 * permitted.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDirectionException() {
		defaultPosition.setOrientation('V');
	}

}
