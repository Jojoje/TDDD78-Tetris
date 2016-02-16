public class Poly {
    private SquareType[][] block;

    public Poly(SquareType[][] block) {
	this.block = block;

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
