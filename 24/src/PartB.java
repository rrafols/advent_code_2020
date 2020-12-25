import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class PartB {
	public static void main(String[] args) {
		HashSet<String> blackTiles = new HashSet<>();
		Scanner sc = null;
		
		try {
        	sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
		
		while(sc.hasNext()) {
			String line = sc.nextLine();
			int x = 1000;
			int y = 1000;
			
			for (int i = 0; i < line.length(); i++) {
				switch(line.charAt(i)) {
					case 'e':
						x += 2;
						break;
						
					case 'w':
						x -= 2;
						break;
						
					case 's':
						y++;
						x += line.charAt(++i) == 'e' ? 1 : -1;
						break;
						
					case 'n':
						y--;
						x += line.charAt(++i) == 'e' ? 1 : -1;
						break;
				}
			}
		
			String idx = getId(x, y);
			if (blackTiles.contains(idx)) blackTiles.remove(idx);
			else blackTiles.add(idx);
		}
		
		HashSet<String> candidates = new HashSet<String>();
		int days = 0;
		while(days++ < 100) {
			HashSet<String> newTileMap = new HashSet<String>();
			candidates.clear();
			
			for (String idx : blackTiles) {
				int adjacent = adjacentBlackTiles(idx, blackTiles, candidates);
				if (adjacent == 1 || adjacent == 2) newTileMap.add(idx);
			}
			
			for (String idx : candidates) {
				int adjacent = adjacentBlackTiles(idx, blackTiles, null);
				if (adjacent == 2) newTileMap.add(idx);
			}
			
			blackTiles = newTileMap;
			
			System.out.println(days + " :: " +blackTiles.size());
		}
		
		System.out.println(blackTiles.size());		
	}
	
	private static String getId(int x, int y) {
		return x + "_" + y;
	}
	
	private static int adjacentBlackTiles(String idx, HashSet<String> tileMap, HashSet<String> candidates) {
		String[] coords = idx.split("_");
		return adjacentBlackTiles(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), tileMap, candidates);
	}
	
	private static int adjacentBlackTiles(int x, int y, HashSet<String> tileMap, HashSet<String> candidates) {
		int count = 0;
		
		count += tileMap.contains(getId(x + 2, y    )) ? 1 : 0;
		count += tileMap.contains(getId(x - 2, y    )) ? 1 : 0;
		count += tileMap.contains(getId(x + 1, y + 1)) ? 1 : 0;
		count += tileMap.contains(getId(x + 1, y - 1)) ? 1 : 0;
		count += tileMap.contains(getId(x - 1, y + 1)) ? 1 : 0;
		count += tileMap.contains(getId(x - 1, y - 1)) ? 1 : 0;
		
		if (candidates != null) {
			String idx = getId(x + 2, y);
			if (!tileMap.contains(idx)) candidates.add(idx);
			idx = getId(x - 2, y);
			if (!tileMap.contains(idx)) candidates.add(idx);
			idx = getId(x + 1, y + 1);
			if (!tileMap.contains(idx)) candidates.add(idx);
			idx = getId(x + 1, y - 1);
			if (!tileMap.contains(idx)) candidates.add(idx);
			idx = getId(x - 1, y + 1);
			if (!tileMap.contains(idx)) candidates.add(idx);
			idx = getId(x - 1, y - 1);
			if (!tileMap.contains(idx)) candidates.add(idx);
		}
		
		return count;
	}
}