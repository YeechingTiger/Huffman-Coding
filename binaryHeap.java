public class binaryHeap
//<Node extends Comparable<Node>> 
{
	public Node[] bHeap;
	private int size = 0;

	public binaryHeap() {
		bHeap = new Node[1000001];
		bHeap[0] = null;
	}

	public int size() {
		return size;
	}

	public void insert(Node Node) {
		size = size + 1;
		bHeap[size] = Node;
		bottomUp();
	}

	public Node deleteM() {
		Node min = bHeap[1];
		exchange(1, size);
		size = size - 1;
		topDown();
		return min;
	}

	private void bottomUp() {
		int pointer = size;
		while (pointer > 1 && less(pointer, pointer/2)) {
			exchange(pointer, pointer/2);
			pointer = pointer/2;
		}
	}

	private void topDown() {
		int pointer = 1;
		while (2 * pointer <= size) {
			int childrenMin = 2 * pointer;
			if (!less(childrenMin, childrenMin + 1) && childrenMin < size) {
				childrenMin++;
			}

			if (less(pointer, childrenMin)) {
				break;
			} else {
				exchange(pointer, childrenMin);
			}
			pointer = childrenMin;

		}
	}

	private boolean less(int i, int j) {
		return bHeap[i].value < bHeap[j].value;
	}

	private void exchange(int i, int j) {
		Node temp = bHeap[i];
		bHeap[i] = bHeap[j];
		bHeap[j] = temp;
	}
}