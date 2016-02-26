/**
 * Holds a 2D array of SquareTypes and represent a polyomino
 */
public class Poly {
    private SquareType[][] block;

    public Poly(SquareType[][] block) {
	this.block = block;

    }

    public int getSize(){
        return block.length;
    }

    public SquareType getSquareAt(int x, int y){
        return block[x][y];
    }

    public Poly rotateRight(){
    	int size = block.length;
        Poly newPoly = new Poly(new SquareType[size][size]);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++){
                newPoly.block[c][size-1-r] = this.block[r][c];
            }
        }

        return newPoly;
    }

    public SquareType[][] getBlock() {
	return block;
    }
}
