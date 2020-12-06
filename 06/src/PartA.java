import java.io.File;
import java.util.Arrays;
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
        
        int sumSameAnswers = 0;
        while (sc.hasNext()) {
        	HashSet<Integer> uniqueAnswers = new HashSet<Integer>();
        	String[] answers = sc.next().split(System.getProperty("line.separator"));
        	
        	for (int i = 0; i < answers.length; i++) {
    			for (int j = 0; j < answers[i].length(); j++) {
	        		char ch = answers[i].charAt(j);
	        		int idx = ch - 'a';
	        		uniqueAnswers.add(idx);
    			}
        	}
        	
        	sumSameAnswers += uniqueAnswers.size();
        }
        
        System.out.println(sumSameAnswers);
	}
}
