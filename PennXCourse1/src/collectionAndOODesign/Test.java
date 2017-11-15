package collectionAndOODesign;

import java.util.ArrayList;
import java.util.Random;

public class Test {

    private static final float MATRIX_SIZE = 20;

    public static void main(String[] args) {
	ArrayList<String> list = new ArrayList<>();
	Random rnd = new Random ();
	System.out.println((int) (rnd.nextFloat() * MATRIX_SIZE));

    }

}
