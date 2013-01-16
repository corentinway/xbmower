package xebia.mower.corentin;

/**
 * Commands to control the mower
 * @author Corentin Jechoux
 *
 */
public interface MowerCommand {
	/**
	 * move the mower one step forward
	 */
	public static final char FORWARD = 'A';
	/**
	 * 90 degree left rotation of the mower
	 */
	public static final char LEFT = 'G';
	/**
	 * 90 degree right rotation of the mower
	 */
	public static final char RIGHT = 'D';
	
}
