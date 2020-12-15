import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
	private static final ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(new Integer[] {9,12,1,4,17,0,18}));
	private static final HashMap<Integer, Integer> lastIndex = new HashMap<>();
	private static final HashMap<Integer, Integer> prevIndex = new HashMap<>();
	
	public static void main(String[] args) {
		int turn = 1;
		
		HashSet<Integer> seen = new HashSet<>();
		for (int i = 0; i < nums.size(); i++) {
			if (i < nums.size() - 1) seen.add(nums.get(i));
			
			lastIndex.put(nums.get(i), turn);
			turn++;
		}
		
		int lastNum = nums.get(nums.size() - 1);
		while (turn <= 30000000) {
			if (!seen.contains(lastNum)) {
				seen.add(lastNum);
				lastNum = 0;
			} else {
				seen.add(lastNum);
				lastNum = lastIndex.get(lastNum) - prevIndex.get(lastNum);
			}
			
			if (lastIndex.containsKey(lastNum)) prevIndex.put(lastNum, lastIndex.get(lastNum));
			lastIndex.put(lastNum, turn);
			
			if (turn == 2020 || turn == 30000000) System.out.println(turn + ", " + lastNum);
			turn++;
		}
	}
}