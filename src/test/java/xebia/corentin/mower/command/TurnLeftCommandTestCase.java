package xebia.corentin.mower.command;

import static org.junit.Assert.assertEquals;
import static xebia.corentin.mower.model.MowerPosition.EAST;
import static xebia.corentin.mower.model.MowerPosition.NORTH;
import static xebia.corentin.mower.model.MowerPosition.SOUTH;
import static xebia.corentin.mower.model.MowerPosition.WEST;

import org.junit.Before;
import org.junit.Test;

import xebia.corentin.mower.command.Command;
import xebia.corentin.mower.command.TurnLeftCommand;

/**
 * Test case for the TurnLeftCommand.
 * 
 * @author Corentin Jechoux
 * 
 * @see TurnLeftCommand
 * 
 */
public class TurnLeftCommandTestCase extends AbstractTurnTestCase {

	/**
	 * Command to test
	 */
	private Command command;

	@Before
	public void setup() {
		super.setup();
		command = new TurnLeftCommand();
	}

	/**
	 * Test the the mower turn reverse clockwise or by the left each time the
	 * TurnLeftCommand is invoked on it.
	 */
	@Test
	public void testUpdate() {

		final char[] expectedDirection = { WEST, SOUTH, EAST, NORTH };

		for (int i = 0; i < 4; i++) {
			// call
			command.update(mower, grass);
			// assertions
			assertEquals(expectedDirection[i], mower.getCurrentPosition()
					.getOrientation());
		}

	}

}
