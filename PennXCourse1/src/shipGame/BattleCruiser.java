package shipGame;

public class BattleCruiser extends Ship{

    public BattleCruiser(int bowRow, int bowColumn, boolean horizontal){
	super(bowRow, bowColumn, 7, horizontal);
    }
    
    public BattleCruiser(){
	super(- 1, -1, 7, false);
    }
    public String getShipType() {
	return "battlecruiser";
    }

}
