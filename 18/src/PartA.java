import java.io.File;
import java.util.Scanner;

public class PartA {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        try {
        	sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        long result = 0;
        while(sc.hasNext()) {
        	String line = sc.nextLine();
        	result += process(line, 0, line.length());
        }
        
        System.out.println(result);
	}
	
	public static long process(String str, int start, int end) {
    	long acc = 0;
    	char pendingOperation = ' ';
    	
		int currentNum = -1;
    	for(int i = start; i < end; i++) {
    		char ch = str.charAt(i);

    		if (ch == ' ' || ch == ')') {
    			if (currentNum != -1) {
					acc = runOp(acc, currentNum, pendingOperation);
					pendingOperation = ' ';
    				currentNum = -1;
    			}
    		}
    		
    		if (ch >= '0' && ch <= '9') currentNum = (int) (ch - '0');
    		if (ch == '*' || ch == '+') pendingOperation = ch;
    		
    		if (ch == '(') {
    			int close = -1;
    			int level = 0;
    			for (int k = i + 1; k < end && close == -1; k++) {
    				if (str.charAt(k) == ')') {
    					if(level == 0) {
    						close = k;
    					}
    					else level--;
    				}
    				if (str.charAt(k) == '(') level++;
    			}
    			
    			acc = runOp(acc, process(str, i + 1, close + 1), pendingOperation);
    			pendingOperation = ' ';
    			i = close;
    		}
    	}
    	
    	if (currentNum != -1) acc = runOp(acc, currentNum, pendingOperation);
    	return acc;
    }

	private static long runOp(long acc, long lastNum, char pendingOperation) {
		if (pendingOperation == ' ') {
			acc = lastNum;
		} else {
			if (pendingOperation == '*') acc *= lastNum;
			else acc += lastNum;
		}
		return acc;
	}
}