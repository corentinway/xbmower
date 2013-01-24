package xebia.corentin.mower;

/**
 * This exception should be raised each time a line has a bad format in the
 * mower instructio file.
 * 
 * @author Corentin Jechoux
 * 
 */
public class FileFormatMowerException extends Exception {

	/**
	 * bad formatted line
	 */
	private String line;
	/**
	 * line number in the mower instruction file
	 */
	private int lineNumber;
	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a new exception concerning bad format in the instruction file
	 * 
	 * @param line
	 *            line with the wrong format
	 * @param lineNumber
	 *            line number in the instruction file
	 */
	public FileFormatMowerException(final String message, final String line,
			final int lineNumber) {
		super(message);
		this.line = line;
		this.lineNumber = lineNumber;
	}

	/**
	 * Use this constructor if the exception apply for all the instruction file.
	 * @param message
	 */
	public FileFormatMowerException(final String message) {
		this(message, "", 0);
	}

	/**
	 * get the line content
	 * 
	 * @return the line content
	 */
	public String getLine() {
		return line;
	}

	/**
	 * get the line number
	 * 
	 * @return the line number
	 */
	public int getLineNumber() {
		return lineNumber;
	}

}
