import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PartA {
	private static HashMap<Integer, ArrayList<Node>> ruleMap = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        try {
        	sc = new Scanner(new File("bin/i.txt")).useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        String[] rules = sc.next().split("\\n"); sc.nextLine();
        String[] messages = sc.next().split("\\n");

        for (int i = 0; i < rules.length; i++) {
        	int idx = Integer.parseInt(rules[i].substring(0, rules[i].indexOf(":")));
        	String rule = rules[i].substring(rules[i].indexOf(":") + 2);
        	
        	ArrayList<Node> ruleList = new ArrayList<Node>();
        	if (rule.indexOf("\"") != -1) {
        		char ch = rule.charAt(1);
        		ruleList.add(new Node(ch));
        	} else {
        		String[] orRules = rule.split("\\| ");
        		for (int j = 0; j < orRules.length; j++) {
        			String[] strIds = orRules[j].split(" ");
        			int[] ids = new int[strIds.length];
        			
        			for (int k = 0; k < strIds.length; k++) {
        				ids[k] = Integer.parseInt(strIds[k]);
        			}
        			ruleList.add(new Node(ids));
        		}
        	}
        	ruleMap.put(idx, ruleList);
        }
        
        HashSet<String> validStrings = new HashSet<>();
        LinkedList<Node> pending = new LinkedList<>();
        for (Node node : ruleMap.get(0)) pending.add(node);
        
        while (!pending.isEmpty()) {
        	Node node = pending.removeFirst();
        	
        	ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
        	combinations.add(new ArrayList<Integer>());
        	
        	for (int i = 0; i < node.subNodes.length; i++) {
        		int id = node.subNodes[i];
        		
        		ArrayList<Node> childs = ruleMap.get(id);
        		if (childs.size() > 1) {
        			ArrayList<ArrayList<Integer>> toAdd = new ArrayList<>();
        			for (int j = 0; j < childs.size() - 1; j++) {
        				for (ArrayList<Integer> combination: combinations) {
        					toAdd.add((ArrayList<Integer>) combination.clone());
        				}
        			}
        			
        			combinations.addAll(toAdd);
        		}
        		
        		for (int j = 0; j < childs.size(); j++) {
        			Node child = childs.get(j);
        			
        			for (int k = 0; k < combinations.size() / childs.size(); k++) {
        				ArrayList<Integer> list = combinations.get(k + (combinations.size() / childs.size()) * j);
        				if (child.subNodes == null) {
        					list.add(id);
        				} else {
        					for (int t = 0; t < child.subNodes.length; t++) {
        						list.add(child.subNodes[t]);
        					}
        				}
        			}
        		}
        	}
        	
        	for(ArrayList<Integer> combination : combinations) {
        		Node n = new Node(combination);
        		if(n.getString() != null) {
        			validStrings.add(n.getString());
        		} else {
        			pending.add(new Node(combination));
        		}
        	}
    	}
        
        int valid = 0;
        for (String msg : messages) {
        	if (validStrings.contains(msg)) valid++;
        }
        
        System.out.println("num valid: " + valid);
	}
		
	static class Node {
		char ch = ' ';
		int[] subNodes = null;
		
		Node(char ch) {
			this.ch = ch;
		}
		
		Node(int[] subNodes) {
			this.subNodes = subNodes;
		}

		Node(ArrayList<Integer> subNodes) {
			this.subNodes = new int[subNodes.size()];
			for(int i = 0; i < subNodes.size(); i++) {
				this.subNodes[i] = subNodes.get(i);
			}
		}
		
		public String getString() {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < subNodes.length; i++) {
				ArrayList<Node> list = ruleMap.get(subNodes[i]);
				if (list.size() > 1) return null;
				for (Node n : list) {
					if (n.subNodes != null) return null;
					sb.append(n.ch);
				}
			}
			return sb.toString();
		}
	}
}