import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Solution {
	private static HashSet<String> validEyeColor = new HashSet<String>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
            sc = new Scanner(new File("bin/i.txt")).useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }

      
        int valid = 0;
        while (sc.hasNext()) {
        	String[] passportFields = sc.next().split("\\s"); 
        	if (sc.hasNext()) sc.nextLine();
      
        	HashSet<String> mandatoryFields = new HashSet<String>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")); // cid is optional
        	for (int i = 0; i < passportFields.length; i++) {
        		String[] vk = passportFields[i].split(":");
        		String field = vk[0];
        		String value = vk[1];
        		if (isFieldValid(field, value)) mandatoryFields.remove(field);
        	}
        	
        	if (mandatoryFields.size() == 0) valid++;
        }
        
        System.out.println("valid passports: " + valid);
	}
	
	private static boolean isFieldValid(String field, String value) {
		//simple solution
		//return true;
		
		switch(field) {
			case "byr":
				return checkStrRange(value, 1920, 2002);
				
			case "iyr":
				return checkStrRange(value, 2010, 2020);
				
			case "eyr":
				return checkStrRange(value, 2020, 2030);
				
			case "hgt":
				String unit = value.substring(value.length() - 2);
				String hgt = value.substring(0, value.length() - 2);
				
				switch (unit) {
					case "cm":
						return checkStrRange(hgt, 150, 193);
					case "in":
						return checkStrRange(hgt, 59, 76);
					default:
						return false;
				}
			case "hcl":
				if (value.length() != 7) return false;
				return (value.split("#([0-9]|[a-f]){6}").length == 0);
				
			case "ecl":
				return validEyeColor.contains(value);
				
			case "pid":
				return value.split("[0-9]{9}").length == 0;
				
			default:
				return false; 
		}
	}
	
	private static boolean checkStrRange(String value, int min, int max) {
		int ival = Integer.parseInt(value);
		return (ival >= min && ival <= max);
	}
}