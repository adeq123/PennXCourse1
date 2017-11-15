package shipGame;

import java.util.Random;

public class Ocean {

    private Ship[][] ships;
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;
    private Ship [] shipMatrix;
    public static final int MATRIX_SIZE = 20;
    public static final int NUMBER_OF_SHIPS = 13;

    public Ocean(){
	this.shotsFired = 0;
	this.hitCount = 0;
	this.shipsSunk = 0;
	ships = new Ship[MATRIX_SIZE][MATRIX_SIZE];
	shipMatrix = new Ship [NUMBER_OF_SHIPS];
	initShipArray();
    }

    /**
     * Place all randomly on the (initially empty) ocean. Place larger ships
     * before smaller ones, so that we do not end up with no legal place to 
     * put a large ship.
     * 
     * Vessels to put:
     * - one 8-square Battleship, 
     * - one 7-square Battlecruiser, 
     * - two 6-square Cruisers, 
     * - two 5-square Light Cruisers, 
     * - three 4-square Destroyers a
     * - four 3-square Submarines
     */
    public void placeAllShipsRandomly(){

	Random rnd = new Random ();
	boolean isPlaced = false;
	int rowRand;
	int colRand;
	boolean horiRand;
	shipMatrix[0] = new BattleShip();
	shipMatrix[1] = new BattleCruiser();
	shipMatrix[2] = new Cruiser();
	shipMatrix[3] = new Cruiser();
	shipMatrix[4] = new LightCruiser();
	shipMatrix[5] = new LightCruiser();
	shipMatrix[6] = new Destroyer();
	shipMatrix[7] = new Destroyer();
	shipMatrix[8] = new Destroyer();
	shipMatrix[9] = new Submarine();
	shipMatrix[10] = new Submarine();
	shipMatrix[11] = new Submarine();
	shipMatrix[12] = new Submarine();

	for(Ship oneShip : shipMatrix){
	    while(!isPlaced){
		rowRand = (int) (rnd.nextFloat() * (MATRIX_SIZE - 1));
		colRand = (int) (rnd.nextFloat() * (MATRIX_SIZE - 1));
		horiRand = rnd.nextBoolean();
		isPlaced = oneShip.okToPlaceShipAt(rowRand, colRand, horiRand, this);
		if(isPlaced){
		    oneShip.setBowColumn(colRand);
		    oneShip.setBowRow(rowRand);
		    oneShip.setHorizontal(horiRand);
		    oneShip.placeShipAt(rowRand, colRand, horiRand, this);
		}

	    }
	    //print();
	    isPlaced = false;
	}

    }
    /**
     *Initialize ship array with EmptySea
     */
    private void initShipArray() {
	for(int i = 0; i < MATRIX_SIZE; i++)
	    for(int j = 0; j < MATRIX_SIZE; j++)
		ships[i][j] = new EmptySea(i, j);

    }
    /**
     * returns true if the spot is occupied
     * @param row
     * @param column
     * @return
     */
    public boolean isOccupied(int row, int column){
	return !ships [row] [column].getShipType().equals(" ");
    }

    /**
     *  Returns true if the given location contains a ”real” ship,
     *  still afloat, (not an EmptySea), false if it does not. In 
     *  addition, this method updates the number of shots that 
     *  have been fired, and the number of hits. Note: If a 
     *  location contains a ”real” ship, shootAt should return 
     *  true every time the user shoots at that same location. 
     *  Once a ship has been ”sunk”, additional shots at its 
     *  location should return false.
     * @param row
     * @param column
     * @return
     */
    public boolean shootAt(int row, int column){
	boolean hit = ships[row][column].shootAt(row, column);
	this.shotsFired ++;
	if(hit){
	    this.hitCount++;
	    if(ships[row][column].isSunk())
		this.shipsSunk ++;
	}
	return hit;

    }

    public Ship[][] getShipArray(){
	return ships;
    }

    public int getShotsFired() {
	return shotsFired;
    }
    public int getHitCount() {
	return hitCount;
    }
    public int getShipsSunk() {
	return shipsSunk;
    }

    /**
     * RETURNS true if all ships are sunk
     * @return
     */
    public boolean isGameOver(){
	return this.shipsSunk == NUMBER_OF_SHIPS;
    }

    /**
     * Prints the ocean. To aid the user, row numbers should be displayed along 
     * the left edge of the array, and column numbers should be displayed along 
     * the top. Numbers should be 00 to 19, not 1 to 20.
     * The top left corner square should be 0, 0.Use ’S’ to indicate a location
     * that you have fired upon and hit a (real) ship, ’-’ to indicate a location
     * that you have fired upon and found nothing there, ’x’ to indicate a 
     * location containing a sunken ship, and ’.’ (a period) to indicate a 
     * location that you have never fired upon.This is the only method in 
     * the Ocean class that does any input/output, and it is never called 
     * from within the Ocean class (except possibly during debugging), only 
     * from the BattleshipGame class.
     */
    public void print(){
	printRows();
	for(int i = 0; i < ships.length; i++){
	    for(int j = 0; j < ships[0].length; j++){
		if(j == 0)
		    if(i < 10){
			System.out.print(i + "  ");
		    }else{
			System.out.print(i + " ");
		    }
		if(ships [i][j].isThisLocationHit(i, j, ships [i][j].isHorizontal())){
		    if(!ships [i][j].getShipType().equals(" ")){
			System.out.print(" " + ships [i][j] + " ");
		    }else{
			System.out.print(" - ");
		    }
		}else{
		    System.out.print(" . ");
		}
	    }
	    System.out.println();
	}
	System.out.println("  **********************Print compleeted**********************");
    }

    private void printRows() {
	System.out.println("  *************************New print*************************");
	System.out.print("   ");
	for(int i = 0; i < ships.length; i++){
	    if(i < 10){
		System.out.print(" " + i + " ");
	    }else{
		System.out.print(i + " ");
	    }
	}
	System.out.println();

    }

}
