import javax.swing.*;
import java.awt.*;

public class TetrisFrame extends JFrame {
    private Board board;
    private JTextArea textArea;

    public TetrisFrame(Board board) {
	super("Jojoje's Tetris");
	this.board = board;

	textArea = new JTextArea(board.getHeight(), board.getWidth());
	textArea.setText(BoardToTextConverter.convertToText(board));

	this.setLayout(new BorderLayout());
	this.add(textArea, BorderLayout.CENTER);
	textArea.setFont(new Font("Courier New",Font.PLAIN,20));
	this.pack();
	this.setVisible(true);

    }

    public void updateFrame(){
	textArea.setText(BoardToTextConverter.convertToText(board));
	this.repaint();
    }


}
