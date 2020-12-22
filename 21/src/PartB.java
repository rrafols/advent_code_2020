import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class PartB {
	private static HashMap<String, SortedSet<Map.Entry<String, Integer>>> allergensMap = new HashMap<>();
	private static HashSet<String> unmappedIngredients = new HashSet<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        try {
        	sc = new Scanner(new File("bin/i.txt")).useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        while(sc.hasNext()) {
        	String[] line = sc.nextLine().split(" \\(contains ");
        	String ingredients = line[0];
        	String allergensStr = line[1].substring(0, line[1].length() - 1);
        	
        	String[] allergensArray = allergensStr.split(", ");
        	String[] ingredientArray = ingredients.split(" ");
        	
        	for (String ingredient : ingredientArray) unmappedIngredients.add(ingredient);
        	
        	for(String allergen: allergensArray) {
        		if (!map.containsKey(allergen)) map.put(allergen, new HashMap<String, Integer>());

        		HashMap<String, Integer> ingredientCount = map.get(allergen);
        		for (String ingredient : ingredientArray) {
        			if(ingredientCount.containsKey(ingredient)) {
        				ingredientCount.put(ingredient, ingredientCount.get(ingredient) + 1);
        			} else {
        				ingredientCount.put(ingredient, 1);
        			}
        		}
        	}
        }

        for (String allergen : map.keySet()) {
        	HashMap<String, Integer> ingredientCount = map.get(allergen);
        	
        	SortedSet<Map.Entry<String, Integer>> sortedMap = new TreeSet<Map.Entry<String, Integer>>(new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					int diff = o2.getValue() - o1.getValue();
					return diff != 0 ? diff : o2.getKey().compareTo(o1.getKey());
				}
			});
			
        	sortedMap.addAll(ingredientCount.entrySet());
        	allergensMap.put(allergen, sortedMap);
        }
        
        TreeSet<String> sortedAllergenList = new TreeSet<String>(new Comparator<String>() {
        	@Override
        	public int compare(String o1, String o2) {
        		int c1 = 0;
        		int c2 = 0;
        		
        		SortedSet<Map.Entry<String, Integer>> o1map = allergensMap.get(o1);
        		SortedSet<Map.Entry<String, Integer>> o2map = allergensMap.get(o2);
				for(Entry<String, Integer> entry : o1map) c1 += entry.getValue();
				for(Entry<String, Integer> entry : o2map) c2 += entry.getValue();
				
				int diff = c2 - c1;
				return diff != 0 ? diff : o1.compareTo(o2);
        	}
        });
        
        sortedAllergenList.addAll(allergensMap.keySet());
        
        HashMap<String, String> allergenIngredient = new HashMap<String, String>();
        for (String allergen : sortedAllergenList) {
        	boolean done = false;
        	Iterator<Entry<String, Integer>> setIt = allergensMap.get(allergen).iterator();
        	while (!done && setIt.hasNext()) {
        		Entry<String, Integer> entry = setIt.next();
        		
        		String ingredient = entry.getKey();
        		if (unmappedIngredients.contains(ingredient)) {
        			unmappedIngredients.remove(ingredient);
        			done = true;
        			
        			System.out.println(allergen + " - " + ingredient);
        			allergenIngredient.put(allergen, ingredient);
        		}
        	}
        }
        
        sortedAllergenList = new TreeSet<String>(new Comparator<String>() {
        	@Override
        	public int compare(String o1, String o2) {
        		return o1.compareTo(o2);
        	}
        });
        
        sortedAllergenList.addAll(allergenIngredient.keySet());
        boolean firstComma = false;
        for (String allergen : sortedAllergenList) {
        	if (firstComma) System.out.print(",");
        	System.out.print(allergenIngredient.get(allergen));
        	firstComma = true;
        }
        System.out.println();

	}
}