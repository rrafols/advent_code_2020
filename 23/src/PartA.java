import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class PartA {
	public static void main(String[] args) {
		CircularLinkedList<Integer> list = new CircularLinkedList<>(Arrays.asList(new Integer[] {4, 6, 3, 5, 2, 8, 1, 7, 9}));
		ArrayList<Node<Integer>> removed = new ArrayList<>();
		
		int moves = 0;
		Node<Integer> current = list.head;
		while (moves++ < 100) {
			removed.clear();
			for (int i = 0; i < 3; i++) removed.add(list.removeNext(current));
				
			Node<Integer> destination = null;
			int destinationValue = current.value - 1;
			
			while((destination = list.contains(destinationValue)) == null) {
				destinationValue--;
				if (destinationValue <= 0) destinationValue = 9;
			}
			
			for (int i = 0; i < 3; i++) list.insertNext(destination, removed.get(2 - i));
			current = current.next;
		}
		
		list.transversePrint(list.contains(1).next);
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
		
		void transversePrint(Node<T> from) {
			Node<T> current = from;
			
			boolean done = false;
			while(!done) {
				System.out.print(current.value);
				current = current.next;
				done = (current.next == from);
			}
			System.out.println();
		}
	}
}