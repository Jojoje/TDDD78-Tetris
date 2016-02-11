import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisFrame_v1 extends JFrame{
    private Board board;
    private JTextArea textArea;

    private final int FONT_SIZE = 20;

    public TetrisFrame_v1(Board board) {
	super("Jojoje's Tetris");
	this.board = board;

	textArea = new JTextArea(board.getHeight(), board.getWidth());
	textArea.setText(BoardToTextConverter.convertToText(board));

	this.setLayout(new BorderLayout());

	createMenus();

	this.add(textArea, BorderLayout.CENTER);
	textArea.setFont(new Font("Courier New",Font.PLAIN,FONT_SIZE));
	this.pack();
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);

    }

    private void createMenus(){
	final JMenuBar bar = new JMenuBar();

	final JMenu options = new JMenu("Options");
	final JMenuItem quit = new JMenuItem("Exit");
	quit.addActionListener(new ExitListener());
	options.add(quit);
	bar.add(options);

	final JMenu help = new JMenu("Help");
	final JMenuItem howToPlay = new JMenuItem("How to play");
	howToPlay.addActionListener(new HowToPlayListener(this));
	help.add(howToPlay);
	bar.add(Box.createHorizontalGlue());
	bar.add(help);

	this.setJMenuBar(bar);
    }

    public void updateFrame(){
	textArea.setText(BoardToTextConverter.convertToText(board));
	this.repaint();
    }


    private class ExitListener implements ActionListener{
	public void actionPerformed(final ActionEvent e) {
	    System.exit(0);
    	}
    }

    private class HowToPlayListener implements ActionListener{
	private final JFrame frame;

	public HowToPlayListener(final JFrame frame){this.frame=frame;}

    	public void actionPerformed(final ActionEvent e) {
	    frame.setVisible(false);
	}
    }

}
