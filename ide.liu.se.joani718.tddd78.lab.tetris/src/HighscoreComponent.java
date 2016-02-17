import java.awt.*;

import javax.swing.*;

public class HighscoreComponent extends JComponent{
    public static final int FONT_SIZE = 20;
    private HighscoreList highscore;
    private Font font;
    private int width;
    private int height;

    public HighscoreComponent(HighscoreList highscore, int width, int height) {
	this.highscore = highscore;
	font = new Font("Times new roman", Font.BOLD, FONT_SIZE);
	this.width = width;
	this.height = height;
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	g2d.setFont(font);
	for(int y = 0; y < highscore.getHighscore().size(); y++){
	    String text = y + 1 + ". " + highscore.getHighscore().get(y).getName() + ": " +
			   highscore.getHighscore().get(y).getScore();
	    g2d.drawString(text, 100, y*(g2d.getFont().getSize() + 5) + font.getSize());
	}
    }

    @Override public Dimension getPreferredSize(){
    	return new Dimension((width*TetrisComponent.BLOCK_SIZE),
    			     (height*TetrisComponent.BLOCK_SIZE));
    }

}
