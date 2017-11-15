package shipGameJUnit;

import shipGame.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {
    
    Ocean oc1;
    Ship [][] shipArray;
    BattleShip bc;
    BattleCruiser bcr;
    Submarine sbH;
    Submarine sbV;
    @Before
    public void setUp() throws Exception {
	oc1 = new Ocean();
	shipArray = oc1.getShipArray();
	bc = new BattleShip(7, 19, false);
	bcr = new BattleCruiser (10, 10, true);
	sbH = new Submarine(10, 3, true);
	sbV = new Submarine(10, 19, false);
	
    }

    @Test
    public void testPlaceShipAt() {
	//first test with vertical battleship
	bc.placeShipAt(bc.getBowRow(), bc.getBowColumn(), bc.isHorizontal(), oc1);
	for (int row = bc.getBowRow(); row > bc.getBowRow() - bc.getLength(); row --)
	    assertTrue(shipArray[row][bc.getBowColumn()].getShipType().equals("battleship"));
	//second test with horizontal battle cruiser
	bcr.placeShipAt(bcr.getBowRow(), bcr.getBowColumn(), bcr.isHorizontal(), oc1);
	for(int column = bcr.getBowColumn(); column > bcr.getBowColumn() - bcr.getLength(); column --)
	    assertTrue(shipArray[bcr.getBowRow()][column].getShipType().equals("battlecruiser"));
	
    }
    @Test
    public void testCheckIfShipFitsMap() {
	//first test with vertical battleship
	assertTrue(bc.checkIfShipFitsMap(bc.getBowRow(), bc.getBowColumn(), bc.isHorizontal(), oc1));
	//second test with horizontal battle cruiser
	assertTrue(bcr.checkIfShipFitsMap(bcr.getBowRow(), bcr.getBowColumn(), bcr.isHorizontal(), oc1));
	//sticking out from the board on the top vertical battleship
	assertFalse(bc.checkIfShipFitsMap(bc.getBowRow() - 1, bc.getBowColumn(), bc.isHorizontal(), oc1));
	//sticking out from the board on the bottom - vertical battleship
	assertFalse(bc.checkIfShipFitsMap(20, bc.getBowColumn(), bc.isHorizontal(), oc1));
	//sticking out to the right - horizontal cruiser
	assertFalse(bcr.checkIfShipFitsMap(bcr.getBowRow(), 5, bcr.isHorizontal(), oc1));
	//sticking out to the left - horizontal cruiser
	assertFalse(bcr.checkIfShipFitsMap(bcr.getBowRow(), 20, bcr.isHorizontal(), oc1));
    }

 

    @Test
    public void testCheckIfShipTouchesShip() {
	//first test with vertical battleship
	assertTrue(bc.checkIfShipTouchesShip(bc.getBowRow(), bc.getBowColumn(), bc.isHorizontal(), oc1));
	//second test with horizontal battle cruiser
	assertTrue(bcr.checkIfShipTouchesShip(bcr.getBowRow(), bcr.getBowColumn(), bcr.isHorizontal(), oc1));
	//ships overlapping
		bc.placeShipAt(10, 10, bc.isHorizontal(), oc1);
	assertFalse(bc.checkIfShipTouchesShip(10, 10, bc.isHorizontal(), oc1));
	
	oc1 = new Ocean();
	shipArray = oc1.getShipArray();
	//ships touching
	  //horizontal
		// on the bottom
		bcr.placeShipAt(10, 10, bcr.isHorizontal(), oc1);
	assertFalse(bcr.checkIfShipTouchesShip(9, 10, bcr.isHorizontal(), oc1));
		// on the top
	assertFalse(bcr.checkIfShipTouchesShip(11, 10, bcr.isHorizontal(), oc1));
		
		// parallel on the left
	assertFalse(sbH.checkIfShipTouchesShip(10, 3, sbH.isHorizontal(), oc1));
		//parallel on the left top corner
	assertFalse(sbH.checkIfShipTouchesShip(9, 3, sbH.isHorizontal(), oc1));
		//parallel on the left bottom corner
	assertFalse(sbH.checkIfShipTouchesShip(11, 3, sbH.isHorizontal(), oc1));
	
		// parallel on the right
	assertFalse(sbH.checkIfShipTouchesShip(10, 13, sbH.isHorizontal(), oc1));
		//parallel on the right top corner
	assertFalse(sbH.checkIfShipTouchesShip(9, 13, sbH.isHorizontal(), oc1));
		//parallel on the right bottom corner
	assertFalse(sbH.checkIfShipTouchesShip(11, 13, sbH.isHorizontal(), oc1));
	//vertical on the top
	assertFalse(sbV.checkIfShipTouchesShip(13, 10, sbV.isHorizontal(), oc1));
		
    }

    @Test
    public void testShootAt() {
	//hit - horizontal vessel
	assertTrue(bcr.shootAt(10, 10));
	assertTrue(bcr.shootAt(10, 6));
	//miss - horizontal vessel
	assertFalse(bcr.shootAt(11, 10));
	assertFalse(bcr.shootAt(11, 6));
	assertFalse(bcr.shootAt(10, 11));
	assertFalse(bcr.shootAt(10, 3));
	
	//hit - vertical vessel
	assertTrue(bc.shootAt(3, 19));
	assertTrue(bc.shootAt(7, 19));
	//miss - vertical vessel
	assertFalse(bc.shootAt(5, 18));
	assertFalse(bc.shootAt(8, 19));
	assertFalse(bc.shootAt(10, 11));
	assertFalse(bc.shootAt(10, 3));
    }
    
    public void testIsSunk(){
	// horizontal vessel
	assertFalse(sbH.isSunk());
	sbH.shootAt(10, 2);
	assertFalse(sbH.isSunk());
	sbH.shootAt(10, 3);
	assertFalse(sbH.isSunk());
	sbH.shootAt(10, 1);
	assertTrue(sbH.isSunk());
	// horizontal vessel
	assertFalse(sbV.isSunk());
	sbV.shootAt(10, 19);
	assertFalse(sbV.isSunk());
	sbV.shootAt(9, 19);
	assertFalse(sbV.isSunk());
	sbV.shootAt(8, 19);
	assertTrue(sbV.isSunk());
    }

}
