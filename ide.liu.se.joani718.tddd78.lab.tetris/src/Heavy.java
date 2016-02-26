import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Heavy implements CollisionHandler{
    @Override public boolean hasCollision(final Board board) {
	boolean stuckCollision = false;
	List<Integer> rowsToCollaps = new ArrayList<>();
	List<Integer> fromHeight = new ArrayList<>();

	for (int y = 0; y < board.getFalling().getSize(); y++){
	    for(int x = 0; x < board.getFalling().getSize(); x++){
		if (board.getSquare(board.getFallingX() + x, board.getFallingY() + y) != SquareType.EMPTY &&
		    board.getFalling().getSquareAt(x, y) != SquareType.EMPTY){
		    if(board.canCollapsRow(board.getFallingX() + x, board.getFallingY() + y + 1)){ //&& !board.isEmptyRow(x)){
			rowsToCollaps.add(board.getFallingX() + x);
			fromHeight.add(board.getFallingY() + y + 1);
		    }else{
			stuckCollision = true;
		    }
		}
	    }
	}


	if(stuckCollision){
	    return true;
	}else{
	    for(int i = 0; i < rowsToCollaps.size(); i++){
		board.collapsRow(fromHeight.get(i), rowsToCollaps.get(i));
	    }
	    return false;
	}
    }

    @Override public String getDescription() {
	return "Heavy";
    }
}
