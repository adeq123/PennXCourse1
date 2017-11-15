package shipGame;

public class EmptySea extends Ship{

    public EmptySea(int bowRow, int bowColumn){

	super(bowRow, bowColumn, 1, true);

    }

    public EmptySea(int bowRow, int bowColumn, boolean horizontal){

	super(bowRow, bowColumn, 1, horizontal);

    }

    public EmptySea(){
	super();
    }

    public String getShipType() {
	return " ";
    }

    public String toString() {
	return " ";
    }

}
