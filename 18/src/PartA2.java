import java.io.File;
import java.util.Scanner;

public class PartA2 {
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
		Node root = new Node(-1);
        
        for (int i = start; i < end; i++) {
        	char ch = str.charAt(i);
        	if (ch == ' ') continue;

        	if (ch >= '0' && ch <= '9') root.addChild(new Node((int) (ch - '0')));
        	
        	if (ch == '*' || ch == '+') {
        		if (root.value == -1 && root.op == ' ') root.op = ch;
        		else {
        			Node parent = new Node(ch);
        			parent.left = root;
        			root = parent;
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
    			
    			root.addChild(process(str, i + 1, close + 1));
    			i = close;
        	}
        }
        return root;
	}
	
	static class Node {
		long value;
		char op;
		Node left;
		Node right;
		
		Node(long value) {
			this.value = value;
			this.op = ' ';
		}
		
		Node(char op) {
			this.op = op;
			this.value = -1;
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