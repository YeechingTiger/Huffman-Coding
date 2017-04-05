// @Author: Xing He  UFID: 5901-7218
// Project for Advanced Data Structure Course in University of Florida
//Final Version Date: April 4, 2017

public class FourWayHeap{
	public Node[] fourWayHeap;
	private int size = 0;

	public FourWayHeap() {
		fourWayHeap = new Node[1000000];
	}

	public int size() {
		return size;
	}

	public void insert(Node node) {
		size++;
		fourWayHeap[size - 1] = node;
		bottomUp();
	}

	public Node deleteM() {
		Node min = fourWayHeap[0];
		exchange(0, size - 1);
		size = size - 1;
		topDown();
		return min;
	}

	private void bottomUp() {
		int pointer = size - 1;
		while (pointer > 0 && less(pointer, (pointer- 1)/4)) {
			exchange(pointer, (pointer - 1)/4);
			pointer = (pointer - 1)/4;
		}
	}

	private void topDown() {
		int pointer = 0;
		while (4 * pointer < size - 1) {
			int childrenMin = 4 * pointer + 1; 
			if (4 * pointer + 2 <= size - 1 && !notlarger(childrenMin, 4 * pointer + 2)) {
				childrenMin = 4 * pointer + 2;	
			}
			if (4 * pointer + 3 <= size - 1 && !notlarger(childrenMin, 4 * pointer + 3)) {
				childrenMin = 4 * pointer + 3;
			}
			if (4 * pointer + 4 <= size - 1 && !notlarger(childrenMin, 4 * pointer + 4)) {
				childrenMin = 4 * pointer + 4;
			}
			if (notlarger(pointer, childrenMin)) {
				break;
			} else {
				exchange(pointer, childrenMin);
			}
			pointer = childrenMin;
		}
	}

	private boolean notlarger(int i, int j) {
		return fourWayHeap[i].value <= fourWayHeap[j].value;
	}

	private boolean less(int i, int j) {
		return fourWayHeap[i].value < fourWayHeap[j].value;
	}

	private void exchange(int i, int j) {
		Node temp = fourWayHeap[i];
		fourWayHeap[i] = fourWayHeap[j];
		fourWayHeap[j] = temp;
	}

}