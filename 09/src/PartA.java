import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class PartA {

	static ArrayList<BigInteger> numbers = new ArrayList<BigInteger>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
        	sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        while (sc.hasNext()) numbers.add(new BigInteger(sc.nextLine()));

        
        for (int i = 25; i < numbers.size(); i++) {
        	BigInteger current = numbers.get(i);
        	
        	boolean found = false;
        	for(int j = 0; j < 25 && !found; j++) {
        		BigInteger a = numbers.get(i - 25 + j);
        		for(int k = 0; k < 25 && !found; k++) {
        			if (k == j) continue;
        			
        			BigInteger b = numbers.get(i - 25 + k);
        			if (a.equals(b)) continue;
        			
        			if(a.add(b).equals(current)) found = true;
        		}
        	}
        	
        	if (!found) {
        		System.out.println(current);
        		return;
        	}
        }
	}
}