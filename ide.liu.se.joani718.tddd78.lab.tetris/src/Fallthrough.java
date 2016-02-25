/**
 * Checks collision with the bounderies of the board.
 * Also checks collision with other blocks if so removes them.
 */
public class Fallthrough implements CollisionHandler{
    /**
     * Checks if falling is colliding but only with outside blocks.
     * Then checks if it's colliding with anything on the board, if so removes that block.
     * @param board
     * @return boolean
     */
    public boolean hasCollision(Board board){
    	for (int y = 0; y < board.getFalling().getBlockSize(); y++){
    	    for(int x = 0; x < board.getFalling().getBlockSize(); x++){
    		if (board.getSquare(board.getFallingX() + x, board.getFallingY() + y) == SquareType.OUTSIDE &&
    		    board.getFalling().getSquareAt(x, y) != SquareType.EMPTY){
    		    return true;
    		}
    	    }
    	}
	for (int y = 0; y < board.getFalling().getBlockSize(); y++){
	    for(int x = 0; x < board.getFalling().getBlockSize(); x++){
	    	if(board.getSquare(board.getFallingX() + x, board.getFallingY() + y) != SquareType.EMPTY &&
			 board.getFalling().getSquareAt(x, y) != SquareType.EMPTY){
		    board.removeSquare(board.getFallingX() + x,board.getFallingY() + y);
		}
	    }
	}

    	return false;
    }

    @Override public String getDescription() {
	return "Remover";
    }
}
