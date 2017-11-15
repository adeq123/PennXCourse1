package javaBasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WhackAMole {
    
    public int score;
    public int molesLeft;
    public int attemptsLeft;
    public char [][] moleGrid;
    
    public WhackAMole(int numAttempts, int gridDimension){
	this.score = 0;
	this.molesLeft = 0;
	this.attemptsLeft = numAttempts;
	this.moleGrid = new char [gridDimension] [gridDimension];
	initGrid();
    }
    
    public boolean place (int x, int y){
	
    	if(moleGrid [x][y] != 'M'){
    		moleGrid [x][y] = 'M';
    		molesLeft ++;
    		return true;
    	}else
    		return false;
	
    }
    
    public void whack(int x, int y){
	 
    	if(moleGrid [x][y] == 'M' ){
    		moleGrid [x][y] = 'W';
    		score ++;
    		molesLeft --;
    	}else if(moleGrid [x][y] == 'W'){
    		
    	}
    	
    	attemptsLeft --;
    }
    
   public void printGridToUser(){
	   System.out.println("XXXXXXXXXXXX");
	   for(int i = 0; i <moleGrid.length; i++ ){
		   for(int j = 0; j<moleGrid [0].length; j++){
			   if(moleGrid [i] [j] == 'M'){
				   System.out.print('*');
			   }else
				   System.out.print(moleGrid [i] [j]);
		   }
		   System.out.println("");
	   }
	   System.out.println("XXXXXXXXXXXX");
    }
    
   public void printGrid(){
	   System.out.println("XXXXXXXXXXXX");
	   for(int i = 0; i <moleGrid.length; i++ ){
		   for(int j = 0; j<moleGrid [0].length; j++){
				   System.out.print(moleGrid [i] [j]);
		   }
		   System.out.println("");
	   }
	   System.out.println("XXXXXXXXXXXX");
    }
   
   
   public void initGrid(){
	   for(int i = 0; i < moleGrid.length; i++)
		   for(int j = 0; j < moleGrid [0] .length; j++)
			   moleGrid [i] [j] = '*';
   }
   
   public int getAttemptsLeft(){
	   return attemptsLeft;
   }
   
   public int getScore(){
	   return score;
   }
   
   public int getMolesLeft(){
	   return molesLeft;
   }
   
    public static void main(String[] args) throws NumberFormatException, IOException {
	
    	int molesNumber = 10;
    	int randX;
    	int randY;
    	int userX = 0;
    	int userY = 0;
    	int gridDim = 10;
    	int numOfAttempts = 50;
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	
    	WhackAMole wam = new WhackAMole (numOfAttempts, gridDim);
    	
    	while(molesNumber > 0){
    		
    		randX = (int) (Math.random() * 10);
    		randY = (int) (Math.random() * 10);
    		
    		if(wam.place(randX, randY))
    		molesNumber --;	
    		
    	}
    	wam.printGrid();
    	System.out.println("Welcome to the game ! Moles Grid dimensions: " + gridDim +"x"+gridDim);
    	System.out.println("Number of attempts: " + numOfAttempts);
    	while(userX != -1 && userY != -1){
    		System.out.println("Enter X coordinate of where you want to whack a mole" );
    		userX = Integer.parseInt(br.readLine());
    		System.out.println("Enter Y coordinate of where you want to whack a mole" );
    		userY = Integer.parseInt(br.readLine());
    		if(userX == -1 && userY == -1)
    			break;
    		wam.whack(userX, userY);
    		wam.printGridToUser();
    		System.out.println("Number of attempts left: " + wam.getAttemptsLeft() );
    		System.out.println("Score: " +wam.getScore());
    		System.out.println("Moles left: " +wam.getMolesLeft());
    	}
    	
	wam.printGrid();
	System.out.println("Good bye!! ");

    }

}

