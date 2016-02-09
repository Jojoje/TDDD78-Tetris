public class TetrominoMaker {

    public int getNumberOfTypes() {

    }

    public Poly getPoly(int n) {
	Poly poly = createPoly(SquareType.values()[n]);
	return poly;
    }

    public Poly createPoly(SquareType ST){
	int witdh, height;

	if(ST == SquareType.I){
	    Poly poly = new Poly(4,4);
	}
	else if(ST == SquareType.S || ST == SquareType.T || ST == SquareType.Z || ST == SquareType.J || ST == SquareType.L){
	    Poly poly = new Poly(3,3);
	}
	else if(ST == SquareType.O){
	    Poly poly = new Poly(2,2);
	}


	switch(ST){
	    case I:
		Poly poly = new Poly(4,4);
		poly.

	}

	return poly;
    }
}
