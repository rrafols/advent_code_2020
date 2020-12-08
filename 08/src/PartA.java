import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PartA {

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
        
        int acc = 0;
        int pc = 0;
        
        while(true) {
        	Instruction in = bootCode.get(pc);
        	
        	if (in.executed) {
        		System.out.println(acc);
        		return;
        	}
        	
        	in.executed = true;
        	switch (in.op) {
	        	case "acc":
	        		acc += in.value;
	        		pc++;
	        		break;
	        	case "nop":
	        		pc++;
	        		break;
	        	case "jmp":
	        		pc += in.value;
	        		break;
        	}
        }
	}
	
	static class Instruction {
		String op;
		int value;
		boolean executed;
		
		public Instruction(String unparsed) {
			this.executed = false;
			
			String[] parts = unparsed.split(" ");
			this.op = parts[0].trim();
			this.value = Integer.parseInt(parts[1]);
		}
	}
}