import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PartB {
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
        	// for each question, accumulate answers from the whole group 
        	int[] answerCount = new int['z' - 'a' + 1];
        	for (int i = 0; i < answerCount.length; i++) answerCount[i] = 0;
        	
        	String[] answers = sc.next().split(System.getProperty("line.separator"));
        	
        	int nAnswers = 0;
        	for (int i = 0; i < answers.length; i++) {
        		
        		//ignore empty lines
        		if (answers[i].length() == 0) continue;
    			for (int j = 0; j < answers[i].length(); j++) {
	        		char ch = answers[i].charAt(j);
	        		int idx = ch - 'a';
	        		answerCount[idx]++;
    			}
    			
    			nAnswers++;
        	}
        	
        	int sameAnswer = 0;
        	for(int i = 0; i < answerCount.length; i++) {
        		if (answerCount[i] == nAnswers) sameAnswer++;
        	}
        	
        	sumSameAnswers += sameAnswer;
        }
        
        System.out.println(sumSameAnswers);
	}
}
