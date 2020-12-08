import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class PartB {

	static ArrayList<Instruction> bootCode = new ArrayList<Instruction>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
        	sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        while (sc.hasNext()) bootCode.add(new Instruction(sc.nextLine()));
        
        // identify unique jmp or nops executed
        HashSet<Integer> seen = new HashSet<Integer>();
        
        int acc = 0;
        int pc = 0;
        
        while(true) {
        	if (pc == bootCode.size()) {
        		System.out.println(acc);
        		return;
        	}
        	
        	Instruction in = bootCode.get(pc);
        	switch (in.op) {
	        	case "acc":
	        		acc += in.value;
	        		pc++;
	        		break;
	        	case "nop":
	        		pc++;
	        		break;
	        	case "jmp":
	        		if (pc == 210) {
	        			pc++;
	        		} else {
		        		if (!seen.contains(pc)) {
		        			seen.add(pc);
		        			System.out.println(pc + " " + in.op + " " + in.value);
		        		}
		        		pc += in.value;
	        		}
	        		break;
        	}
        }
	}
	
	static class Instruction {
		String op;
		int value;
		
		public Instruction(String unparsed) {
			String[] parts = unparsed.split(" ");
			this.op = parts[0].trim();
			this.value = Integer.parseInt(parts[1]);
		}
	}
}