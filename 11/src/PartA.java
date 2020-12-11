import java.io.File;
import java.util.ArrayList;
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
        
        ArrayList<String> strList = new ArrayList<String>();
        while (sc.hasNext()) strList.add(sc.nextLine());
        
        int width = strList.get(0).length();
        int height = strList.size();
        char[] map = new char[width * height];
        
        for (int i = 0; i < strList.size(); i++) {
        	String line = strList.get(i);
        	for (int j = 0; j < line.length(); j++) {
        		map[i * width + j] = line.charAt(j);
        	}
        }
        
        char[] map2 = new char[width * height];
        while (true) {
        	int changes = 0;
        	
        	for (int i = 0; i < height; i++) {
        		for (int j = 0; j < width; j++) {
        			int ofs = i * width + j;
        			char seat = '.';
        			
        			if (map[ofs] == 'L') seat = shouldOccupy(map, j, i, width, height) ? '#' : 'L';
        			else if (map[ofs] == '#') seat = shouldEmpty(map, j, i, width, height) ? 'L' : '#';
        			
        			if (map[ofs] != seat) changes++;
        			map2[ofs] = seat;
        		}
        	}
        	
        	if (changes == 0) {
        		int total = 0;
        		for (int i = 0; i < map.length; i++) {
        			if (map[i] == '#') total++;
        		}
        		
        		System.out.println("occupied: " + total);
        		return;
        	}
        	
        	char[] tmp = map;
        	map = map2;
        	map2 = tmp;
        }
	}
	
	private static boolean shouldOccupy(char[] map, int x, int y, int w, int h) {
		if (x > 0 && map[y * w + x - 1] == '#') return false;
		if (x < w - 1 && map[y * w + x + 1] == '#') return false;
		
		if (y > 0) {
			if (map[(y - 1) * w + x] == '#') return false;
			if (x > 0 && map[(y - 1) * w + x - 1] == '#') return false;
			if (x < w - 1 && map[(y - 1) * w + x + 1] == '#') return false;
		}
		
		if (y < h - 1) {
			if (map[(y + 1) * w + x] == '#') return false;
			if (x > 0 && map[(y + 1) * w + x - 1] == '#') return false;
			if (x < w - 1  && map[(y + 1) * w + x + 1] == '#') return false;
		}
		
		return true;
	}
	
	private static boolean shouldEmpty(char[] map, int x, int y, int w, int h) {
		int adjacent = 0;
		
		if (x > 0 && map[y * w + x - 1] == '#') adjacent++;
		if (x < w - 1&& map[y * w + x + 1] == '#') adjacent++;
		
		if (y > 0) {
			if (map[(y - 1) * w + x] == '#') adjacent++;
			if (x > 0 && map[(y - 1) * w + x - 1] == '#') adjacent++;
			if (x < w - 1 && map[(y - 1) * w + x + 1] == '#') adjacent++;
		}
		
		if (y < h - 1) {
			if (map[(y + 1) * w + x] == '#') adjacent++;
			if (x > 0 && map[(y + 1) * w + x - 1] == '#') adjacent++;
			if (x < w - 1 && map[(y + 1) * w + x + 1] == '#') adjacent++;
		}
		
		return adjacent >= 4;
	}
}