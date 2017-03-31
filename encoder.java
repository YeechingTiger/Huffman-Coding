import java.util.*;
import java.io.*;
public class encoder {
	public void encoder(String path) throws Exception{
		long start, end, costtime;
		int readnum;
		ArrayList<Character> buffer = new ArrayList<>(32);
		String[] table;
		BuildTable buildTable = new BuildTable();
		start = System.currentTimeMillis();
		buildTable.buildTable(path);
		end = System.currentTimeMillis();
	    costtime = end - start;
	    System.out.println("Build table:" + costtime + "s");
		table = buildTable.table;
		//System.out.println(table);
		start = System.currentTimeMillis();
		BufferedReader read = new BufferedReader(new FileReader(new File(path)));
		File encoded = new File("encoded.bin");
		encoded.createNewFile();
		FileOutputStream outputStream = new FileOutputStream(encoded);
		//BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("encoded.bin"));
		String str = null;
		byte[] outBuffer = new byte[4 * 1024];
		int i = 0;
      	while((str = read.readLine()) != null){
        	readnum = Integer.parseInt(str);
        	//System.out.println(readnum);
        	for (int k = 0; k < table[readnum].length(); k++) {
        		buffer.add(table[readnum].charAt(k));
        	}

        	while (buffer.size() >= 8) {
        		String temp = "";
        		for(int k = 0; k < 8; k++) {
        			temp += buffer.get(0);
        			buffer.remove(0);
        		}
        		outBuffer[i] = Integer.valueOf(temp+"", 2).byteValue();
        		i++;
        		if (i == 4 * 1024) {
        			outputStream.write(outBuffer);
        			outBuffer = new byte[4 * 1024];
        			i = 0;
        		}
        	}
        	
        }
        //For the remaining character in the buffer
       	for (int j = 0; j < i; j++) {
			outputStream.write(outBuffer[j]);
        }
        outputStream.close();
        end = System.currentTimeMillis();
	    costtime = end - start;
	    System.out.println("OutPut bin:" + costtime + "s");
		table = buildTable.table;
	}

	public static void main(String[] args) throws Exception{
		encoder encode1 = new encoder();
		encode1.encoder(args[0]);
	}
}