import java.util.*;
import java.io.*;
public class encoder {
	public void encoder(String path) throws Exception{
		int bufferSize = 4096;
		String str = null;
		int readnum;
		int i = 0;
		int k,  j, length;
		String temp;
		String[] table;
		LinkedList<Character> buffer = new LinkedList<>();
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
      	while((str = read.readLine()) != null){
        	readnum = Integer.parseInt(str);
        	length = table[readnum].length();
        	for (k = 0; k < length; k++) {
        		buffer.add(table[readnum].charAt(k));
        	}
        	while (buffer.size() >= 8) {
        		temp = "";
        		for(k = 0; k < 8; k++) {
        			temp += buffer.remove();
        		}
        		outBuffer[i] = Integer.valueOf(temp, 2).byteValue();
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
		encoder encode1 = new encoder();
		encode1.encoder(args[0]);
	}
}