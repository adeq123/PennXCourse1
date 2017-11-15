package unitTestingDebugging;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SquarelotronTest {
   
    Squarelotron testS; 
    Squarelotron testS2;
    Squarelotron testS3;
    Squarelotron testS4;
    Squarelotron testS5;
    @Before
    public void setUp() throws Exception {
	testS = new Squarelotron(3);
	testS2 = new Squarelotron(6);
	testS4 = new Squarelotron(4);

    }

    @Test
    public void testSquarelotron() {
	assertEquals(3, testS.getSize());
	assertEquals(3, testS.getSquarelotronMatrix()[0][2]);
	assertEquals(9, testS.getSquarelotronMatrix()[2][2]);
	assertEquals(4, testS.getSquarelotronMatrix()[1][0]);
    }

    @Test
    public void testInitMatrix() {
	assertEquals(3, testS.getSquarelotronMatrix()[0][2]);
	assertEquals(9, testS.getSquarelotronMatrix()[2][2]);
	assertEquals(4, testS.getSquarelotronMatrix()[1][0]);
    }

    @Test
    public void testUpsideDownFlip() {
	testS3 = testS2.upsideDownFlip(1);
	assertEquals(31, testS3.getSquarelotronMatrix()[0][0]);
	assertEquals(33, testS3.getSquarelotronMatrix()[0][2]);
	assertEquals(13, testS3.getSquarelotronMatrix()[3][0]);
	assertEquals(18, testS3.getSquarelotronMatrix()[3][5]);
	
	testS3 = testS2.upsideDownFlip(2);
	assertEquals(1, testS3.getSquarelotronMatrix()[0][0]);
	assertEquals(11, testS3.getSquarelotronMatrix()[4][4]);
	assertEquals(21, testS3.getSquarelotronMatrix()[3][2]);
	assertEquals(15, testS3.getSquarelotronMatrix()[2][2]);

    
    }

    @Test
    public void testMainDiagonalFlip() {
	testS5 = testS4.mainDiagonalFlip(1);
	assertEquals(1, testS5.getSquarelotronMatrix()[0][0]);
	assertEquals(9, testS5.getSquarelotronMatrix()[0][2]);
	assertEquals(4, testS5.getSquarelotronMatrix()[3][0]);
	assertEquals(12, testS5.getSquarelotronMatrix()[3][2]);
	
	testS5 = testS4.mainDiagonalFlip(2);
	assertEquals(1, testS5.getSquarelotronMatrix()[0][0]);
	assertEquals(3, testS5.getSquarelotronMatrix()[0][2]);
	assertEquals(13, testS5.getSquarelotronMatrix()[3][0]);
	assertEquals(15, testS5.getSquarelotronMatrix()[3][2]);
	
	assertEquals(6, testS5.getSquarelotronMatrix()[1][1]);
	assertEquals(10, testS5.getSquarelotronMatrix()[1][2]);
	assertEquals(7, testS5.getSquarelotronMatrix()[2][1]);
	assertEquals(11, testS5.getSquarelotronMatrix()[2][2]);
    }

    @Test
    public void testRotateRight() {
	testS.rotateRight(1);
	assertEquals(1, testS.getSquarelotronMatrix()[0][2]);
	assertEquals(3, testS.getSquarelotronMatrix()[2][2]);
	assertEquals(8, testS.getSquarelotronMatrix()[1][0]);
	
	testS.rotateRight(2);
	assertEquals(9, testS.getSquarelotronMatrix()[0][2]);
	assertEquals(7, testS.getSquarelotronMatrix()[2][2]);
	assertEquals(2, testS.getSquarelotronMatrix()[1][0]);
	
	testS.rotateRight(-1);
	assertEquals(7, testS.getSquarelotronMatrix()[0][2]);
	assertEquals(1, testS.getSquarelotronMatrix()[2][2]);
	assertEquals(6, testS.getSquarelotronMatrix()[1][0]);
	
    }

}
