import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
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
        
        LinkedList<Integer> player1 = getStack(sc.next());
        sc.nextLine();
        LinkedList<Integer> player2 = getStack(sc.next());
        
        int win = solveGame(player1, player2, 1);
        
        LinkedList<Integer> winning = win == 1 ? player1 : player2;
    	
        int score = 0;
        int mult = 1;
        while(winning.size() > 0) {
        	score += winning.removeLast() * mult;
        	mult++;
        }
        
        System.out.println("total: " + score);
	}
	
	private static int solveGame(LinkedList<Integer> player1, LinkedList<Integer> player2, int game) {
		HashSet<String> previousStates = new HashSet<>();
		
		boolean done = false;
        while(!done) {
        	String state = getState(player1) + "-" + getState(player2);
        	if (previousStates.contains(state)) return 1;
        	previousStates.add(state);
        	
        	int win = 1;
        	int p1 = player1.removeFirst();
        	int p2 = player2.removeFirst();
        	
        	if (player1.size() >= p1 && player2.size() >= p2) {
        		LinkedList<Integer> newP1 = (LinkedList<Integer>) player1.clone();
        		LinkedList<Integer> newP2 = (LinkedList<Integer>) player2.clone();
        		
        		while(newP1.size() > p1) newP1.removeLast();
        		while(newP2.size() > p2) newP2.removeLast();
        		win = solveGame(newP1, newP2, game + 1);
        	} else win = p1 > p2 ? 1 : 2;
        	
        	if (win == 1) {
        		player1.addLast(p1);
        		player1.addLast(p2);
        	} else {
        		player2.addLast(p2);
        		player2.addLast(p1);
        	}
        	done = player1.size() == 0 || player2.size() == 0;
        }
        
		return player1.size() == 0 ? 2 : 1;
	}
	
	private static String getState(LinkedList<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (int k : list) {
			sb.append(k);
			sb.append("_");
		}
		return sb.toString();
	}
	
	public static LinkedList<Integer> getStack(String startState) {
		LinkedList<Integer> stack = new LinkedList<Integer>();
		
		String[] nums = startState.split("\\n");
		for (int i = 1; i < nums.length; i++) stack.add(Integer.parseInt(nums[i]));
		
		return stack;
	}
}