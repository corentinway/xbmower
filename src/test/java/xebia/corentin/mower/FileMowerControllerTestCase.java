package xebia.corentin.mower;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import xebia.corentin.mower.model.MowerPosition;

/**
 * Test case for the class FileMowerController.
 * 
 * @author Corentin Jechoux
 * 
 * @see FileMowerController
 */
public class FileMowerControllerTestCase {

	private String instructionFileName;

	private String instructionFileName_badGrass1;
	private String instructionFileName_badGrass2;

	private String instructionFileName_badMower1;
	private String instructionFileName_badMower2;
	private String instructionFileName_badMower3;

	private String instructionFileName_badCommand;

	private String instructionFileName_badFormat1;
	private String instructionFileName_badFormat2;
	private String instructionFileName_badFormat3;
	private String instructionFileName_badFormat4;

	private String instructionFileName_mowerOut1;
	private String instructionFileName_mowerOut2;

	@Before
	public void setup() throws UnsupportedEncodingException {
		instructionFileName = getResource("instruction.txt");
		instructionFileName_badGrass1 = getResource("instruction_bad_grass1.txt");
		instructionFileName_badGrass2 = getResource("instruction_bad_grass2.txt");

		instructionFileName_badMower1 = getResource("instruction_bad_mower1.txt");
		instructionFileName_badMower2 = getResource("instruction_bad_mower2.txt");
		instructionFileName_badMower3 = getResource("instruction_bad_mower3.txt");

		instructionFileName_badCommand = getResource("instruction_bad_command.txt");

		instructionFileName_badFormat1 = getResource("instruction_bad_format_gen1.txt");
		instructionFileName_badFormat2 = getResource("instruction_bad_format_gen2.txt");
		instructionFileName_badFormat3 = getResource("instruction_bad_format_gen3.txt");
		instructionFileName_badFormat4 = getResource("instruction_bad_format_gen4.txt");

		instructionFileName_mowerOut1 = getResource("instruction_mower_out1.txt");
		instructionFileName_mowerOut2 = getResource("instruction_mower_out2.txt");

	}

	/**
	 * Get a resource from a file name
	 * 
	 * @param name
	 *            name of the resource
	 * @return the absolute file path
	 * @throws UnsupportedEncodingException
	 *             if the resource file path decoding could not be done.
	 */
	private String getResource(String name) throws UnsupportedEncodingException {
		final URL url = Class.class.getResource("/" + name);
		final String path = URLDecoder.decode(url.getFile(), "UTF8");
		final File file = new File(path);

		return file.getAbsolutePath();
	}

	/**
	 * test that no exception is raised during instantiation
	 */
	@Test
	public void testFileMowerController() throws Exception {
		// input
		new FileMowerController(instructionFileName);
	}

	// test bad grass format
	@Test
	public void testBadGrassDefinition() {

		// inputs
		final String[] filenames = {
		/* bad grass format */
		instructionFileName_badGrass1, instructionFileName_badGrass2,
		/* bad mower format */
		instructionFileName_badMower1, instructionFileName_badMower2,
				instructionFileName_badMower3,
				/* bad command format */
				instructionFileName_badCommand,
				/* bad file format */
				instructionFileName_badFormat1, instructionFileName_badFormat2,
				instructionFileName_badFormat3, instructionFileName_badFormat4,
				/* mower out of the graass */
				instructionFileName_mowerOut1, instructionFileName_mowerOut2

		};

		// expected bad formatted line to be reported
		final String[] expectedLines = {
		/* bad grass */
		"5 5dhdh", "5     5",
		/* bad mower format */
		"1 2 Z", "1    2 N", "3 3.DF. E",
		/* bad command format */
		"GAGAGAGAAFNCHFNCK",
		/* bad file format */
		"", "", "", "",
		/* mower out of the grass */
		"10 2 N", "1 20 N"

		};

		final int[] expectedLineNumbers = {
		/* bad grass format */
		1, 1,
		/* bad mower format */
		2, 2, 4,
		/* bad command format */
		3,
		/* bad file format */
		0, 0, 0, 0, 
		/* mower out of the grass*/
		2, 2,
		};

		for (int i = 0; i < filenames.length; i++) {

			try {
				// call
				new FileMowerController(filenames[i]);

				// assertions
				fail("Exception should be raised");
			} catch (FileFormatMowerException e) {
				assertNotNull(e);
				assertEquals(expectedLineNumbers[i], e.getLineNumber());
				assertEquals(expectedLines[i], e.getLine());
			} catch (IOException e) {
				fail("Not expected: " + e);
			}
		}
	}

	@Test
	public void testControl() throws Exception /* FIXME */{

		// input
		final FileMowerController controller = new FileMowerController(
				instructionFileName);

		// call
		List<MowerPosition> positions = controller.control();

		// assertions
		assertEquals(2, positions.size());
		assertMowerPosition(1, 3, 'N', positions.get(0));
		assertMowerPosition(5, 1, 'E', positions.get(1));
	}

	// FIXME refactoring
	private void assertMowerPosition(final int x, final int y,
			final char orientation, final MowerPosition actual) {

		assertEquals(x, actual.getX());
		assertEquals(y, actual.getY());
		assertEquals(orientation, actual.getOrientation());
	}

}
