import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class PartB {
	public static void main(String[] args) {
		CircularLinkedList<Integer> list = new CircularLinkedList<>(Arrays.asList(new Integer[] {4, 6, 3, 5, 2, 8, 1, 7, 9}));
		ArrayList<Node<Integer>> removed = new ArrayList<>();
		for(int i = 10; i <= 1000000; i++) list.addNode(i);
		
		int moves = 0;
		Node<Integer> current = list.head;
		while (moves++ < 10000000) {
			removed.clear();
			for (int i = 0; i < 3; i++) removed.add(list.removeNext(current));
				
			Node<Integer> destination = null;
			int destinationValue = current.value - 1;
			
			while((destination = list.contains(destinationValue)) == null) {
				destinationValue--;
				if (destinationValue <= 0) destinationValue = 1000000;
			}
			
			for (int i = 0; i < 3; i++) list.insertNext(destination, removed.get(2 - i));
			current = current.next;
		}
		
		BigInteger result = BigInteger.valueOf(list.contains(1).next.value);
		result = result.multiply(BigInteger.valueOf(list.contains(1).next.next.value));
		System.out.println("result: " + result);
	}
	
	static class Node<T> {
		T value;
		Node<T> next;
		
		Node(T value) {
			this.value = value;
		}
	}
	
	static class CircularLinkedList<T> {
		HashMap<T, Node<T>> nodeByValue = new HashMap<T, Node<T>>();
		Node<T> head;
		Node<T> tail;
		
		CircularLinkedList(Collection<T> list) {
			for (T elem : list) addNode(elem);
		}
		
		void addNode(T value) {
			Node<T> node = new Node<>(value);
			nodeByValue.put(value, node);
			
			if (head == null) {
				head = node;
			} else {
				tail.next = node;
			}
			
			tail = node;
			tail.next = head;
		}
		
		Node<T> contains(T value) {
			return nodeByValue.get(value);
		}
		
		Node<T> removeNext(Node<T> node) {
			Node<T> removed = node.next;
			
			if (tail == node.next) tail = node;
			if (node.next == head) head = node.next.next;
			
			node.next = node.next.next;
			nodeByValue.remove(removed.value);
			return removed;
		}
		
		void insertNext(Node<T> node, Node<T> newNode) {
			newNode.next = node.next;
			node.next = newNode;
			if (tail == node) tail = newNode;
			
			nodeByValue.put(newNode.value, newNode);
		}
	}
}