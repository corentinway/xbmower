package xebia.corentin.mower.model;

/**
 * a mower
 * 
 * @author Corentin Jechoux
 * 
 */
public class Mower {

	/**
	 * Current position of the mower
	 */
	private MowerPosition currentPosition;

	/**
	 * constructor
	 * 
	 */
	public Mower() {
	}

	/**
	 * Get the current position of the mower
	 * 
	 * @return
	 */
	public MowerPosition getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * Set the current position of the mower
	 * 
	 * @param currentPosition
	 *            mower position
	 */
	public void setCurrentPosition(final MowerPosition currentPosition) {
		this.currentPosition = currentPosition;
	}

}
