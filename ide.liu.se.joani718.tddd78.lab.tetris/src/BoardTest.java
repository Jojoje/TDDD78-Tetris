import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BoardTest {

    static Board board;
    static TetrisFrame frame;

    public static void main(String[] args){

	board = new Board(10,13);
	board.randomiseBoard();
	frame = new TetrisFrame(board);

	final Action doOneStep = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
		board.randomiseBoard();

		frame.updateFrame();
	    }
	};

   	final Timer clockTimer = new Timer(1000, doOneStep);
   	clockTimer.setCoalesce(true);
   	clockTimer.start();
	//clockTimer.stop();
    }
}
