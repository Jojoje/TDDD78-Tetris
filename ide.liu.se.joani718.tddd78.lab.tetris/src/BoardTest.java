import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoardTest {

    public static final int TIME_BETWEEN_TICKS = 500;
    public static Board board;
    private static TetrisFrame frame;
    private static HighscoreList highscore;
    private static final int ROW = 15;
    private static final int COLUMMN = 10;
    public static Timer clockTimer;

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
