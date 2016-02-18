public class DefaultCollisionHandler implements CollisionHandler{
    public boolean hasCollision(Board board){
	for (int y = 0; y < board.getFalling().getBlockSize(); y++){
	    for(int x = 0; x < board.getFalling().getBlockSize(); x++){
		if (board.getSquare(board.getFallingX() + x, board.getFallingY() + y) != SquareType.EMPTY &&
		    board.getFalling().getSquareAt(x, y) != SquareType.EMPTY){
		    return true;
		}
	    }
	}
	return false;
    }

    @Override public String getDescription() {
	return "Standard";
    }
}
