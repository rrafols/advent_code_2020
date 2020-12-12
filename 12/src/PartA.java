import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PartA {
	private static final int EAST = 1;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        ArrayList<Inst> list = new ArrayList<Inst>();
        while(sc.hasNext()) list.add(new Inst(sc.nextLine()));
        
        HashMap<Integer, Mult> mult = new HashMap<Integer, Mult>();
        mult.put((int) 'N', new Mult( 0, -1));
        mult.put((int) 'S', new Mult( 0,  1));
        mult.put((int) 'E', new Mult( 1,  0));
        mult.put((int) 'W', new Mult(-1,  0));
        
        mult.put(0, new Mult( 0, -1));
        mult.put(2, new Mult( 0,  1));
        mult.put(1, new Mult( 1,  0));
        mult.put(3, new Mult(-1,  0));
        
        int x = 0;
        int y = 0;
        int dir = EAST;
        for (Inst i : list) {
        	switch(i.dir) {
        		case 'N':
        		case 'S':
        		case 'E':
        		case 'W':
        			Mult m = mult.get((int) i.dir);
        			x += m.dx * i.amount;
        			y += m.dy * i.amount;
        			break;
        			
        		case 'F':
        			Mult d = mult.get(dir);
        			x += d.dx * i.amount;
        			y += d.dy * i.amount;
        			break;
        			
        		case 'L':
        			dir = Math.floorMod(dir - i.amount / 90, 4);
        			break;
        			
        		case 'R':
        			dir = (dir + i.amount / 90) % 4;
        			break;
        	}
        }
        
        System.out.println(x + ", " + y + " dist: " + (Math.abs(x) + Math.abs(y)));
	}
	
	static class Mult {
		int dx;
		int dy;
		
		Mult(int dx, int dy) { this.dx = dx; this.dy = dy; }
	}
	
	static class Inst {
		char dir;
		int amount;
		
		Inst(String str) {
			dir = str.charAt(0);
			amount = Integer.parseInt(str.substring(1));
		}
	}
}