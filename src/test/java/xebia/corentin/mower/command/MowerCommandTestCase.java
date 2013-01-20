package xebia.corentin.mower.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xebia.corentin.mower.command.MowerCommand;

/**
 * Test case for the MowerCommand class
 * 
 * @author Corentin Jechoux
 * 
 * 
 * @see MowerCommand
 */
public class MowerCommandTestCase {

	@Test
	public void test() {

		assertEquals('A', MowerCommand.FORWARD);
		assertEquals('G', MowerCommand.LEFT);
		assertEquals('D', MowerCommand.RIGHT);

	}

}
