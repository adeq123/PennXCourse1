package unitTestingDebugging;

import java.util.*;

public class Stats {

    ArrayList<Integer> numbers;
    
    public Stats(){
	numbers = new ArrayList<Integer>();
    }

    public double average(){
	double sum = 0;
	for (int i = 0; i < numbers.size(); i++) {
	    sum += numbers.get(i);
	}
	return sum / numbers.size();
    }

    public double std() {
	double mean = average();
	double deviations = 0;
	for (int i = 0; i < numbers.size(); i++) {
	    deviations += Math.pow((numbers.get(i) - mean), 2);
	}
	double standardDev = Math.sqrt(deviations / (numbers.size() - 1));
	return standardDev;
    }

    public static void main(String[] args) {
	Stats stats = new Stats();
	System.out.println("Enter some numbers. Enter a 'q' to quit");
	Scanner scanner = new Scanner(System.in);
	String input = "";
	while (!input.equals("q")) {
	    input = scanner.next();
	    if (!input.equals("q")) {
		stats.numbers.add(Integer.parseInt(input));
	    }
	}
	System.out.println(stats.average());
	System.out.println(stats.std());
    }

}

