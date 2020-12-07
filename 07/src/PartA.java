import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PartA {
	private static HashMap<String, Node> nodeByKey = new HashMap<String, Node>();
	private static HashSet<String> uniqueRoots = new HashSet<String>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
        	sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        while (sc.hasNext()) {
        	String line = sc.nextLine();
        	String[] parts = line.split(" contain ");
        	
        	String key = processKey(parts[0]);
        	Node root = getKeyNode(key);
        	uniqueRoots.add(key);
        	
        	String[] contains = parts[1].split(",\\s+");
    		for (int j = 0; j < contains.length; j++) {
    			contains[j] = contains[j].replace(".","");
    			String[] kv = contains[j].split(" ", 2);
    			
    			if (!kv[0].equals("no")) {
    				key = processKey(kv[1]);
    				
    				Node child = getKeyNode(key);
    				root.addChild(child);
    			}
    			
    		}
        }

        String check = "shiny gold bag";
        int sumCount = 0;
        for (String uniqueRoot : uniqueRoots) {
        	if (!uniqueRoot.equals(check)) {
	        	Node root = nodeByKey.get(uniqueRoot);
	        	int count = totalContains(root, check, 0);
	        	
	        	sumCount += count;
        	}
        }
        
        System.out.println(sumCount);
	}
	
	private static int totalContains(Node root, String key, int lvl) {
    	if (root.key.equals(key)) return 1;
    	
    	for (Node child : root.contains) {
    		int count = totalContains(child, key, lvl+1);
    		if (count >= 1) return 1;
    	}
    	
    	return 0;
	}
	
	private static String processKey(String key) {
		if (key.endsWith("s")) key = key.substring(0, key.length() - 1);
		return key;
	}
	
	private static Node getKeyNode(String key) {
		Node node = nodeByKey.get(key);
		
		if (node == null) {
			node = new Node(key);
			nodeByKey.put(key, node);
		}
		
		return node;
	}
	
	static class Node {
		String key;
		HashSet<Node> contains;
		
		public Node(String key) {
			this.key = key;
			this.contains = new HashSet<Node>();
		}
		
		public void addChild(Node child) {
			contains.add(child);
		}
	}
}
