import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class PartB {
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
    				root.addChild(Integer.parseInt(kv[0]), child);
    			}
    			
    		}
        }

        System.out.println(countChilds(nodeByKey.get("shiny gold bag")));
	}
	
	private static int countChilds(Node node) {
		int count = 0;
		
		for (int i = 0; i < node.contains.size(); i++) {
			int k = node.amount.get(i);
			count += k * (1 + countChilds(node.contains.get(i)));
		}
		
		return count;
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
		ArrayList<Node> contains;
		ArrayList<Integer> amount;
		
		public Node(String key) {
			this.key = key;
			this.contains = new ArrayList<Node>();
			this.amount = new ArrayList<Integer>();
		}
		
		public void addChild(int n, Node child) {
			amount.add(n);
			contains.add(child);
		}
	}

}
