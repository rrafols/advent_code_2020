import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PartA {
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
        numbers.add(0);	// add outlet
        
        Collections.sort(numbers, Collections.reverseOrder());
        numbers.add(0, numbers.get(0) + 3);	//add device (maximum + 3)
        
        int diff1 = 0;
        int diff3 = 0;
        int lastK = Integer.MIN_VALUE;
        
        for(int k : numbers) {
        	if (k == lastK - 1) diff1++;
        	if (k == lastK - 3) diff3++;
        	lastK = k;
        }
        
        System.out.println("1-diff: " + diff1 + " :: 3-diff: " + diff3 + " result: " + (diff1 * diff3));
	}
}