import java.util.ArrayList;
import java.io.*;
public class BuildTable {
	File codeTable;
	PrintWriter p;
	String[] table = new String[1000000];
	String[] string;
	public void buildTable(String path) throws Exception {
		readfile file = new readfile();
        buildtree b = new buildtree();
		ArrayList<Integer> freqtable = file.buildFreTable(path);
		b.buildtreef(freqtable);
		Node rootNode = b.fH.fourWayHeap[0];
		String str = "";
		codeTable = new File("code_table.txt");
		codeTable.createNewFile();
		p = new PrintWriter(codeTable);
		//System.out.println(rootNode.value);
		//System.out.println(rootNode.rightChild.value);
		helper(rootNode, str, " ");
		p.close();
	}

	public void helper(Node node, String str, String num) {
		if (node.leftChild == null && node.rightChild == null) {
			str = node.number + str + num;
			p.println(str);
			string = str.split(" ");
			table[node.number] = string[1];
			return;
		}
		str = str + num;
		helper(node.leftChild, str, "0");
		helper(node.rightChild, str, "1");
	}

	public static void main(String[] args) throws Exception{
		BuildTable b = new BuildTable();
		b.buildTable(args[0]);
	}
}