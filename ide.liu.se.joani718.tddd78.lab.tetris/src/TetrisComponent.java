import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class TetrisComponent extends JComponent {
    final Board board;
    final int BLOCK_WITDH = 30;
    final int BLOCK_HEIGHT = 30;

    public TetrisComponent(final Board board) {
	this.board = board;
    }

    @Override protected void paintComponent(Graphics g){
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
    }

    @Override public Dimension getPreferredSize(){
	return new Dimension(board.getWidth()*BLOCK_WITDH, board.getHeight()*BLOCK_HEIGHT);
    }
}
