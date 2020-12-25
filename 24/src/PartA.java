import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class PartA {
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
		
			String idx = x + "_" + y;
			if (blackTiles.contains(idx)) blackTiles.remove(idx);
			else blackTiles.add(idx);
		}
		
		System.out.println(blackTiles.size());		
	}
}