import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private static final int SCORE_OF_1_ROWS = 100;
    private static final int SCORE_OF_2_ROWS = 300;
    private static final int SCORE_OF_3_ROWS = 500;
    private static final int SCORE_OF_4_ROWS = 800;
    private static final int PADDING = 2;

    private SquareType[][] squares;
    private int width, height;
    private int score;
    private Random random;

	private Poly falling;
	private int fallingX, fallingY;

	private TetrominoMaker tetrominoMaker;
	private List<BoardListener> boardListeners;

    private boolean gameOver;

    public Board(final int width, final int height) {
	this.width = width;
	this.height = height;
	score = 0;
	random = new Random();
	gameOver = false;
	falling = null;

	tetrominoMaker = new TetrominoMaker();
	boardListeners = new ArrayList<>();

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
	    fallingX = falling.getBlock().length == 2 ? width / 2 - 1 : width / 2 - 2;
	    fallingY = 0;
	    if(hasCollision()){
		falling = null;
		gameOver = true;

	    }

	}else{
	    fall();
	}
	int removedRows = 0;
	while(hasFullRow() != -1){
	    removeRow(hasFullRow());
	    removedRows++;
	}
	switch(removedRows){
	    case(1):
		score += SCORE_OF_1_ROWS;
		break;
	    case(2):
		score += SCORE_OF_2_ROWS;
		break;
	    case(3):
		score += SCORE_OF_3_ROWS;
		break;
	    case(4):
	    	score += SCORE_OF_4_ROWS;
		break;
	}

	notifyListeners();
    }

    public void removeRow(int row){

	for(int x = PADDING; x < width + PADDING; x++){
	    System.arraycopy(squares[x], PADDING, squares[x], PADDING + 1, row + PADDING - 2);
	}
	for(int x = PADDING; x < width + PADDING; x++){
	    squares[x][PADDING] = SquareType.EMPTY;
	}
	notifyListeners();
    }

    @SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion") public int hasFullRow(){
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
	notifyListeners();
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
	Poly tempPoly = falling;
	falling = falling.rotateRight();

	if(hasCollision()){
	    falling = tempPoly;
	}

	/*while(hasCollision()){
	    if(fallingX < PADDING){
		fallingX++;
	    }else if(fallingX > width - falling.getBlock().length){
		fallingX--;
	    }else{
		fallingY--;
	    }
	}*/



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

	public void resetBoard(){
		primeBoard();
		score = 0;
		gameOver = false;
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

    public int getScore() {
	return score;
    }
}
