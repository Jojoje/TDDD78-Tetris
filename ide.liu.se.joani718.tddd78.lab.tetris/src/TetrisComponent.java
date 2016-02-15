import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class TetrisComponent extends JComponent implements BoardListener {
    final Board board;
    final int BLOCK_SIZE = 40;
    final int SPACE_BETWEEN_BLOCKS = 1;

    private static final Map<SquareType, Color> enumMap =
	    new EnumMap<SquareType,Color>(SquareType.class);


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

	int cornerX, cornerY;
	for(int y = 0; y < board.getHeight(); y++){
	    for(int x = 0; x < board.getWidth(); x++) {
		if(zoneOfFalling(x,y)){
		    g2d.setColor(enumMap.get(board.getFalling().getBlock()[x - board.getFallingX()][y - board.getFallingY()]));
		}else{
		    //System.out.println("Not in da zone...");
		    g2d.setColor(enumMap.get(board.getSquare(x,y)));
		}

		cornerX = x*BLOCK_SIZE + SPACE_BETWEEN_BLOCKS;
		cornerY = y*BLOCK_SIZE + SPACE_BETWEEN_BLOCKS;

		g2d.fillRect(cornerX, cornerY,
			     BLOCK_SIZE - SPACE_BETWEEN_BLOCKS,
			     BLOCK_SIZE - SPACE_BETWEEN_BLOCKS);


		g2d.setColor(Color.BLACK);
		g2d.drawLine(x*BLOCK_SIZE,y*BLOCK_SIZE, x*BLOCK_SIZE, (y+1)*BLOCK_SIZE);
		g2d.drawLine(x*BLOCK_SIZE,y*BLOCK_SIZE, (x+1)*BLOCK_SIZE, y*BLOCK_SIZE);
	    }
	}
    }

    public boolean zoneOfFalling(int x, int y){
	return ((board.getFalling() != null) &&
		((board.getFallingX() <= x && (board.getFallingX() + board.getFalling().getBlock().length) > x) &&
		(board.getFallingY() <= y && board.getFallingY() + board.getFalling().getBlock()[0].length > y) &&
		!(board.getFalling().getBlock()[1][1] == SquareType.EMPTY)));
    }

    @Override public Dimension getPreferredSize(){
	return new Dimension(board.getWidth()*BLOCK_SIZE + SPACE_BETWEEN_BLOCKS,
			     board.getHeight()*BLOCK_SIZE + SPACE_BETWEEN_BLOCKS);
    }

    @Override public void boardChanged() {
	repaint();
    }
}
