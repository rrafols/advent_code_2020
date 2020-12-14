import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
        
        HashMap<Long, Long> mem = new HashMap<Long, Long>();
        
        String mask = "";
        while(sc.hasNext()) {
        	String str = sc.nextLine();
        	
        	if(str.startsWith("mask")) {
        		mask = str.split(" = ")[1];
        	} else {
        		String[] pv = str.split(" = ");
        		
        		long pos = Long.parseLong(pv[0].substring(0, pv[0].length() - 1).split("\\[")[1]);
        		long value = Long.parseLong(pv[1]);
        		
        		String posBinary = Long.toBinaryString(pos);
        		while(posBinary.length() < 36) posBinary = "0" + posBinary;

        		char[] posArray = posBinary.toCharArray();
        		for (int i = 0; i < posBinary.length(); i++) if (mask.charAt(i) != '0') posArray[i] = mask.charAt(i);
        		
        		ArrayList<Long> posList = new ArrayList<Long>();
        		LinkedList<char[]> pending = new LinkedList<char[]>();
        		pending.add(posArray);
        		
        		while(!pending.isEmpty()) {
        			char[] posIt = pending.removeFirst();
        			
        			boolean valid = true;
        			for (int i = 0; i < posIt.length && valid; i++) {
        				if(posIt[i] == 'X') {
        					char[] tmp1 = posIt.clone();
        					
        					tmp1[i] = '1';
        					posIt[i] = '0';
        					
        					pending.add(tmp1);
        					pending.add(posIt);
        					valid = false;
        				}
        			}
        			
        			if (valid) posList.add(Long.parseLong(new String(posIt), 2));
        		}
        		
        		for (long mpos : posList) mem.put(mpos, value);
        	}
        }
        
        long acc = 0;
        for(Long k : mem.keySet()) acc += mem.get(k);
        
        System.out.println("result: " + acc);
	}
}