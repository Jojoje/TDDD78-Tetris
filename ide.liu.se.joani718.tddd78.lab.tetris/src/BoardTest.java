import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoardTest {

    private static Board board;
    private static TetrisFrame frame;
    private static final int ROW = 13;
    private static final int COLUMMN = 10;

    public static void main(String[] args){

	board = new Board(COLUMMN,ROW);
	frame = new TetrisFrame(board);

	final Action doOneStep = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {


	    }
	};

   	final Timer clockTimer = new Timer(1000, doOneStep);
   	clockTimer.setCoalesce(true);
   	clockTimer.start();
	//clockTimer.stop();
    }
}
