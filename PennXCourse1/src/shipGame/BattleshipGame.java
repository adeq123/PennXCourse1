package shipGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The BattleshipGame class is the ”main” class–that is, it contains
 * a main method. In this class you will set up the game; accept 
 * ”shots” from the user; display the results; print final scores; 
 * and ask the user if he/she wants to play again. All input/output 
 * is done here (although some of it is done by calling a print() 
 * method in the Ocean class.) All computation will be done in the 
 * Ocean class and the various Ship classes. Note that you want to
 * accept 5 shots from the user. So you need to ensure that you have
 * a well defined format for this. For example you can provide an 
 * instruction to the user as follows. The input format should look
 * like this: 1, 1; 0, 3; 7, 3; 9, 11; 12, 17
 * @author ania
 *
 */
public class BattleshipGame {

    public static void main(String[] args) throws IOException {
	int userRow = 0;
	int userCol = 0;
	int roundCounter = 0;
	String line = "";
	String [] lineArray;
	String coordinatesArray [];
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	boolean exitCondition = true;
	Ocean oc = new Ocean();
	oc.placeAllShipsRandomly();
	System.out.println("We’ll play this game on a 20x20 ocean. This is larger than the\n"
		+ "ocean in the traditional battleship game.  In this game we will have one\n"
		+ "8-square Battleship, one 7-square Battlecruiser, two 6-square Cruisers,\n"
		+ "two 5-square Light Cruisers, three 4-square Destroyers and four 3-square Submarines.\n"
		+ "Finally, unlike the traditional game, A player can shoot 5 times in each turn.");


	System.out.println("\nWrite -1, -1; and press ENTER to quit the game");

	while (exitCondition || oc.isGameOver()){

	    System.out.println("\nPlease enter coordintaes of 5 points in appropriate format. "
		    + "If you enter more then 5 points, additaional coordinates will neglected\n");
	    System.out.println("Round: " + roundCounter +"\nHits: " + oc.getHitCount() + "\nShips sunken: " + oc.getShipsSunk());
	    System.out.println("\nINFO: The input format should look "
		    + "like this: 1, 1; 0, 3; 7, 3; 9, 11; 12, 17");

	    line = br.readLine();
	    lineArray = line.split("; ");
	    if(lineArray.length > 5)
		System.out.println("You have enter more then 5 points. Additional points will be neglected \n");
	    for(int i = 0; i < 5 && i < lineArray.length; i++){
		coordinatesArray = lineArray[i].split(", ");
		try{
		   // System.out.println("("+coordinatesArray[0] + ", " + coordinatesArray[1] + ")");
		    userRow = Integer.parseInt(coordinatesArray[0]);
		    userCol= Integer.parseInt(coordinatesArray[1]);
		}catch(NumberFormatException n){
		    System.out.println("The coordinates entered format is nor correct! Please use correct pattern...");
		}
		if(userRow == -1 && userCol == -1){
		    exitCondition = false;
		}else{

		    if(oc.shootAt(userRow, userCol)){
			System.out.println("Row: " + userRow +" Column: " + userCol +" Hit!");
			if(oc.getShipArray()[userRow][userCol].isSunk())
			    System.out.println("You have sunk a ship of type: " + oc.getShipArray()[userRow][userCol].getShipType());
		    }else
			System.out.println("Row: " + userRow +" Column: " + userCol +" Miss!");

		}
	    }
	    roundCounter ++;
	    oc.print();

	}

	if(oc.isGameOver()){
	    System.out.println("You won bro!");
	    System.out.println("Hits required: "+oc.getHitCount());
	    
	}
	br.close();
	System.out.println("Thanks for game!");
    }

}
