import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class PartB {

	static ArrayList<BigInteger> numbers = new ArrayList<BigInteger>();
	static BigInteger solution = BigInteger.valueOf(20874512l);
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
        	sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        while (sc.hasNext()) numbers.add(new BigInteger(sc.nextLine()));

        for (int i = 0; i < numbers.size(); i++) {
        	BigInteger current = numbers.get(i);
        	
        	for (int j = i + 1; j < numbers.size(); j++) {
        		current = current.add(numbers.get(j));
        		
        		if (current.compareTo(solution) > 0) break;
        		if (current.compareTo(solution) == 0) {
        			BigInteger min = numbers.get(i);
        			BigInteger max = numbers.get(i);
        			for (int k = i; k <= j; k++) {
        				BigInteger val = numbers.get(k);
        				if (val.compareTo(min) < 0) min = val;
        				if (val.compareTo(max) > 0) max = val;
        			}
        			
        			System.out.println("range: " + i + ", " + j + " :: min/max" + min + "/" + max + " solution: " + min.add(max));
        		}
        	}
        }
	}
}