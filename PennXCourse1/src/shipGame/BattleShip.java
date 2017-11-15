package shipGame;

public class BattleShip extends Ship{

    public BattleShip(int bowRow, int bowColumn, boolean horizontal){
	super(bowRow, bowColumn, 8, horizontal);
    }
    
    public BattleShip(){
	super(-1, -1, 8, false);
    }
    
    @Override
    public String getShipType() {
	return "battleship";
    }

}
