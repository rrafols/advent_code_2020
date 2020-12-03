import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
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
        
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        while(sc.hasNext()) {
        	int N = sc.nextInt(); sc.nextLine();
        	list.add(N);
        }
        
        list.sort(new Comparator<Integer>() {
        	@Override
        	public int compare(Integer o1, Integer o2) {
        		return o1.compareTo(o2);
        	}
		});

// simple solution

//        for(int i = 0; i < list.size(); i++) {
//        	for(int j = list.size() - 1; j > i; j--) {
//        		int sum = list.get(i) + list.get(j);
//        		if(sum < 2020) break;
//        		if(sum == 2020) {
//        			System.out.println(list.get(i) + ", " + list.get(j) + " == " + (list.get(i) * list.get(j)));
//        			break;
//        		}
//        	}
//        }

        for(int i = 0; i < list.size(); i++) {
        	int A = list.get(i);
        	for(int j = i + 1; j < list.size(); j++) {
        		int B = list.get(j);
        		
        		int partSum = A + B;
        		if(partSum < 2020) {
        			for(int k = j + 1; k < list.size(); k++) {
        				int C = list.get(k);
        				
        				int sum = partSum + C;
        				if (sum == 2020) {
        					System.out.println(A + ", " + B + ", " + C + " == " + (A * B * C));
        				}
        			}
        		}
        	}
        }
	}
}