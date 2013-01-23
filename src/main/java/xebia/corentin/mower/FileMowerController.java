package xebia.corentin.mower;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import xebia.corentin.mower.model.Grass;
import xebia.corentin.mower.model.Mower;
import xebia.corentin.mower.model.MowerPosition;

/**
 * This class control a set of mower from a file description.
 * <P>
 * The format of the file follow the rules bellow :
 * <UL>
 * <LI>The first line match the top right grass coordinate. <BR>
 * Format: <code>X Y</code></LI>
 * <LI>Each even following lines describe the starting mower position. <BR>
 * Format: <code>X Y Orientation</code></LI>
 * <LI>Each odd following lines describe the command sequence. <BR>
 * Format: <code>Cmd1Cmd2Cmd3</code></LI>
 * </UL>
 * 
 * @author Corentin Jechoux
 * 
 */
public class FileMowerController {

	/**
	 * grass defined from the file
	 */
	private Grass grass;
	/**
	 * list of mowers to control
	 */
	private List<MowerController> controllers;
	/**
	 * list of commands. One element has its matching mower in the list
	 * {@link #controllers}.
	 */
	private List<String> commands;

	/**
	 * minimum number of line the file must have: The minimum requirements is
	 * one grass definition, one mower definition and one command definition for
	 * that mower.
	 */
	private static final int MINIMUM_LINES = 3;
	/**
	 * first line number
	 */
	private static final int FIRST_LINE = 1;

	/**
	 * create the file mower controller
	 * 
	 * @param filename
	 *            name of the file to read and "execute"
	 * @throws IOException
	 * @throws FileFormatMowerException
	 */
	public FileMowerController(final String filename) throws IOException,
			FileFormatMowerException {
		controllers = new ArrayList<MowerController>();
		commands = new ArrayList<String>();
		parse(filename);
	}

	/**
	 * Parse the file and set the grass, all mowers and command sequence
	 * 
	 * @param filename
	 * @throws IOException
	 * @throws FileFormatMowerException
	 */
	private void parse(String filename) throws IOException,
			FileFormatMowerException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename)));

			// line read
			String line = null;
			// line number
			int n = FIRST_LINE;

			while ((line = reader.readLine()) != null) {
				if (n == FIRST_LINE) {
					parseGrass(line, n);
				} else if (n % 2 == 0) {
					parseMower(line, n);
				} else if (n % 2 == 1) {
					parseCommands(line, n);
				}
				n++;
			}

			// check
			if (n < MINIMUM_LINES) {
				// There must be at least one grass definition, one mower
				// definition and one command sequence
				throw new FileFormatMowerException(
						"There must be at least one grass definition, one mower definition and one command sequence",
						"", 0);
			}
			if (controllers.size() != commands.size()) {
				throw new FileFormatMowerException(
						"The number of mower starting position is not equals to the number of mower's sequence",
						"", 0);
			}

		} finally {
			if (reader != null) {
				reader.close();
			}
		}

	}

	/**
	 * parse a grass definition from the a line
	 * 
	 * @param line
	 *            grass definition
	 * @param lineNumber
	 *            line number
	 * @throws FileFormatMowerException
	 *             thrown if the grass format is not valid
	 */
	private void parseGrass(final String line, final int lineNumber)
			throws FileFormatMowerException {
		if (!line.matches("^\\d+ \\d+$")) {
			throw new FileFormatMowerException(
					"The grass definition does not match the requirements.",
					line, lineNumber);
		}

		final String[] parts = line.split(" ");

		final int width = Integer.parseInt(parts[0]);
		final int height = Integer.parseInt(parts[1]);

		grass = new Grass();
		grass.setMaxX(width);
		grass.setMaxY(height);
	}

	/**
	 * Parse a mower position from a string
	 * 
	 * @param line
	 *            string to parse
	 * @param lineNumber
	 *            line number of the definition in the file
	 * @throws FileFormatMowerException
	 *             thrown in case the definition is not the correct format.
	 */
	private void parseMower(final String line, final int lineNumber)
			throws FileFormatMowerException {
		if (!line.matches("^\\d+ \\d+ [NSEW]$")) {
			throw new FileFormatMowerException(
					"The mower position does not match the requirements.",
					line, lineNumber);
		}

		final String[] parts = line.split(" ");

		final int x = Integer.parseInt(parts[0]);
		final int y = Integer.parseInt(parts[1]);

		// the Mower must be inside the grass
		if (x > grass.getMaxX() || y > grass.getMaxY()) {
			throw new FileFormatMowerException(
					"The mower can not be set outside", line, lineNumber);
		}

		final char orientation = parts[2].charAt(0);

		final MowerPosition position = new MowerPosition(x, y, orientation);
		final Mower mower = new Mower();
		mower.setCurrentPosition(position);

		final MowerController controller = new MowerController(mower);
		controllers.add(controller);

	}

	private void parseCommands(final String line, final int lineNumber)
			throws FileFormatMowerException {
		if (!line.matches("^[GDA]+$")) {
			throw new FileFormatMowerException(
					"the commands sequence MUST only contains the following characters: A, G or D",
					line, lineNumber);
		} else {
			commands.add(line);
		}
	}

	/**
	 * Control all the mower in the order has they were defined.
	 * <P>
	 * This move all the mowers and return a list of {@link MowerPosition}: each
	 * position match de mower in the same order as it was defined in the
	 * instruction file.
	 * 
	 * @return all last position of the mowers
	 */
	public List<MowerPosition> control() {
		// mower position
		final List<MowerPosition> positions = new ArrayList<MowerPosition>();

		for (int i = 0; i < controllers.size(); i++) {
			final MowerController controller = controllers.get(i);
			final String command = commands.get(i);

			final MowerPosition lastPosition = controller.control(command,
					grass);
			positions.add(lastPosition);
		}

		return positions;
	}

}
