package xebia.corentin.mower.command;

import static org.junit.Assert.assertEquals;
import static xebia.corentin.mower.model.MowerPosition.EAST;
import static xebia.corentin.mower.model.MowerPosition.NORTH;
import static xebia.corentin.mower.model.MowerPosition.SOUTH;
import static xebia.corentin.mower.model.MowerPosition.WEST;

import org.junit.Before;
import org.junit.Test;

import xebia.corentin.mower.command.Command;
import xebia.corentin.mower.command.TurnRightCommand;

/**
 * Test case for the class TurnRightCommand.
 * 
 * @author Corentin Jechoux
 * 
 * @see TurnRightCommand
 * 
 */
public class TurnRightCommandTestCase extends AbstractTurnTestCase {

	/**
	 * command to test
	 */
	private Command command;

	@Before
	public void setup() {
		super.setup();
		command = new TurnRightCommand();
	}

	/**
	 * Test the the mower turn clockwise or by the right each time the
	 * TurnRightCommand is invoked on it.
	 */
	@Test
	public void testUpdate() {

		final char[] expectedDirection = { EAST, SOUTH, WEST, NORTH };

		for (int i = 0; i < 4; i++) {
			// call
			command.update(mower, grass);
			// assertions
			assertEquals(expectedDirection[i], mower.getCurrentPosition()
					.getOrientation());
		}

	}

}
