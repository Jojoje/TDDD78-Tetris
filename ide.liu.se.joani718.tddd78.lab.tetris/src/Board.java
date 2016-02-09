import java.util.Random;

public class Board {
    private SquareType[][] squares;
    private int width, height;

    public Board(final int width, final int height) {
	this.width = width;
	this.height = height;

	squares = new SquareType[width][height];

	for(int y = 0; y < getHeight(); y++){
	    for(int x = 0; x < getWidth(); x++) {
		squares[x][y] = SquareType.EMPTY;
	    }
	}
    }

    public void randomiseBoard(){
	Random random = new Random();
	for(int y = 0; y < getHeight(); y++){
	    for(int x = 0; x < getWidth(); x++) {
		squares[x][y] = SquareType.values()[random.nextInt(8)];
	    }
	}
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public SquareType getSquare(int x, int y){
	return squares[x][y];
    }

}
