import java.util.ArrayList;
import java.util.List;

public class HighscoreList {

    private static final HighscoreList INSTANCE = new HighscoreList();
    private List<Highscore> highscore;

    private HighscoreList() {
	highscore = new ArrayList<Highscore>();
    }

    public static HighscoreList getInstance() {
        return INSTANCE;
    }

    public void addHighscore(String name, int score ){
	highscore.add(new Highscore(score, name));
    }

    public List<Highscore> getHighscore() {
	return highscore;
    }
}
