import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class PartA {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
        	sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        HashMap<Long, String> mem = new HashMap<Long, String>();
        
        String mask = "";
        while(sc.hasNext()) {
        	String str = sc.nextLine();
        	
        	if(str.startsWith("mask")) {
        		mask = str.split(" = ")[1];
        	} else {
        		String[] pv = str.split(" = ");
        		
        		long pos = Long.parseLong(pv[0].substring(0, pv[0].length() - 1).split("\\[")[1]);
        		
        		String valueBinary = Long.toBinaryString(Long.parseLong(pv[1]));
        		while(valueBinary.length() < 36) valueBinary = "0" + valueBinary;

        		char[] valueArray = valueBinary.toCharArray();
        		for (int i = 0; i < valueBinary.length(); i++) if (mask.charAt(i) != 'X') valueArray[i] = mask.charAt(i);
        		
        		mem.put(pos, new String(valueArray));
        	}
        }
        
        long acc = 0;
        for(Long k : mem.keySet()) acc += Long.parseLong(mem.get(k), 2);
        
        System.out.println("result: " + acc);
	}
}