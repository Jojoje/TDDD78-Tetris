import javax.swing.Timer;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

/**
 * The main class for the TetrisProgram,
 * creates a Board and a TetrisFrame, and a Timer.
 * Each time the Timer does an action the board updates.
 */
public final class BoardTest {

    private static final int TIME_BETWEEN_TICKS = 500;

    /**
     *Game Timer, reason for public is to be
     * able to stop and start Timer from within the game. e.a pause.
     * clockTimer is created within the main method and not the constructor because the class should not be instanced.
     */
    public static Timer clockTimer;
    private static final int ROW = 15;
    private static final int COLUMMN = 10;


    private BoardTest() {}

    public static void main(String[] args){

	Board board = new Board(COLUMMN,ROW);
	TetrisFrame frame = new TetrisFrame(board);
	HighscoreList highscore = HighscoreList.getInstance();


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
