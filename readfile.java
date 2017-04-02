import java.util.*;
import java.io.*;

public class ReadFile{
    
    public ArrayList<Integer> buildFreTable(String path) throws Exception{
        int readnum;
        int numfreq;
        BufferedReader read = new BufferedReader(new FileReader(new File(path)));
        ArrayList<Integer> freqTable = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
        	freqTable.add(i, 0);
        }
        String str = null;
      	while((str = read.readLine()) != null){
        	readnum = Integer.parseInt(str);
        	numfreq = freqTable.get(readnum);
        	freqTable.set(readnum, numfreq + 1);	
        }
        
        return freqTable;
    }

}