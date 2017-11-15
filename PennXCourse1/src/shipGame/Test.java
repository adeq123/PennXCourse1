package shipGame;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
	int rowRand;
	int colRand;
	Random rnd = new Random ();
	Ocean oc = new Ocean();
	oc.placeAllShipsRandomly();
	oc.print();
	for(int i = 0; i < 10; i++){
	    rowRand = (int) (rnd.nextFloat() * (19));
	    colRand = (int) (rnd.nextFloat() * (19));
	    oc.shootAt(rowRand, colRand);
	    System.out.println("(" + rowRand +", " + colRand + ")");
	}
	oc.print();

    }

}
