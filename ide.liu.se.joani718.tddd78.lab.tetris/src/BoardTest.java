import javax.swing.*;
import java.awt.event.ActionEvent;

public final class BoardTest {

    private static final int TIME_BETWEEN_TICKS = 500;
    private static Board board;
    private static TetrisFrame frame;
    private static HighscoreList highscore;
    private static final int ROW = 15;
    private static final int COLUMMN = 10;
    /**
    *Game Timer, reason for public is to be
    * able to stop and start Timer from the game.
     */
    public static Timer clockTimer;

    private BoardTest() {}

    public static void main(String[] args){

	board = new Board(COLUMMN,ROW);
	frame = new TetrisFrame(board);
	highscore = HighscoreList.getInstance();


	final Action doOneStep = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
		if(!board.isGameOver()){
		    board.tick();
		}
		else{
		    String input = JOptionPane.showInputDialog("Please input a name");
		    highscore.addHighscore(input, board.getScore());
		    highscore.sort();
		    frame.showHighscore(highscore);
		    clockTimer.stop();
		}

	    }
	};

   	clockTimer = new Timer(TIME_BETWEEN_TICKS, doOneStep);
   	clockTimer.setCoalesce(true);
   	clockTimer.start();
    }

}
