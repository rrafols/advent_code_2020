import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
        
        int x = 10;
        int y = -1;
        int sx = 0;
        int sy = 0;
        
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
        			int dx = x * i.amount;
        			int dy = y * i.amount;
        			sx += dx;
        			sy += dy;
        			break;
        			
        		case 'L':
        		case 'R':
        			int q = i.amount/90;
                	
        			for(int j = 0; j < q; j++) {
        				int tmp = y;
        				
        				y = x * (i.dir == 'L' ? -1 : 1);
        				x = tmp * (i.dir == 'R' ? -1 : 1);
        			}
        			break;
        	}
        }
        
        System.out.println(sx + ", " + sy + " dist: " + (Math.abs(sx) + Math.abs(sy)));
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