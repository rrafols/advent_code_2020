import java.io.File;
import java.util.Scanner;

public class PartB {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        try {
        	sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        long result = 0;
        while(sc.hasNext()) result += processExpression(sc.nextLine());
        
        System.out.println(result);
	}
	
	public static long processExpression(String str) {
		Node tree = process(str, 0, str.length());
		return tree.getValue();
	}
	
	public static Node process(String str, int start, int end) {
        Node current = new Node(null, -1);
        
        for (int i = start; i < end; i++) {
        	char ch = str.charAt(i);
        	if (ch == ' ') continue;

        	if (ch >= '0' && ch <= '9') current.addChild(new Node(current, (int) (ch - '0')));
        	
        	if (ch == '*') {
        		if (current.value == -1 && current.op == ' ') current.op = ch;
        		else {
        			Node parent = new Node(current.parent, ch);
        			parent.left = current;
        			if (current.parent != null) {
	        			current.parent.right = parent;
	        			current.parent = parent;
        			}
        			
        			current = parent;
        		}
        	}
        	
        	if (ch == '+') {
        		if (current.value == -1 && current.op == ' ') current.op = ch;
        		else {
        			if (current.op == '+') {
        				Node parent = new Node(current.parent, ch);
            			parent.left = current;
            			if (current.parent != null) {
            				current.parent.right = parent;
            				current.parent = parent;
            			}
            			
            			current = parent;
        			} else {
		        		Node child = new Node(current, ch);
		        		child.left = current.right;
		        		current.right = child;
		        		current = child;
        			}
        		}
        	}
        	
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
    			
    			current.addChild(process(str, i + 1, close + 1));
    			i = close;
        	}
        }
        return current.getRoot();
	}
	
	static class Node {
		Node parent;
		long value;
		char op;
		Node left;
		Node right;
		
		Node(Node parent, long value) {
			this.parent = parent;
			this.value = value;
			this.op = ' ';
		}
		
		Node(Node parent, char op) {
			this.parent = parent;
			this.op = op;
			this.value = -1;
		}
		
		Node getRoot() {
			if (parent != null) return parent.getRoot();
			else return this;
		}
		
		void addChild(Node child) {
			if (left == null) left = child;
			else if (right == null) right = child;
		}
		
		long getValue() {
			if (value != -1) return value;
			else {
				long lvalue = left.getValue();
				long rvalue = right.getValue();
				if (op == '*') return lvalue * rvalue;
				else return lvalue + rvalue;
			}
		}
	}
}