public class Board {
    private SquareType[][] squares;
    private int width, height;

    public Board(final int width, final int height) {
	this.width = width;
	this.height = height;

	squares = new SquareType[width][height];
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public SquareType[][] getSquares() {
	return squares;
    }

}
