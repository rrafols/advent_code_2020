import java.io.File;
import java.util.ArrayList;
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
		for (int j = x - 1; j >= 0; j--) { if (map[y * w + j] == 'L') break; if (map[y * w + j] == '#') return false; }
		for (int j = x + 1; j < w ; j++) { if (map[y * w + j] == 'L') break; if (map[y * w + j] == '#') return false; }
		
		for (int i = y - 1; i >= 0; i--) { if (map[i * w + x] == 'L') break; if (map[i * w + x] == '#') return false; }
		for (int i = y + 1; i < h ; i++) { if (map[i * w + x] == 'L') break; if (map[i * w + x] == '#') return false; }
		
		for (int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--) { if (map[i * w + j] == 'L') break; if (map[i * w + j] == '#') return false; }
		for (int i = y - 1, j = x + 1; i >= 0 && j < w ; i--, j++) { if (map[i * w + j] == 'L') break; if (map[i * w + j] == '#') return false; }
		for (int i = y + 1, j = x - 1; i < h && j >= 0 ; i++, j--) { if (map[i * w + j] == 'L') break; if (map[i * w + j] == '#') return false; }
		for (int i = y + 1, j = x + 1; i < h && j < w  ; i++, j++) { if (map[i * w + j] == 'L') break; if (map[i * w + j] == '#') return false; }
		
		return true;
	}
	
	private static boolean shouldEmpty(char[] map, int x, int y, int w, int h) {
		int adjacent = 0;
		
		for (int j = x - 1; j >= 0; j--) { if (map[y * w + j] == 'L') break; if (map[y * w + j] == '#') { adjacent++; break; } }
		for (int j = x + 1; j < w ; j++) { if (map[y * w + j] == 'L') break; if (map[y * w + j] == '#') { adjacent++; break; } }
		
		for (int i = y - 1; i >= 0; i--) { if (map[i * w + x] == 'L') break; if (map[i * w + x] == '#') { adjacent++; break; } }
		for (int i = y + 1; i < h ; i++) { if (map[i * w + x] == 'L') break; if (map[i * w + x] == '#') { adjacent++; break; } }
		
		for (int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--) { if (map[i * w + j] == 'L') break; if (map[i * w + j] == '#') { adjacent++; break; } }
		for (int i = y - 1, j = x + 1; i >= 0 && j < w ; i--, j++) { if (map[i * w + j] == 'L') break; if (map[i * w + j] == '#') { adjacent++; break; } }
		for (int i = y + 1, j = x - 1; i < h && j >= 0 ; i++, j--) { if (map[i * w + j] == 'L') break; if (map[i * w + j] == '#') { adjacent++; break; } }
		for (int i = y + 1, j = x + 1; i < h && j < w  ; i++, j++) { if (map[i * w + j] == 'L') break; if (map[i * w + j] == '#') { adjacent++; break; } }
		
		return adjacent >= 5;
	}
}