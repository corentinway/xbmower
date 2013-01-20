package xebia.corentin.mower.command;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommandManagerTestCase {

	/**
	 * test the instantiation raise NO exception
	 */
	@Test
	public void testCommandManager() {
		new CommandManager();
	}

	/**
	 * test all command are registered in the manager. 
	 */
	@Test
	public void testGetCommand() {
		CommandManager manager = new CommandManager();

		assertTrue(manager.getCommand('A') instanceof ForwardCommand);
		assertTrue(manager.getCommand('G') instanceof TurnLeftCommand);
		assertTrue(manager.getCommand('D') instanceof TurnRightCommand);
		
		assertNull(manager.getCommand('Z'));
	}

}
