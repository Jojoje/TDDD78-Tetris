public class BoardTest {
    public static void main(String[] args){

	Board board = new Board(10,13);

	String text = BoardToTextConverter.convertToText(board);

	System.out.println(text);

	for(int i = 0; i < 10; i++){
	    board.randomiseBoard();
	}
	text = BoardToTextConverter.convertToText(board);
	System.out.println(text);

    }
}
