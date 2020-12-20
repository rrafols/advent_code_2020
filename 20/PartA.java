import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.soap.Node;

public class PartA {
	private static HashMap<String, ArrayList<Integer>> borderTile = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        try {
        	sc = new Scanner(new File("bin/i.txt")).useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        while(sc.hasNext()) {
        	String[] lines = sc.next().split("\\n");
			String idStr = lines[0].split(" ")[1];
			int id = Integer.parseInt(idStr.substring(0, idStr.length() - 1));
			char[] tmp = new char[lines.length - 1];
			for (int i = 1; i < lines.length; i++) tmp[i - 1] = lines[i].charAt(0);
			addId(new String(tmp), id); //leftBorder
			
			for (int i = 1; i < lines.length; i++) tmp[i - 1] = lines[i].charAt(lines[i].length() - 1);
			addId(new String(tmp), id);	//rightBorder

			addId(lines[1], id);
			addId(lines[lines.length - 1], id);
			
        	if (sc.hasNext()) sc.nextLine();
        }
        
        long mul = 1;
        HashSet<Integer> count = new HashSet<Integer>();
        for (String border: borderTile.keySet()) {
        	ArrayList<Integer> list = borderTile.get(border);
        	if (list.size() == 1) {
        		int id = list.get(0);
        		
        		if (!count.contains(id)) count.add(id);
        		else mul *= id;
        	}
        }
        
        System.out.println(mul);
	}
	
	private static void addId(String border, int id) {
		String reverse = new StringBuilder(border).reverse().toString();
		
		if (!borderTile.containsKey(border) && !borderTile.containsKey(reverse)) {
			borderTile.put(border, new ArrayList<Integer>());
		}
		
		String used = borderTile.containsKey(border) ? border : reverse;
		borderTile.get(used).add(id);
	}
}