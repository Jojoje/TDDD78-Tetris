public class Tetris {

    public static void main(String[] args) {
    	Board bord = new Board(15,30);
    	for(int y = 0; y < bord.getHeight(); y++){
    	    for(int x = 0; x < bord.getWidth(); x++){
    		System.out.print(bord.getSquares()[x][y]);
    	    }
	    System.out.println();
    	}

    }
}
