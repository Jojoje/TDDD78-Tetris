import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
*Calculates and keep tracks on what is ont the tetris gameboard.
 */
public class Board {

    private static final int SCORE_OF_1_ROWS = 100;
    private static final int SCORE_OF_2_ROWS = 300;
    private static final int SCORE_OF_3_ROWS = 500;
    private static final int SCORE_OF_4_ROWS = 800;
    private static final int PADDING = 2;
    /**
     * 2/CHACNE_OF_POWERUP is the chance to get a powerup.
     */
    public static final int CHANCE_OF_POWERUP = 20;

    private SquareType[][] squares;
    private int width, height;
    private int score;
    private Random random;

    private Poly falling;
    private int fallingX, fallingY;

    private TetrominoMaker tetrominoMaker;
    private List<BoardListener> boardListeners;
    private CollisionHandler collisionHandler;

    private boolean gameOver;

    public Board(final int width, final int height) {
	this.width = width;
	this.height = height;
	score = 0;
	random = new Random();
	gameOver = false;
	falling = null;
	collisionHandler = new DefaultCollisionHandler();

	tetrominoMaker = new TetrominoMaker();
	boardListeners = new ArrayList<>();

	squares = new SquareType[this.width + PADDING*2][this.height + PADDING*2];

	primeBoard();
    }

    /**
     * Makes the board empty with a border.
     */
    public void primeBoard(){
	for(int y = 0; y < this.height + 2*PADDING; y++){
	    for(int x = 0; x < this.width + 2*PADDING; x++) {
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

	/**
	 * If no falling exist, creates a new one.
	 * Randomizes if the block should have a powerup, with the specefied chance
	 * for that falling.
	 */
	if(falling == null){
	    switch(random.nextInt(CHANCE_OF_POWERUP)){
		case(1):
		    collisionHandler = new Heavy();
		    break;
		case(0):
		    collisionHandler = new Fallthrough();
		    break;
		default:
		    if(collisionHandler.getClass() != DefaultCollisionHandler.class){
			collisionHandler = new DefaultCollisionHandler();
		    }
	    }

	    falling = tetrominoMaker.getPoly(random.nextInt(tetrominoMaker.getNumberOfTypes()));
	    fallingX = falling.getBlock().length == 2 ? width / 2 - 1 : width / 2 - 2;
	    fallingY = 0;
	    if(collisionHandler.hasCollision(this)){
		falling = null;
		gameOver = true;
	    }

	}else{
	    fall();
	}

	addScore();

	notifyListeners();
    }


    private void addScore(){
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
    }

    /**
     * Checks the row under the specified height if it has an empty block
     * @param row
     * @param fromHeight
     * @return boolean
     */
    public boolean canCollapsRow(int row, int fromHeight){
	for(int y = fromHeight; y < height; y++){
	    if(getSquare(row, y) == SquareType.EMPTY){
		return true;
	    }
	}
	return false;
    }


    public void collapsRow(int fromHeight, int row){
	int heighestEmpty = 0;
	for(int y = fromHeight; y < height; y++){
	    if(getSquare(row, y) == SquareType.EMPTY){
		heighestEmpty = y;
		break;
	    }
	}

	for(int y = heighestEmpty; y > fromHeight - 1; y--){
	    setSquare(row, y, getSquare(row, y - 1));
	}
	setSquare(row, fromHeight - 1, SquareType.EMPTY);

    }


    public void removeSquare(int x, int y){
	squares[x + PADDING][y + PADDING] = SquareType.EMPTY;
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
	if(collisionHandler.hasCollision(this)){
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


    public void moveFallingRight(){
	fallingX++;

	if(collisionHandler.hasCollision(this)){
	    fallingX--;
	}

	notifyListeners();
    }


    public void moveFallingLeft(){
	fallingX--;

	if(collisionHandler.hasCollision(this)){
	    fallingX++;
	}

	notifyListeners();
    }


    public void rotate(){
	Poly tempPoly = falling;
	falling = falling.rotateRight();

	if(collisionHandler.hasCollision(this)){
	    falling = tempPoly;
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


    public void resetBoard(){
		primeBoard();
	    	falling = null;
	    	fallingX = 0;
	    	fallingY = 0;
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


    public void setSquare(int x, int y, SquareType st) {
	squares[x + PADDING][y + PADDING] = st;
    }


    public boolean isGameOver() {
	return gameOver;
    }


    public int getScore() {
	return score;
    }


    public CollisionHandler getCollisionHandler() {
	return collisionHandler;
    }
}
