import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }

        int valid = 0;
        int invalid = 0;
        while(sc.hasNext()) {
        	String[] range = sc.next().split("-");
        	String ch = sc.next().substring(0, 1);
        	String str = sc.nextLine();
        	
//        	System.out.println(range[0] + " - " + range[1] + " char: " + ch + " str: " + str);
        	if (isValid(range, ch, str)) valid++;
        	else invalid++;
        }
        
        System.out.println("num valid: " + valid);
        System.out.println("num invalid: " + invalid);
	}

// simple solution

//	private static final boolean isValid(String[] range, String chStr, String str) {
//		int min = Integer.parseInt(range[0]);
//		int max = Integer.parseInt(range[1]);
//		char ch = chStr.charAt(0);
//		
//		int count = 0;
//		for(int i = 0; i < str.length(); i++) {
//			if (str.charAt(i) == ch) count++;
//		}
//		
//		return (count >= min && count <= max);
//	}
	
	private static final boolean isValid(String[] range, String chStr, String str) {
		int min = Integer.parseInt(range[0]);
		int max = Integer.parseInt(range[1]);
		char ch = chStr.charAt(0);
		
		// works as 0-indexed string as string contains a leading space (due to how it's parsed with sc.nextLine())
		int count = 0;
		if (str.charAt(min) == ch) count++;
		if (str.charAt(max) == ch) count++;
		count &= 1;
		
		return count == 1;
	}
}