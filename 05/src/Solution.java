import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }

      
        // map all the seats to check those that are available at the end
        HashSet<Integer> seatMap = new HashSet<Integer>();
        for (int seatID = 0; seatID < 128 * 8; seatID++) {
        	seatMap.add(seatID);
        }
        
        // keep track of seats that are not available to quickly check if +1 / -1 were on the list
        HashSet<Integer> removedSeats = new HashSet<Integer>();
        
        int maxSeatID = 0;
        while (sc.hasNext()) {
        	int miny = 0;
        	int maxy = 127;
        	int minx = 0;
        	int maxx = 7;
        	
        	String boardingPass = sc.nextLine();

        	for (int i = 0; i < boardingPass.length(); i++) {
        		char ch = boardingPass.charAt(i);
        		
        		switch(ch) {
		    		case 'F':
		    			maxy = (maxy + miny) / 2;
		    			break;
	    			case 'B':
		    			miny = (maxy + miny + 1) / 2;
		    			break;
		    			
		    		case 'L':
		    			maxx = (maxx + minx) / 2;
		    			break;
		    		case 'R':
		    			minx = (maxx + minx + 1) / 2;
		    			break;
		    			
        		}
        	}
        	
        	int seatID = miny * 8 + minx;
        	
        	seatMap.remove(seatID);
        	removedSeats.add(seatID);
        	if (seatID > maxSeatID) maxSeatID = seatID;
        }
        
        System.out.println("highest seatID: " + maxSeatID);
        
        for (int availableSeat : seatMap) {
        	if (removedSeats.contains(availableSeat - 1) && removedSeats.contains(availableSeat + 1)) {
        		System.out.println("available seat: " + availableSeat);
        	}
        }
	}
}
