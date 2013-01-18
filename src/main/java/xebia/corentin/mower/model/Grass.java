package xebia.corentin.mower.model;

/**
 * To define the grass on which the mower will run.
 * <P>
 * The grass is a square with a <em>width</em> and a <em>height</em>. Its
 * minimum coordinate are (0, 0) corresponding to the bottom left corner. Its
 * maximum coordinate are (width, height) corresponding to the top right corner.
 * 
 * 
 * @author Corentin Jechoux
 * 
 */
public class Grass {

	private int width;

	private int height;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
