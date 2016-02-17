import java.awt.*;

import javax.swing.*;

public class HighscoreComponent extends JComponent{
    public static final int FONT_SIZE = 20;
    private HighscoreList highscore;
    private Font font;
    private int width;
    private int height;
    private int BLOCK_SIZE;

    public HighscoreComponent(HighscoreList highscore, int width, int height, int BLOCK_SIZE) {
	this.highscore = highscore;
	font = new Font("Times new roman", Font.BOLD, FONT_SIZE);
	this.width = width;
	this.height = height;
	this.BLOCK_SIZE = BLOCK_SIZE;
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	g2d.setFont(font);
	String print;
	for(int y = 0; y < highscore.getHighscore().size(); y++){
	    print = y+1 + ". " +highscore.getHighscore().get(y).getName() + ": " + highscore.getHighscore().get(y).getScore();
	    g2d.drawString(print, 100, y*(g2d.getFont().getSize() + 5) + font.getSize());
	}
    }

    @Override public Dimension getPreferredSize(){
    	return new Dimension((width*BLOCK_SIZE),
    			     (height*BLOCK_SIZE));
    }

}
