
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final int NUMBER_OF_SQUARETYPE = 8;

    private SquareType[][] squares;
    public int width, height;
    private Random random;

    private Poly falling = null;
    private int fallingX, fallingY;

    private TetrominoMaker tetrominoMaker;
    private List<BoardListener> boardListeners;

    public Board(final int width, final int height) {
	this.width = width;
	this.height = height;
	random = new Random();

	tetrominoMaker = new TetrominoMaker();
	boardListeners = new ArrayList<BoardListener>();

	squares = new SquareType[width][height];

	for(int y = 0; y < height; y++){
	    for(int x = 0; x < width; x++) {
		squares[x][y] = SquareType.EMPTY;
	    }
	}
    }

    public void tick(){
	if(falling == null){
	    falling = tetrominoMaker.createPoly(SquareType.values()[random.nextInt(NUMBER_OF_SQUARETYPE)]);
	    fallingX = falling.getBlock().length == 2 ? (int) width/2 - 1 : (int)width/2 - 2;
	    fallingY = 0;

	}else{
	    fallingY += 1;
	}
	notifyListeners();
    }

    public void moveFallingRight(){
	fallingX++;
	notifyListeners();
    }

    public void moveFallingLeft(){
	fallingX--;
	notifyListeners();
    }

    public void randomiseBoard(){
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
