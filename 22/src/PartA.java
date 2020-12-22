import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
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
        
        LinkedList<Integer> player1 = getStack(sc.next());
        sc.nextLine();
        LinkedList<Integer> player2 = getStack(sc.next());
        
        boolean done = false;
        while(!done) {
        	int p1 = player1.removeFirst();
        	int p2 = player2.removeFirst();
        	
        	if (p1 > p2) {
        		player1.addLast(p1);
        		player1.addLast(p2);
        	} else {
        		player2.addLast(p2);
        		player2.addLast(p1);
        	}
        	
        	done = player1.size() == 0 || player2.size() == 0;
        }
        
        LinkedList<Integer> winning = player1.size() == 0 ? player2 : player1;
        
        int score = 0;
        int mult = 1;
        while(winning.size() > 0) {
        	score += winning.removeLast() * mult;
        	mult++;
        }
        
        System.out.println("total: " + score);
	}
	
	public static LinkedList<Integer> getStack(String startState) {
		LinkedList<Integer> stack = new LinkedList<Integer>();
		
		String[] nums = startState.split("\\n");
		for (int i = 1; i < nums.length; i++) stack.add(Integer.parseInt(nums[i]));
		
		return stack;
	}
}