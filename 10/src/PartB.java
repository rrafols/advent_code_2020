import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class PartB {

	static ArrayList<Integer> numbers = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
        	sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        
        while (sc.hasNext()) numbers.add(Integer.parseInt(sc.nextLine()));
        Collections.sort(numbers);
        
        int device = numbers.get(numbers.size() - 1) + 3;
        numbers.add(device);	//add device (maximum + 3)
        
        HashMap<Integer, BigInteger> count = new HashMap<Integer, BigInteger>();
        count.put(0, BigInteger.ONE);
        
        for (int i = 0; i < numbers.size(); i++) {
        	int k = numbers.get(i);
        	
        	BigInteger accVal = BigInteger.ZERO;
        	for (int j = 1; j <= 3; j++) {
	        	if (count.containsKey(k - j)) {
	        		accVal = accVal.add(count.get(k - j));
	        	}
        	}
        	
        	if (accVal.compareTo(BigInteger.ZERO) > 0) {
        		count.put(k, accVal);
        	}
        }
        
        System.out.println("combinations: " + count.get(device));
	}
}