import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EnumMap;
import java.util.Map;

/**
 *Paints all the blocks in the Tetrisgame.
 */
public class TetrisComponent extends JComponent implements BoardListener {
    /**
     * Font size of the tet on the screen
     */
    public static final int FONT_SIZE = 20;
    /**
     * Color of the lines seperating the squares
     */
    public static final Color LINE_COLOR = Color.BLACK;
    private final Board board;
    /**
     * The block Size of each square in the game.
     */
    public static final int BLOCK_SIZE = 40;
    private static final Map<SquareType, Color> ENUM_MAP = new EnumMap<>(SquareType.class);


    public TetrisComponent(final Board board) {
	this.board = board;
	ENUM_MAP.put(SquareType.I, Color.CYAN);
	ENUM_MAP.put(SquareType.S, Color.GREEN);
	ENUM_MAP.put(SquareType.J, Color.BLUE);
	ENUM_MAP.put(SquareType.L, Color.ORANGE);
	ENUM_MAP.put(SquareType.O, Color.YELLOW);
	ENUM_MAP.put(SquareType.T, Color.PINK);
	ENUM_MAP.put(SquareType.Z, Color.RED);
	ENUM_MAP.put(SquareType.EMPTY, Color.WHITE);

	setupKeyBinds();
    }

    public void setupKeyBinds(){
	InputMap keys = getInputMap(WHEN_IN_FOCUSED_WINDOW);
	keys.put(KeyStroke.getKeyStroke("RIGHT"),"Right");
	keys.put(KeyStroke.getKeyStroke("LEFT"), "Left");
	keys.put(KeyStroke.getKeyStroke("UP"), "Up");
	keys.put(KeyStroke.getKeyStroke("DOWN"), "Down");
	keys.put(KeyStroke.getKeyStroke("SPACE"), "Space");


	ActionMap act = getActionMap();
	act.put("Right", new RightAction());
	act.put("Left", new LeftAction());
	act.put("Up", new UpAction());
	act.put("Down", new DownAction());
	act.put("Space", new SpaceAction());
    }

    /**
     * HardDrop the falling tetromino.
     */
    private class SpaceAction extends AbstractAction{
    	@Override public void actionPerformed(final ActionEvent e) {
	    while(board.getFalling() != null){
		board.fall();
	    }
    	}
    }


    private class UpAction extends AbstractAction{
    	@Override public void actionPerformed(final ActionEvent e) {
    	    if(board.getFalling() != null){
		if(board.getFalling() != null){
		    board.rotate();
		}

    	    }

    	}
    }


    private class DownAction extends AbstractAction{
    	@Override public void actionPerformed(final ActionEvent e) {
	    if(board.getFalling() != null){
		board.fall();
	    }

    	}
    }


    private class RightAction extends AbstractAction{
	@Override public void actionPerformed(final ActionEvent e) {
	    if(board.getFalling() != null){
		board.moveFallingRight();
	    }

	}
    }

    private class LeftAction extends AbstractAction{
    	@Override public void actionPerformed(final ActionEvent e) {
	    if(board.getFalling() != null){
		board.moveFallingLeft();
	    }
    	}
    }

    @Override protected void paintComponent(Graphics g){
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	final int border = 1;
	for(int y = 0; y < board.getHeight(); y++){
	    for(int x = 0; x < board.getWidth(); x++) {
		if(zoneOfFalling(x,y)){
		    g2d.setColor(ENUM_MAP.get(board.getFalling().getBlock()[x - board.getFallingX()][y - board.getFallingY()]));
		}else{
		    g2d.setColor(ENUM_MAP.get(board.getSquare(x, y)));
		}

		int cornerX = x * BLOCK_SIZE + border;
		int cornerY = y * BLOCK_SIZE + border;

		g2d.fillRect(cornerX, cornerY, BLOCK_SIZE - border, BLOCK_SIZE - border);


		g2d.setColor(LINE_COLOR);
		g2d.drawLine(x*BLOCK_SIZE,y*BLOCK_SIZE, x*BLOCK_SIZE, (y+1)*BLOCK_SIZE);
		g2d.drawLine(x*BLOCK_SIZE,y*BLOCK_SIZE, (x+1)*BLOCK_SIZE, y*BLOCK_SIZE);


	    }
	}
	g2d.setFont(new Font("Times new roman", Font.BOLD, FONT_SIZE));
	g2d.drawString("Score " + Integer.toString(board.getScore()), 0, FONT_SIZE + 10);
	/*
	*This is a magic number i could get rid of if i knowed the width of the font but the Font.getSize()
	* only return the height. So without using monospace or somehow get the width i cant get around using a magic number.
	 */
	g2d.drawString(board.getCollisionHandler().getDescription(), board.getWidth() * BLOCK_SIZE - 110, FONT_SIZE + 10);
    }

    /**
     * Returns whether or not a falling Poly square that is not EMPTY
     * occopies a square on the board specifed by x,y.
     * @param x int
     * @param y int
     * @return boolean
     */
    private boolean zoneOfFalling(int x, int y){
	/**
	 * Return if the board has a falling and checks
	 * if the the current x and y is within the block and is not and empty block.
	 * Thus returns true if the game should paint from the falling poly  and false if the board
	 * sgould be used instead.
	 */
	return ((board.getFalling() != null) &&
		((board.getFallingX() <= x && (board.getFallingX() + board.getFalling().getBlock().length) > x) &&
		(board.getFallingY() <= y && board.getFallingY() + board.getFalling().getBlock()[0].length > y) &&
		(board.getFalling().getBlock()[x - board.getFallingX()][y - board.getFallingY()] != SquareType.EMPTY)));
    }

    @Override public Dimension getPreferredSize(){
	return new Dimension((board.getWidth())*BLOCK_SIZE,
			     (board.getHeight())*BLOCK_SIZE);
    }

    @Override public void boardChanged() {
	repaint();
    }

}
