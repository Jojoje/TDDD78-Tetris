public class BoardToTextConverter {

    public static String convertToText(Board board){
	StringBuilder builder = new StringBuilder();

	for(int y = 0; y < board.getHeight(); y++){
	    for(int x = 0; x < board.getWidth(); x++){

		if ((board.getFalling() != null) &&
		    ((board.getFallingX() >= x && board.getFallingX() + board.getFalling().getBlock()[0].length <= x) &&
		    (board.getFallingY() >= y && board.getFallingY() + board.getFalling().getBlock().length <= y) &&
		    !board.getFalling().getBlock()[x][y].equals(SquareType.EMPTY))){

		    addToBuilder(board.getFalling().getBlock()[x][y], builder);
		}else{
		    addToBuilder(board.getSquare(x,y), builder);
		}

	    }
	    builder.append("\n");
	}
	String result = builder.toString();

	return result;
    }

    private static void addToBuilder(SquareType st, StringBuilder builder){
	switch(st) {
	    case EMPTY:
		builder.append("#");
		break;
	    case I:
		builder.append("I");
		break;
	    case O:
	    	builder.append("O");
		break;
	    case T:
		builder.append("T");
		break;
	    case S:
		builder.append("S");
		break;
	    case Z:
		builder.append("Z");
		break;
	    case J:
	    	builder.append("J");
	    	break;
	    case L:
		builder.append("L");
		break;
	    default:
		builder.append("#");
		break;
			}
    }
}
