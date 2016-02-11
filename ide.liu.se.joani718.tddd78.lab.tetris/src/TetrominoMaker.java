public class TetrominoMaker {

    public int getNumberOfTypes() {
	return SquareType.values().length;
    }

    public Poly getPoly(int n) {
	Poly poly = createPoly(SquareType.values()[n]);
	return poly;
    }

    public Poly createPoly(SquareType st){

	switch(st){
	    case I:
		return createIBlock();
	    case S:
		return createSBlock();
	    case T:
		return createTBlock();
	    case Z:
		return createZBlock();
	    case J:
		return createJBlock();
	    case L:
		return createLBlock();
	    case O:
		return createOBlock();
	    default:
		return null;
	}

    }

    public Poly createIBlock(){
	SquareType[][] block = new SquareType[4][4];
	fillWithEmpty(block);
	for(int i = 0; i < 4; i++ ){
	    block[i][1] = SquareType.I;
	}
	Poly poly = new Poly(block);
	return poly;
    }

    public Poly createSBlock(){
	SquareType[][] block = new SquareType[3][3];
	fillWithEmpty(block);

	block[1][0] = SquareType.S;
	block[2][0] = SquareType.S;
	block[0][1] = SquareType.S;
	block[1][1] = SquareType.S;

	Poly poly = new Poly(block);
	return poly;
    }

    public Poly createTBlock(){
    	SquareType[][] block = new SquareType[3][3];
    	fillWithEmpty(block);

    	block[1][0] = SquareType.T;
    	block[0][1] = SquareType.T;
    	block[1][1] = SquareType.T;
    	block[2][1] = SquareType.T;

    	Poly poly = new Poly(block);
    	return poly;
    }

    public Poly createZBlock(){
    	SquareType[][] block = new SquareType[3][3];
    	fillWithEmpty(block);

    	block[0][0] = SquareType.Z;
    	block[1][0] = SquareType.Z;
    	block[1][1] = SquareType.Z;
    	block[2][1] = SquareType.Z;

    	Poly poly = new Poly(block);
    	return poly;
    }

    public Poly createJBlock(){
    	SquareType[][] block = new SquareType[3][3];
    	fillWithEmpty(block);

    	block[0][0] = SquareType.J;
    	block[0][1] = SquareType.J;
    	block[1][1] = SquareType.J;
    	block[2][1] = SquareType.J;

    	Poly poly = new Poly(block);
    	return poly;
    }

    public Poly createLBlock(){
    	SquareType[][] block = new SquareType[3][3];
    	fillWithEmpty(block);

    	block[2][0] = SquareType.L;
    	block[0][1] = SquareType.L;
    	block[1][1] = SquareType.L;
    	block[2][1] = SquareType.L;

    	Poly poly = new Poly(block);
    	return poly;
    }

    public Poly createOBlock(){
    	SquareType[][] block = new SquareType[2][2];

    	block[0][0] = SquareType.O;
    	block[0][1] = SquareType.O;
    	block[1][0] = SquareType.O;
    	block[1][1] = SquareType.O;

    	Poly poly = new Poly(block);
    	return poly;
    }





    public void fillWithEmpty(SquareType[][] block){
	for(int y = 0; y < block.length; y++){
	    for(int x = 0; x < block[y].length; x++) {
		block[x][y] = SquareType.EMPTY;
	    }
	}
    }
}
