package xebia.corentin.mower.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for Grass class
 * 
 * @author Corentin Jechoux
 * 
 * @see Grass
 */
public class GrassTestCase {

	/**
	 * grass to test
	 */
	private Grass grass;

	@Before
	public void setup() {
		grass = new Grass();
	}

	/**
	 * test getter and setter
	 */
	@Test
	public void test() {
		grass.setMaxX(2);
		grass.setMaxY(3);

		assertEquals(2, grass.getMaxX());
		assertEquals(3, grass.getMaxY());
	}

	/**
	 * test invalid X value
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalX() {
		grass.setMaxX(-2);
	}

	/**
	 * test invalid Y value
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalY() {
		grass.setMaxY(-2);
	}
}
