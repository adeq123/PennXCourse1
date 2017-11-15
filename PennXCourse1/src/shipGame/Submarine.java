package shipGame;

public class Submarine extends Ship{

    public Submarine(int bowRow, int bowColumn, boolean horizontal){

	super(bowRow, bowColumn, 3, horizontal);

    }
    
    public Submarine(){

	super(-1, -1, 3, false);

    }
    
    public String getShipType() {
	return "submarine";
    }
}
