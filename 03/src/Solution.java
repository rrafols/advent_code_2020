import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }

        ArrayList<String> mapStr = new ArrayList<String>();
        while(sc.hasNext()) {
        	mapStr.add(sc.nextLine());
        }
        
        char[][]map = new char[mapStr.size()][];
        for (int i = 0; i < mapStr.size(); i++) {
        	map[i] = mapStr.get(i).toCharArray();
		}

// simple solution

//        int x = 0;
//        int y = 0;
//        int treeCount = 0;
//        boolean done = false;
//        while(!done) {
//        	if (map[y][x] == '#') treeCount++;
//        	x = (x + 3) % map[y].length;
//        	y += 1;
//        	
//        	if (y >= map.length) done = true;
//        }
        
        int[] slopes = new int[] {
        		1, 1,
        		3, 1,
        		5, 1,
        		7, 1,
        		1, 2
        };  
        
        int accTrees = 1;
        for (int i = 0; i < slopes.length; i+= 2) {
	        int dX = slopes[i    ];
	        int dY = slopes[i + 1];
	        int x = 0;
	        int y = 0;
	        int treeCount = 0;
	        boolean done = false;
	        while(!done) {
	        	if (map[y][x] == '#') treeCount++;
	        	x = (x + dX) % map[y].length;
	        	y += dY;
	        	
	        	if (y >= map.length) done = true;
	        }
	        System.err.println("slope: " + dX + "," + dY + " trees: " + treeCount);
	        accTrees *= treeCount;
        }
        
        System.out.println("number of trees: " + accTrees);
    }
	
}