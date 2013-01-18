package xebia.corentin.mower.command;

import static xebia.corentin.mower.model.MowerPosition.NORTH;
import xebia.corentin.mower.model.Grass;
import xebia.corentin.mower.model.Mower;
import xebia.corentin.mower.model.MowerPosition;

/**
 * test case for any Turn operation
 * 
 * @author Corentin Jechoux
 * 
 */
public class AbstractTurnTestCase {

	/**
	 * the mower that we must assert its state
	 */
	protected Mower mower;
	/**
	 * The grass on which the mower run on
	 */
	protected Grass grass;

	/**
	 * default setup: mower set to (0, 0, N) and a 5x5 sized grass.
	 */
	public void setup() {
		mower = new Mower();
		MowerPosition position = new MowerPosition(0, 0, NORTH);
		mower.setCurrentPosition(position);

		grass = new Grass();
		grass.setHeight(5);
		grass.setWidth(5);

	}

}
