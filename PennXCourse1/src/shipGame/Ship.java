package shipGame;
/**
 * The class describes generic ship. 
 * Bow coordinates describes the most left piece of the ship if horizontal or the most bottom piece of the ship if vertical
 * 
 * @author Adrian Roguski
 *
 */
public abstract class Ship {

    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean [] hit;

    


    public Ship(int bowRow, int bowColumn, int length, boolean horizontal){
	this.length = length;
	this.bowRow = bowRow;
	this.bowColumn = bowColumn;
	this.horizontal = horizontal;
	this.hit = new boolean [8];
	initHit();
    }
    
    private void initHit() {
	for(int i = 0; i < length; i++)
	    hit [i] = false;
	
    }

    public Ship(){
	this.length = 1;
	this.bowRow = -1;
	this.bowColumn = -1;
	this.horizontal = true;
	initHit();
	
    }
    
    
    public abstract String getShipType();

    /**
     * Returns true if it is okay to put a ship of this length with its bow 
     * in this location, with the given orientation, and returns false 
     * otherwise. The ship must not overlap another ship, or touch another
     * ship (vertically, horizontally, or diagonally), and it must not ”stick
     * out” beyond the array. Do not actually change either the ship 
     * or the Ocean, just says whether it is legal to do so.
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     * @return Returns true if it is okay to put a ship of this length with its bow 
     * in this location, with the given orientation, and returns false 
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
	if(!checkIfShipFitsMap(row, column, horizontal, ocean))
	    return false;
	if(!checkIfShipTouchesShip(row, column, horizontal, ocean))
	    return false;
	return true;
    }
    /**
     * The method tells you whether the given location was hit before or not. 
     * @param row
     * @param column
     * @param horizontal
     * @return, true if the location is hit, false otherwise
     */
    public boolean isThisLocationHit (int row, int column, boolean horizontal){
    		if(isSunk())    
    		    return true;
    		if(horizontal){
    		    if(row == this.bowRow && column <= this.bowColumn && column > (this.bowColumn - length)){
    			return hit [this.bowColumn - column];
    			
    		    }
    		}else{
    		    if(column == this.bowColumn && row <= this.bowRow && row > (this.bowRow - length)){
    			return hit [this.bowRow - row];	
    		    }
    		}
    		return false;

    	    }
 
