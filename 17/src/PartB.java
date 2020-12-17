import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
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
        
        ArrayList<String> mapStr = new ArrayList<String>();
        while (sc.hasNext()) mapStr.add(sc.nextLine());
        

        HashSet<String> map = new HashSet<String>();
        for (int i = 0; i < mapStr.size(); i++) {
        	for (int j = 0; j < mapStr.get(i).length(); j++) {
        		if (mapStr.get(i).charAt(j) == '#') {
        			map.add(getID(j, i, 0, 0));
        		}
        	}
        }
        
        int it = 0;
        while (it < 6) {
        	HashSet<String> newMap = new HashSet<String>();
        	HashSet<String> candidates = new HashSet<String>();
        	
        	for (String id : map) {
        		int[] coords = getCoords(id);      		
        		int nActive = getActiveNeighbours(map, coords[0], coords[1], coords[2], coords[3], candidates);
        		if (nActive == 2 || nActive == 3) newMap.add(id);
        	}
        	
    		for (String candidateId : candidates) {
    			int[] coords = getCoords(candidateId);
    			
        		if (getActiveNeighbours(map, coords[0], coords[1], coords[2], coords[3], null) == 3) {
        			newMap.add(candidateId);
        		}
    		}
        	
        	System.out.println("iteration " + (it + 1) + " active cubes: " + newMap.size());
        	map = newMap;
        	it++;
        }
	}

	private static int getActiveNeighbours(HashSet<String> map, int x, int y, int z, int w, HashSet<String> pendingCandidates) {
		int nActive = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				for (int k = -1; k <= 1; k++) {
					for (int t = -1; t <= 1; t++) {
						if (i == 0 && j ==0 && k == 0 && t == 0) continue;
						
						if (map.contains(getID(x + j, y + i, z + k, w + t))) nActive++;
			    		else if (pendingCandidates != null) pendingCandidates.add(getID(x + j, y + i, z + k, w + t));
					}
				}
			}
		}
		return nActive;
	}

	private static String getID(int x, int y, int z, int w) {
		return x + "_" + y + "_" + z + "_" + w;
	}
	
	private static int[] getCoords(String id) {
		String[] strCoords = id.split("_");
		int[] coords = new int[strCoords.length];
		
		for (int i = 0; i < strCoords.length; i++) coords[i] = Integer.parseInt(strCoords[i]);
		return coords;
	}
}