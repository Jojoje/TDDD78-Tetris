import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * JFrame that contains:
 * menubar,
 * a tetrisComponent or a HighscoreComponent
 */
public class TetrisFrame extends JFrame{
    private Board board;
    private TetrisComponent tetrisComponent;
    private HighscoreComponent highscoreComponent;

    public TetrisFrame(Board board) {
	super("Jojoje's Tetris");
	this.board = board;
	this.setLayout(new BorderLayout());
	createMenus();

	tetrisComponent = new TetrisComponent(board);
	board.addBoardListener(tetrisComponent);
	this.add(tetrisComponent);

	this.pack();
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	this.setVisible(true);

    }

    private void createMenus(){
	final JMenuBar menuBar = new JMenuBar();

	final JMenu options = new JMenu("Options");
	final JMenuItem reset = new JMenuItem("Reset");
	final JMenuItem quit = new JMenuItem("Exit");
	reset.addActionListener(new ResetListener());
	quit.addActionListener(new ExitListener());
	options.add(reset);
	options.add(quit);
	menuBar.add(options);

	final JMenu help = new JMenu("Help");
	final JMenuItem howToPlay = new JMenuItem("How to play");
	howToPlay.addActionListener(new HowToPlayListener(this));
	help.add(howToPlay);
	menuBar.add(Box.createHorizontalGlue());
	menuBar.add(help);

	this.setJMenuBar(menuBar);
    }

    private class ExitListener implements ActionListener{
	public void actionPerformed(final ActionEvent e) {
	    System.exit(0);
    	}
    }

    private class ResetListener implements ActionListener{
    	public void actionPerformed(final ActionEvent e) {
    	    resetGame();
	}
    }

    private final class HowToPlayListener implements ActionListener{
	private final JFrame frame;

	private HowToPlayListener(final JFrame frame){this.frame=frame;}

    	public void actionPerformed(final ActionEvent e) {
	    frame.setVisible(false);
	}
    }

    public void showHighscore(HighscoreList highscore){
	highscoreComponent = new HighscoreComponent(highscore, board.getWidth(),board.getHeight());
	this.remove(tetrisComponent);
	this.add(highscoreComponent);
	this.pack();

    }

    public void resetGame(){
	this.remove(highscoreComponent);
	board.resetBoard();
	tetrisComponent = new TetrisComponent(board);
	board.addBoardListener(tetrisComponent);
	this.add(tetrisComponent);
	this.pack();
	BoardTest.clockTimer.start();
    }

}
