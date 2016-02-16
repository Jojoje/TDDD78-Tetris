import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private final int PADDING = 2;

    private SquareType[][] squares;
    private int width, height;
    private Random random;

    private Poly falling = null;
    private int fallingX, fallingY;

    private TetrominoMaker tetrominoMaker;
    private List<BoardListener> boardListeners;

    private boolean gameOver = false;

    public Board(final int width, final int height) {
	this.width = width;
	this.height = height;
	random = new Random();

	tetrominoMaker = new TetrominoMaker();
	boardListeners = new ArrayList<BoardListener>();

	squares = new SquareType[this.width + PADDING*2][this.height + PADDING*2];

	primeBoard();
    }

    public void primeBoard(){
	for(int y = 0; y < this.height; y++){
	    for(int x = 0; x < this.width; x++) {
		squares[x][y] = SquareType.OUTSIDE;
	    }
	}

	for(int y = PADDING; y < height + PADDING; y++){
	    for(int x = PADDING; x < width + PADDING; x++) {
		squares[x][y] = SquareType.EMPTY;
	    }
	}
    }

    public void tick(){
	if(falling == null){
	    falling = tetrominoMaker.getPoly(random.nextInt(tetrominoMaker.getNumberOfTypes()));
	    fallingX = falling.getBlock().length == 2 ? (int) width/2 - 1 : (int)width/2 - 2;
	    fallingY = 0;
	    if(hasCollision()){
		falling = null;
	    }

	}else{
	    fall();
	}

	while(hasFullRow() != -1){
	    removeRow(hasFullRow());
	}

	notifyListeners();
    }
    /*
    public void removeRow(int row){
    	System.out.println("Begining...");
    	for(int y = row; y > height; y--){
    	    for(int x = PADDING; x < width + PADDING; x++){
    		squares[y][x] = squares[y + 1][x];
    	    }
    	}
    	System.out.println("Second...");
    	for(int x = PADDING; x < width; x++){
    	    squares[0][x] = SquareType.EMPTY;
    	}
    	System.out.println("Done...");
    	notifyListeners();
    }*/

    public void removeRow(int row){
	for(int x = PADDING; x < width + PADDING; x++){
	    for(int y = row + PADDING; y > PADDING; y--){
		squares[x][y] = squares[x][y - 1];
	    }
	}
	for(int x = PADDING; x < width + PADDING; x++){
	    squares[x][PADDING] = SquareType.EMPTY;
	}
	notifyListeners();
    }

    public int hasFullRow(){
	int c = 0;
	for(int y = 0; y < height; y++){
	    for(int x = 0;x < width; x++){
		if(getSquare(x,y) != SquareType.EMPTY){
		    c++;
		}
	    }
	    if(c == width){
		return y;
	    }else{
		c = 0;
	    }
	}
	return -1;
    }

    public void fall(){
	fallingY++;
	if(hasCollision()){
	    fallingY --;
	    addFallingToBoard();
	}
    }

    public void addFallingToBoard(){
	for(int y = 0; y < falling.getBlock()[0].length; y++){
	    for(int x = 0; x < falling.getBlock().length; x++){
		if(falling.getBlock()[x][y] != SquareType.EMPTY){
		    squares[fallingX + x + PADDING][fallingY + y + PADDING] = falling.getBlock()[x][y];
		}

	    }
	}
	falling = null;
    }

    public boolean hasCollision(){
	for (int y = 0; y < falling.getBlock()[0].length; y++){
	    for(int x = 0; x < falling.getBlock().length; x++){
		if (getSquare(fallingX + x, fallingY + y) != SquareType.EMPTY &&
			falling.getBlock()[x][y] != SquareType.EMPTY){
		    return true;

		}
	    }
	}
	return false;
    }

    public void moveFallingRight(){
	fallingX++;

	if(hasCollision()){
	    fallingX--;
	}

	notifyListeners();
    }

    public void moveFallingLeft(){
	fallingX--;

	if(hasCollision()){
	    fallingX++;
	}

	notifyListeners();
    }

    public void rotate(){
	falling = falling.rotateRight();
	while(hasCollision()){
	    if(fallingX < PADDING){
		fallingX++;
	    }else if(fallingX > width - falling.getBlock().length){
		fallingX--;
	    }else{
		fallingY--;
	    }
	}
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
	return squares[x + PADDING][y + PADDING];
    }

    public boolean isGameOver() {
	return gameOver;
    }
}
