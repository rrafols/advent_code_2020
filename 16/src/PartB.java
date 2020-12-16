import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
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
        
        HashSet<Integer> validValue = new HashSet<>();
        HashMap<String, Range> fieldRange = new HashMap<>();
        
        SortedSet<Map.Entry<String, ArrayList<Integer>>> sortedFieldIndex = new TreeSet<Map.Entry<String, ArrayList<Integer>>>(new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
			@Override
			public int compare(Entry<String, ArrayList<Integer>> o1, Entry<String, ArrayList<Integer>> o2) {
				return o1.getValue().size() - o2.getValue().size();
			}
		});
        
        String fieldRanges = sc.next();
        String[] ownTicket = sc.next().split("\n")[2].split(",");
        String nearbyTickets = sc.next();
        
        String[] ranges = fieldRanges.split("\n");
        for (String range : ranges) {
        	String field = range.split(": ")[0];
        	Range rng = new Range();
        	fieldRange.put(field, rng);
        	
        	String[] subRanges = range.split(": ")[1].split(" or ");
        	for (String subRange : subRanges) {
        		String[] values = subRange.split("-");
        		int min = Integer.parseInt(values[0]);
        		int max = Integer.parseInt(values[1]);
        		rng.add(min, max);
      
        		for (int n = min; n <= max; n++) validValue.add(n);
        	}
        }
        
        ArrayList<int[]> validTickets = new ArrayList<>();
        
        String[] ticketsToCheck = nearbyTickets.split("\n");
		for (int i = 2; i < ticketsToCheck.length; i++) {
			String[] values = ticketsToCheck[i].split(",");
			boolean valid = true;
			for (int j = 0; j < values.length & valid; j++) {
				int k = Integer.parseInt(values[j]);
				valid &= validValue.contains(k);
			}
			
			if (valid) {
				int[] valuesInt = new int[values.length];
				for (int j = 0; j < values.length; j++) valuesInt[j] = Integer.parseInt(values[j]);
				validTickets.add(valuesInt);
			}
		}
		
		HashMap<String, ArrayList<Integer>> fieldIndexArray = new HashMap<String, ArrayList<Integer>>();
		for (String field : fieldRange.keySet()) {
			Range rng = fieldRange.get(field);
			fieldIndexArray.put(field, new ArrayList<Integer>());
			
			for (int j = 0; j < validTickets.get(0).length; j++) {
				boolean valid = true;
				
				for (int i = 0; i < validTickets.size() && valid; i++) {
					valid &= rng.inRange(validTickets.get(i)[j]);
				}
				
				if (valid) fieldIndexArray.get(field).add(j);
			}
		}
		sortedFieldIndex.addAll(fieldIndexArray.entrySet());
		
		HashMap<String, Integer> fieldIndex = new HashMap<String, Integer>();
		HashSet<Integer> usedIdx = new HashSet<Integer>();
		for (Entry<String, ArrayList<Integer>> fieldEntry : sortedFieldIndex) {
			String field = fieldEntry.getKey();
			ArrayList<Integer> values = fieldEntry.getValue();
			
			for(int k: values) {
				if (!usedIdx.contains(k)) {
					usedIdx.add(k);
					fieldIndex.put(field, k);	
				}
			}
		}
		
		long value = 1;
		for (String field : fieldIndex.keySet()) {
			if (field.startsWith("departure")) {
				value *= Integer.parseInt(ownTicket[fieldIndex.get(field)]);
			}
		}
		
		System.out.println("result: " + value);
	}
	
	static class Range {
		ArrayList<Integer> min;
		ArrayList<Integer> max;
		
		Range() {
			min = new ArrayList<Integer>();
			max = new ArrayList<Integer>();
		}
		
		public void add(int min, int max) {
			this.min.add(min);
			this.max.add(max);
		}
		
		public boolean inRange(int k) {
			for (int i = 0; i < min.size(); i++) if(k >= min.get(i) && k <= max.get(i)) return true;
			
			return false;
		}
	}
}