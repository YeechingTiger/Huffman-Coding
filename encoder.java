// @Author: Xing He  UFID: 5901-7218
// Project for Advanced Data Structure Course in University of Florida
//Final Version Date: April 4, 2017

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

public class encoder {
	public void encoder(String path) throws Exception{
		//Declare variables
		int bufferSize = 8192;
		String str = null;
		int readnum;
		int i = 0;
		int k,  j, length;
		char[] temp = new char[8];
		String[] table;
		Queue<Character> buffer = new LinkedList<>();
		byte[] outBuffer = new byte[bufferSize];

		//Read file form code table
		BufferedReader read = new BufferedReader(new FileReader(new File(path)));

		//Create bin File
		File encoded = new File("encoded.bin");
		encoded.createNewFile();
		FileOutputStream outputStream = new FileOutputStream(encoded);
		
		//Build Table
		BuildTable buildTable = new BuildTable();
		buildTable.buildTable(path);
		table = buildTable.table;

		//Read code table file and output bin file
      	while ((str = read.readLine()) != null) {
        	readnum = Integer.parseInt(str);
        	length = table[readnum].length();
        	for (k = 0; k < length; k++) {
        		buffer.add(table[readnum].charAt(k));
        	}
        	while (buffer.size() >= 8) {	
        		for(k = 0; k < 8; k++) {
        			temp[k] = buffer.remove();
        		}
        		outBuffer[i] = Integer.valueOf(String.valueOf(temp), 2).byteValue();
        		i++;
        		
        		if (i == bufferSize) {
        			outputStream.write(outBuffer);
        			outBuffer = new byte[bufferSize];
        			i = 0;
        		}
        	}
        }

        //For the remaining character in the outBuffer
       	for (j = 0; j < i; j++) {
			outputStream.write(outBuffer[j]);
        }
        outputStream.close();
	}

	public static void main(String[] args) throws Exception{	
		System.out.println("Encoding...... You should wait about 12s when using input_large.txt");
		long start, end;
		start = System.currentTimeMillis();
		encoder encode1 = new encoder();
		encode1.encoder(args[0]);
		end = System.currentTimeMillis();
		System.out.println("Whole Time of Encoding is " + (end - start)+"ms");
		System.out.println("Generated files could be found in source code folder");
	}
}