package shipGameJUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shipGame.Ocean;
import shipGame.Ship;

public class OceanTest {
    Ocean oc1;
    Ocean oc2;
    Ocean oc3;
    Ship [][] sh;
    Ship [][] sh1;
    Ship [][] sh3;
    @Before
    public void setUp() throws Exception {
	oc1 = new Ocean ();
	oc2 = new Ocean ();
	oc3 = new Ocean ();
	sh = oc1.getShipArray();
    }

    @Test
    public void testPlaceAllShipsRandomly() {
	int shipCount = 0;
	oc1.placeAllShipsRandomly();
	
	for(int i = 0; i < sh.length; i ++)
	    for(int j = 0; j < sh[0].length; j ++){
		if(!sh[i][j].getShipType().equals(" "))
		    shipCount ++;
	    }
	assertTrue(shipCount == 61);
	
	oc2.placeAllShipsRandomly();
	sh1 = oc2.getShipArray();
	int sameCells = 0;
	boolean twoShipMatricesDifferent = true;
	for(int i = 0; i < sh.length; i ++)
	    for(int j = 0; j < sh[0].length; j ++){
		if(sh[i][j].getShipType().equals(sh1[i][j].getShipType()))
		    sameCells ++;
	    }
	if(sameCells == 400)
	    twoShipMatricesDifferent = false;
	
	assertTrue(twoShipMatricesDifferent);
    }

    @Test
    public void testIsOccupied() {
	boolean isAnyCellOccupied = false;
	Ship [][] sh3 = oc3.getShipArray();
	
	assertFalse(oc3.isOccupied(0, 0));
	assertFalse(oc3.isOccupied(10, 0));
	assertFalse(oc3.isOccupied(0, 10));
	assertFalse(oc3.isOccupied(1, 1));
	assertFalse(oc3.isOccupied(19, 5));
	
	for(int i = 0; i < sh3.length; i ++)
	    for(int j = 0; j < sh3[0].length; j ++){
		if(oc3.isOccupied(i, j))
		    isAnyCellOccupied = true;
	    }
	assertFalse(isAnyCellOccupied);
	

	oc3.placeAllShipsRandomly();
	sh3 = oc3.getShipArray();
	
	for(int i = 0; i < sh3.length; i ++)
	    for(int j = 0; j < sh3[0].length; j ++){
		if(oc3.isOccupied(i, j))
		    isAnyCellOccupied = true;
	    }
	assertTrue(isAnyCellOccupied);
    }

    @Test
    public void testShootAt() {
	oc1.shootAt(0, 0);
	assertTrue(sh[0][0].isThisLocationHit(0, 0, sh[0][0].isHorizontal()));
	
	oc1.shootAt(10, 10);
	assertTrue(sh[10][10].isThisLocationHit(10, 10, sh[10][10].isHorizontal()));
	
	oc1.shootAt(8, 8);
	assertTrue(sh[8][8].isThisLocationHit(8, 8, sh[8][8].isHorizontal()));
	
	oc1.shootAt(15, 15);
	assertTrue(sh[15][15].isThisLocationHit(15, 15, sh[15][15].isHorizontal()));
	
	oc1.shootAt(4, 8);
	assertTrue(sh[4][8].isThisLocationHit(4, 8, sh[4][8].isHorizontal()));
	
	oc1.shootAt(6, 6);
	assertTrue(sh[6][6].isThisLocationHit(6, 6, sh[6][6].isHorizontal()));
	
	assertFalse(sh[1][1].isThisLocationHit(1, 1, sh[1][1].isHorizontal()));
	assertFalse(sh[2][2].isThisLocationHit(2, 2, sh[2][2].isHorizontal()));
	assertFalse(sh[12][1].isThisLocationHit(12, 1, sh[12][1].isHorizontal()));
	assertFalse(sh[1][14].isThisLocationHit(1, 14, sh[1][14].isHorizontal()));
	assertFalse(sh[3][13].isThisLocationHit(3, 13, sh[3][13].isHorizontal()));
    }

    @Test
    public void testIsGameOver() {
	oc1.placeAllShipsRandomly();
	oc2.placeAllShipsRandomly();
	oc3.placeAllShipsRandomly();
	
	assertFalse(oc1.isGameOver());
	assertFalse(oc2.isGameOver());
	assertFalse(oc3.isGameOver());
	
	for(int i = 0; i < oc1.getShipArray().length; i ++)
	    for(int j = 0; j < oc1.getShipArray().length; j ++)
		oc1.shootAt(i, j);
	    
	assertTrue(oc1.isGameOver());
	assertFalse(oc2.isGameOver());
	assertFalse(oc3.isGameOver());
	
	for(int i = 0; i < oc2.getShipArray().length; i ++)
	    for(int j = 0; j < oc2.getShipArray().length; j ++)
		oc2.shootAt(i, j);
	
	assertTrue(oc2.isGameOver());
	assertFalse(oc3.isGameOver());
	
	for(int i = 0; i < oc3.getShipArray().length; i ++)
	    for(int j = 0; j < oc3.getShipArray().length; j ++)
		oc3.shootAt(i, j);
	
	assertTrue(oc3.isGameOver());
    }


}
