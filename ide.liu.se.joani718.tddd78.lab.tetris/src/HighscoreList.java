import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HighscoreList {

    private static final HighscoreList INSTANCE = new HighscoreList();
    private List<Highscore> highscore;
    //TODO Learn about warning
    private ScoreComparator scoreComparator = new ScoreComparator();

    private HighscoreList() {
	highscore = new ArrayList<>();
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

    public void sort(){
	//TODO Learn about this
	Collections.sort(highscore, scoreComparator);
    }
}
