package shipGame;

public class Destroyer extends Ship{

    public Destroyer(int bowRow, int bowColumn, boolean horizontal){

	super(bowRow, bowColumn, 4, horizontal);

    }
    
    public Destroyer(){

	super(-1, -1, 4, false);

    }
    public String getShipType() {
	return "destroyer";
    }

}
