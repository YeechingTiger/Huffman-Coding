// @Author: Xing He  UFID: 5901-7218
// Project for Advanced Data Structure Course in University of Florida
//Final Version Date: April 4, 2017

import java.io.*;

public class decoder{
	public Node rootNode = new Node(-1, -1);
	public void buildTree(String codeTable1) throws Exception{
		String[] string;
		String str = null;
		int number;
		char a;
		String code;
		BufferedReader codeTable2 = new BufferedReader(new FileReader(new File(codeTable1)));

		while((str = codeTable2.readLine()) != null) {
        	Node node = rootNode;
        	string = str.split(" ");
        	number = Integer.parseInt(string[0]);
        	code = string[1];
        	for (int i = 0; i < code.length() - 1; i++) {
        		if (code.charAt(i) == '0') {
        			if (node.leftChild == null) {
        				node.leftChild = new Node(-1, -1);
        			}
        			node = node.leftChild;
        		} else { 
        			if (node.rightChild == null)	{
        				node.rightChild = new Node(-1, -1);
        			}
        			node = node.rightChild;
        		}
        	}
        	if (code.charAt(code.length() - 1) == '0') {
        		node.leftChild = new Node(number, 0);
        	} else {
        		node.rightChild = new Node(number, 0);
        	}	
		}
	}

	public void decodeData(String encoded1) throws Exception{
		DataInputStream encoded2 = new DataInputStream( new BufferedInputStream( new FileInputStream( new File(encoded1))));
		File decoded = new File("decoded.txt");
		decoded.createNewFile();
		BufferedWriter p = new BufferedWriter(new FileWriter(decoded));
		byte[] b = new byte[8*1024];
		String temp;
		Node pointer = rootNode;
		int available;
		while ((available = encoded2.available()) > 0) {
			if (available < 8*1024){
				b = new byte[available];
			}
			encoded2.read(b);
			for (byte temp0: b) {
				temp = byteToString(temp0);
				for (int i = 0; i < 8; i++) {
					if (temp.charAt(i) == '0') {
						pointer = pointer.leftChild;
						if (pointer.value == 0) {
							p.write(pointer.number + "");
							p.newLine();
							pointer = rootNode;
						}
					} else {
						pointer = pointer.rightChild;
						if (pointer.value == 0) {
							p.write(pointer.number + "");
							p.newLine();
							pointer = rootNode;
						}
					}
				}
			}
		}
		p.close();
	}
	//Byte to String
    public static String byteToString(byte b) {
        return ""  
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)  
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)  
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)  
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);  
    }  

    //Main method
	public static void main(String[] args) throws Exception{
		System.out.println("Decoding......You should wait about 8s when using input_large.txt");
		long start, end;
		start = System.currentTimeMillis();
		decoder d = new decoder();
		d.buildTree(args[1]);
		d.decodeData(args[0]);
		end = System.currentTimeMillis();
		System.out.println("Whole Time of Decoding is " + (end - start)+"ms");
		System.out.println("Generated file could be found in source code folder");
	}
}	