import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PartA {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        try {
        	sc = new Scanner(new File("bin/i.txt")).useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        HashSet<Integer> valid = new HashSet<Integer>();
        
        String fieldRanges = sc.next();
        String ownTicket = sc.next();
        String nearbyTickets = sc.next();
        
        String[] ranges = fieldRanges.split("\n");
        for (String range : ranges) {
        	String[] subRanges = range.split(": ")[1].split(" or ");
        	for (String subRange : subRanges) {
        		String[] values = subRange.split("-");
        		int min = Integer.parseInt(values[0]);
        		int max = Integer.parseInt(values[1]);
      
        		for (int n = min; n <= max; n++) valid.add(n);
        	}
        }
        
        ArrayList<Integer> invalidValues = new ArrayList<Integer>();
        String[] ticketsToCheck = nearbyTickets.split("\n");
		for (int i = 2; i < ticketsToCheck.length; i++) {
			String[] values = ticketsToCheck[i].split(",");
			for (String value : values) {
				int k = Integer.parseInt(value);
				if (!valid.contains(k)) invalidValues.add(k);
			}
		}
		
		int result = 0;
		for (int k : invalidValues) result += k;
		
		System.out.println("error rate: " + result);
	}
}