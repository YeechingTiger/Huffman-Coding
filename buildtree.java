// @Author: Xing He  UFID: 5901-7218
// Project for Advanced Data Structure Course in University of Florida
//Final Version Date: April 4, 2017

import java.util.ArrayList;

public class BuildTree {
	BinaryHeap bH = new BinaryHeap();
	FourWayHeap fH = new FourWayHeap();
	PairingHeap pH = new PairingHeap();

	public void buildtreeb(ArrayList<Integer> freqTable) {
		for (int i = 0; i < 1000000; i++) {
			if (freqTable.get(i) != 0) {
				Node temp = new Node(i ,freqTable.get(i));
				bH.insert(temp);
			}
		}
		while (bH.size() > 1) {
			Node node1 = bH.deleteM();
			Node node2 = bH.deleteM();
			Node node3 = Node.meld(node1, node2);
			bH.insert(node3);
		}
	}

	public void buildtreef(ArrayList<Integer> freqTable) {
		for (int i = 0; i < 1000000; i++) {
			if (freqTable.get(i) != 0) {
				Node temp = new Node(i ,freqTable.get(i));
				fH.insert(temp);
			}
		}
		while (fH.size() > 1) {
			Node node1 = fH.deleteM();
			Node node2 = fH.deleteM();
			Node node3 = Node.meld(node1, node2);
			fH.insert(node3);
		}
	}

	public void buildtreep(ArrayList<Integer> freqTable) {
		for (int i = 0; i < 1000000; i++) {
			if (freqTable.get(i) != 0) {
				Node temp = new Node(i ,freqTable.get(i));
				pH.insert(temp);
			}
		}
		//System.out.println(pH.root.prev);
		while (pH.root.leftChild != null) {
			Node node1 = pH.deleteM();
			Node node2 = pH.deleteM();
			Node node3 = Node.meld(node1, node2);
			pH.insert(node3);
			//System.out.println(node3.value);
		}
	}

	public static void main(String[] args) throws Exception{
		ReadFile file = new ReadFile();
        BuildTree b = new BuildTree();
		ArrayList<Integer> freqtable = file.buildFreTable(args[0]);
		b.buildtreep(freqtable);
	}
}