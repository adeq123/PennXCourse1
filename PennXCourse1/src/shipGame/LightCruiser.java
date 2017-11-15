package shipGame;

public class LightCruiser extends Ship{

    public LightCruiser(int bowRow, int bowColumn, boolean horizontal){

	super(bowRow, bowColumn, 5, horizontal);

    }

    public LightCruiser(){

	super(-1, -1, 5, false);

    }
    public String getShipType() {

	return "light cruiser";
    }

}
