import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class TetrisComponent extends JComponent {
    final Board board;
    final int BLOCK_WITDH = 30;
    final int BLOCK_HEIGHT = 30;
    final int SPACE_BETWEEN_BLOCKS = 10;

    private static final Map<SquareType, java.awt.Color> enumMap =
	    new EnumMap<SquareType,java.awt.Color>(SquareType.class);


    public TetrisComponent(final Board board) {
	this.board = board;
	enumMap.put(SquareType.I, Color.CYAN);
	enumMap.put(SquareType.S, Color.GREEN);
	enumMap.put(SquareType.J, Color.BLUE);
	enumMap.put(SquareType.L, Color.ORANGE);
	enumMap.put(SquareType.O, Color.YELLOW);
	enumMap.put(SquareType.T, Color.PINK);
	enumMap.put(SquareType.Z, Color.RED);
	enumMap.put(SquareType.EMPTY, Color.WHITE);
    }

    @Override protected void paintComponent(Graphics g){
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	g2d.setBackground(Color.BLACK);

	for(int y = 0; y < board.getHeight(); y++){
	    for(int x = 0; x < board.getWidth(); x++) {
		g2d.setColor(enumMap.get(board.getSquare(x,y)));
		g2d.fillRect(x*BLOCK_WITDH + SPACE_BETWEEN_BLOCKS,y*BLOCK_HEIGHT + SPACE_BETWEEN_BLOCKS,
			     (x+1)*BLOCK_WITDH - SPACE_BETWEEN_BLOCKS,(y+1)*BLOCK_HEIGHT - SPACE_BETWEEN_BLOCKS);
	    }
	}
    }

    @Override public Dimension getPreferredSize(){
	return new Dimension(board.getWidth()*(BLOCK_WITDH) + SPACE_BETWEEN_BLOCKS,
			     board.getHeight()*(BLOCK_HEIGHT) + SPACE_BETWEEN_BLOCKS);
    }

}
