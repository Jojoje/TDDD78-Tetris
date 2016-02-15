import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private SquareType[][] squares;
    private int width, height;

    private Poly falling = null;
    private int fallingX, fallingY;

    private List<BoardListener> boardListeners;

    public Board(final int width, final int height) {
	this.width = width;
	this.height = height;
	boardListeners = new ArrayList<BoardListener>();

	squares = new SquareType[width][height];

	for(int y = 0; y < height; y++){
	    for(int x = 0; x < width; x++) {
		squares[x][y] = SquareType.EMPTY;
	    }
	}
    }

    public void tick(){

    }

    public void randomiseBoard(){
	Random random = new Random();
	for(int y = 0; y < height; y++){
	    for(int x = 0; x < width; x++) {
		squares[x][y] = SquareType.values()[random.nextInt(8)];
	    }
	}

	notifyListeners();
    }

    public Poly getFalling() {
	return falling;
    }

    public int getFallingX() {
	return fallingX;
    }

    public int getFallingY() {
	return fallingY;
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public void addBoardListener(BoardListener bl){
	boardListeners.add(bl);
    }

    public void notifyListeners(){
	for(BoardListener bl : boardListeners){
	    bl.boardChanged();
	}
    }

    public SquareType getSquare(int x, int y){
	return squares[x][y];
    }

}
