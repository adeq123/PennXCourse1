package shipGame;

public class Cruiser extends Ship{

    public Cruiser(int bowRow, int bowColumn, boolean horizontal){
	super(bowRow, bowColumn, 6, horizontal);
    }
    
    public Cruiser(){
	super(-1, -1, 6, false);
    }
    public String getShipType() {
	return "cruiser";
    }

}