    /**
     * The method checks whether the ships are not overlapping or touches each other 
     * for a given bow coordinates length, position;
     * @return true ships does not touch, false otherwise
     */
    public boolean checkIfShipTouchesShip(int row, int column, boolean horizontal, Ocean ocean) {
	if(horizontal){
	    for(int i = column; i > column - length; i --){
		if(!ocean.getShipArray()[row][i].getShipType().equals(" "))//check if ships are not overlapping
		    return false;
		if(row - 1 >= 0){//check if ship is not touching other ship above
		    if(!ocean.getShipArray()[row - 1][i].getShipType().equals(" "))
			return false;
		}
		if(row + 1 <= (ocean.getShipArray().length - 1)){//check if ship is not touching other ship below
		    if(!ocean.getShipArray()[row + 1][i].getShipType().equals(" "))
			return false;
		}

	    }

	    if(column - length>= 0){ //check if ship is not touching other ship on the left
		if(!ocean.getShipArray()[row][column - length].getShipType().equals(" ")) // parallel to the ship
		    return false;
		if(row - 1 >= 0) // diagonal up
		    if(!ocean.getShipArray()[row - 1][column - length].getShipType().equals(" "))
			return false;
		if(row + 1 <= (ocean.getShipArray().length - 1) ) // diagonal down
		    if(!ocean.getShipArray()[row + 1][column - length].getShipType().equals(" "))
			return false;
	    }

	    if(column + 1 <= (ocean.getShipArray()[0].length - 1)){ //check if ship is not touching other ship on the right
		if(!ocean.getShipArray()[row][column + 1].getShipType().equals(" ")) // parallel to the ship
		    return false;
		if(row - 1 >= 0) // diagonal up
		    if(!ocean.getShipArray()[row - 1][column + 1].getShipType().equals(" "))
			return false;
		if(row + 1 <= (ocean.getShipArray().length - 1) ) // diagonal down
		    if(!ocean.getShipArray()[row + 1][column + 1].getShipType().equals(" "))
			return false;
	    }
	}else { // vertical
	    for(int i = row; i > row - length; i --){
		if(!ocean.getShipArray()[i][column].getShipType().equals(" "))//check if ships are not overlapping
		    return false;
		if(column - 1 >= 0){//check if ship is not touching other ship to the left
		    if(!ocean.getShipArray()[i][column - 1].getShipType().equals(" "))
			return false;
		}
		if(column + 1 <= (ocean.getShipArray()[0].length - 1)){//check if ship is not touching other to the right
		    if(!ocean.getShipArray()[i][column + 1].getShipType().equals(" "))
			return false;
		}

	    }

	    if(row - length >= 0){ //check if ship is not touching other ship on the top
		if(!ocean.getShipArray()[row - length ][column].getShipType().equals(" ")) // parallel to the ship
		    return false;
		if(column - 1 >= 0) // diagonal up
		    if(!ocean.getShipArray()[row - length ][column - 1].getShipType().equals(" "))
			return false;
		if(column + 1 <= (ocean.getShipArray()[0].length - 1) ) // diagonal down
		    if(!ocean.getShipArray()[row - length ][column + 1].getShipType().equals(" "))
			return false;
	    }

	    if(row + 1 <= (ocean.getShipArray().length - 1)){ //check if ship is not touching other ship on the bottom
		if(!ocean.getShipArray()[row + 1][column].getShipType().equals(" ")) // parallel to the ship
		    return false;
		if(column - 1 >= 0) // diagonal up
		    if(!ocean.getShipArray()[row + 1][column - 1].getShipType().equals(" "))
			return false;
		if(column + 1 <= (ocean.getShipArray().length - 1) ) // diagonal down
		    if(!ocean.getShipArray()[row + 1][column + 1].getShipType().equals(" "))
			return false;
	    }

	}
	return true;
    }
    /**
     * The method checks if the ship will fit the map
     * @param row
     * @param column
     * @param horizontal2
     * @param ocean
     * @return true if ship fits
     */
    public boolean checkIfShipFitsMap(int row, int column, boolean horizontal2,
	    Ocean ocean) {
	if(horizontal2){ // horizontal - bow is the most right element
	    if(row < 0 || row > (ocean.getShipArray().length - 1) 
		    || (column - length + 1) < 0 || column > (ocean.getShipArray()[0].length - 1) ) 
		return false;


	}else if((row - length + 1) < 0 || row > (ocean.getShipArray().length - 1) 
		|| column  < 0 || column > (ocean.getShipArray()[0].length - 1) ){ 
	    return false;
	}
	return true;

    }
    /**
     * ”Puts” the ship in the ocean. This involves giving values to the 
     * bowRow, bowColumn, and horizontal instance variables in the ship, 
     * and it also involves putting a reference to the ship in each of 1 
     * or more locations (up to 8) in the ships array in the Ocean object. 
     * (Note: This will be as many as eight identical references; you 
     * can’t refer to a ”part” of a ship, only to the whole ship.)
     * 
     * It assumes that the coordinates has been already checked with 
     * okToPlaceShipAt() so we know it is legal to do it
     * 
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){
/*
 * Change this one to have a new instance in each field
 */
    	Ship oneShip = this;
	if(horizontal){
	    for(int i = column; i > column - length;  i--){/*
	    will not work since hit matrix is shared within one instance
	    	if(ocean.getShipArray()[row][i].getShipType().equals("battleship")){
	    		oneShip = new BattleShip(ocean.getShipArray()[row][i].bowRow, 
	    				ocean.getShipArray()[row][i].getBowColumn(),
	    				ocean.getShipArray()[row][i].horizontal);
	    	}else if(ocean.getShipArray()[row][i].getShipType().equals("battlecruiser")){
	    		oneShip = new BattleCruiser(ocean.getShipArray()[row][i].bowRow, 
	    				ocean.getShipArray()[row][i].getBowColumn(),
	    				ocean.getShipArray()[row][i].horizontal);
	    	}else if(ocean.getShipArray()[row][i].getShipType().equals("cruiser")){
	    		oneShip = new Cruiser(ocean.getShipArray()[row][i].bowRow, 
	    				ocean.getShipArray()[row][i].getBowColumn(),
	    				ocean.getShipArray()[row][i].horizontal);
	    	}else if(ocean.getShipArray()[row][i].getShipType().equals("destroyer")){
	    		oneShip = new Destroyer(ocean.getShipArray()[row][i].bowRow, 
	    				ocean.getShipArray()[row][i].getBowColumn(),
	    				ocean.getShipArray()[row][i].horizontal);
	    	}else if(ocean.getShipArray()[row][i].getShipType().equals("lightcruiser")){
	    		oneShip = new LightCruiser(ocean.getShipArray()[row][i].bowRow, 
	    				ocean.getShipArray()[row][i].getBowColumn(),
	    				ocean.getShipArray()[row][i].horizontal);
	    	}else if(ocean.getShipArray()[row][i].getShipType().equals("submarine")){
	    		oneShip = new Submarine(ocean.getShipArray()[row][i].bowRow, 
	    				ocean.getShipArray()[row][i].getBowColumn(),
	    				ocean.getShipArray()[row][i].horizontal);
	    	}else{
	    		oneShip = new EmptySea(ocean.getShipArray()[row][i].bowRow, 
	    				ocean.getShipArray()[row][i].getBowColumn());
	    	}
	    	*/
	    	ocean.getShipArray()[row][i] = oneShip;
	    }
	}else{//vertical
	    for(int i = row; i > row - length;  i--)
		ocean.getShipArray()[i][column] = this;
	}
    }

    /**
     * If a part of the ship occupies the given row and column, and 
     * the ship hasn’t been sunk, mark that part of the ship as ”hit” 
     * (in the hit array, 0 indicates the bow) and return true, 
     * otherwise return false.
     * 
     * It treats double shot at the same spot as normal shot (no special handling)
     * @param row
     * @param column
     * @return
     */
    public boolean shootAt(int row, int column){
	
	if(isSunk())    
	    return false;
	if(getShipType().equals(" ")){
	    hit [0] = true;
	    return false;
	}
	    
	if(horizontal){
	    if(row == this.bowRow && column <= this.bowColumn && column > (this.bowColumn - length)){
		hit [this.bowColumn - column] = true;
		return true;
	    }
	}else{
	    if(column == this.bowColumn && row <= this.bowRow && row > (this.bowRow - length)){
		hit [this.bowRow - row] = true;
		return true;	
	    }
	}
	return false;

    }
    /**
     * Return true if every part of the ship has been hit,
     * false otherwise.
     * @return
     */
    public boolean isSunk(){
	boolean oneHit;
    	if(this.getShipType().equals(" "))
    		return false;
	for(int i = 0; i < this.length; i++){
	    oneHit = hit [i];
	    if(!oneHit)
		return false;
	}
	return true;

    }

    /**
     * Returns a single-character String to use in the Ocean’s 
     * print method (see below). This method should return ”x”
     * if the ship has been sunk, ”S” if it has not been sunk.
     * This method can be used to print out locations in the
     * ocean that have been shot at; it should not be used
     * to print locations that have not been shot at.Since
     * toString behaves exactly the same for all ship types,
     * it can be moved into the Ship class.
     * Note that the toString method for the EmptySea class
     * has to override the Ship class's implementation. In
     * order to figure out what needs to be done, please see
     * the description of the print method in the Ocean class.
     */
    public String toString(){
	if(isSunk())
	    return "x";
	return "S";

    }

    public int getBowRow() {
	return bowRow;
    }
    public void setBowRow(int bowRow) {
	this.bowRow = bowRow;
    }
    public int getBowColumn() {
	return bowColumn;
    }
    public void setBowColumn(int bowColumn) {
	this.bowColumn = bowColumn;
    }
    public int getLength() {
	return length;
    }
    public void setLength(int length) {
	this.length = length;
    }
    public boolean isHorizontal() {
	return horizontal;
    }
    public void setHorizontal(boolean horizontal) {
	this.horizontal = horizontal;
    }
    public boolean[] getHit() {
	return hit;
    }
    public void setHit(boolean[] hit) {
	this.hit = hit;
    }



}