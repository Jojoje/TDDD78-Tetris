import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BoardTest {

    public static void main(String[] args){

	Board board = new Board(10,13);
	board.randomiseBoard();
	TetrisFrame frame = new TetrisFrame(board);

	final Action doOneStep = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {

	    }
	};

   	final Timer clockTimer = new Timer(1000, doOneStep);
   	clockTimer.setCoalesce(true);
   	clockTimer.start();
	//...
	clockTimer.stop();
    }
}
