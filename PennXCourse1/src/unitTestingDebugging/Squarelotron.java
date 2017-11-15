package unitTestingDebugging;

public class Squarelotron {

    public int[][] squarelotron;
    public int size;

    public Squarelotron(int size){
	this.squarelotron = new int[size][size];
	this.size = size;
	initMatrix();
    }

    public void initMatrix(){
	int num = 1;
	for(int i = 0; i < size; i++)
	    for(int j = 0; j < size; j++)
		squarelotron [i][j] = num++;
    }

    public Squarelotron upsideDownFlip(int ring){
	Squarelotron toReturn = new Squarelotron(size);
	copyValues(this.squarelotron, toReturn.getSquarelotronMatrix());
	
	 for(int i = 0; i < size; i++)
		for(int j = 0; j < size; j++){
		    if(i == ring - 1 && j >= ring - 1 && j <= size - ring ){
			toReturn.getSquarelotronMatrix()[size - ring][j]= this.getSquarelotronMatrix()[i][j];
		    }else if(i == size - ring && j >= ring - 1 && j <= size - ring){
			toReturn.getSquarelotronMatrix()[ring - 1][j]= this.getSquarelotronMatrix()[i][j];
		    }else if((j == ring - 1 || j == size - ring ) && i > ring - 1 && i < size - ring){
			toReturn.getSquarelotronMatrix()[size - 1 - i][j]= this.getSquarelotronMatrix()[i][j];
		    }
		}
	return toReturn;
    }

    public Squarelotron mainDiagonalFlip(int ring){
	Squarelotron toReturn = new Squarelotron(size);
	copyValues(this.squarelotron, toReturn.getSquarelotronMatrix());
	
	 for(int i = 0; i < size; i++)
		for(int j = 0; j < size; j++){
		    if(i == ring - 1 && j >= ring - 1 && j <= size - ring && i != j){
			toReturn.getSquarelotronMatrix()[j][i]= this.getSquarelotronMatrix()[i][j];
		    }else if(i == size - ring && j >= ring - 1 && j <= size - ring && i != j){
			toReturn.getSquarelotronMatrix()[j][i]= this.getSquarelotronMatrix()[i][j];
		    }else if((j == ring - 1 || j == size - ring ) && i > ring - 1 && i < size - ring && i != j){
			toReturn.getSquarelotronMatrix()[j][i]= this.getSquarelotronMatrix()[i][j];
		    }
		}

	return toReturn;
    }

    private void rotateLeft(int numberOfTurns){

	int[][] aux = new int[size][size];
	for(int k = 0; k < numberOfTurns; k++){
	    for(int i = 0; i < size; i++)
		for(int j = 0; j < size; j++){
		    aux[size - 1 -j] [i] = squarelotron[i][j];
		}
	    copyValues(aux, squarelotron);
	    
	}

    }

    public void rotateRight(int numberOfTurns) {
	if(numberOfTurns < 0){
	    rotateLeft(Math.abs(numberOfTurns));
	}else{
	    int[][] aux = new int[size][size];
	    for(int k = 0; k < numberOfTurns; k++){
		for(int i = 0; i < size; i++)
		    for(int j = 0; j < size; j++){
			aux[j] [size - 1 -i] = squarelotron[i][j];
		    }
		copyValues(aux, squarelotron);
		
	    }
	}

    }

    private void copyValues(int[][] aux, int[][] squarelotron2) {
	for(int i = 0; i < aux.length; i ++)
	    for(int j = 0; j < aux[0].length; j++)
		squarelotron2[i][j] = aux [i][j];

    }

    public int[][] getSquarelotronMatrix (){
	return squarelotron;
    }
    public int getSize (){
	return size;
    }

    public void print(){
	for(int i = 0; i < squarelotron.length; i ++){
	    for(int j = 0; j < squarelotron[0].length; j++)
		System.out.print(squarelotron[i][j] + " ");
	    System.out.println("");
	}
	System.out.println("");

    }
}
