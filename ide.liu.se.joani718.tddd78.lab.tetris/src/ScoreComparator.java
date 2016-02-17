import java.util.Comparator;

public class ScoreComparator implements Comparator<Highscore>
{
    /** Compares its two arguments for order.
        Returns a negative integer, zero, or a positive integer
        as the first argument is less than, equal to, or greater than
        the second.  */
    public int compare(Highscore o1, Highscore o2) {
        if(o1.getScore() > o2.getScore()){
	    return -1;
	}else if(o1.getScore() < o2.getScore()){
	    return 1;
	}else{
	    return 0;
	}
    }
}