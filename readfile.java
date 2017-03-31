import java.util.*;
import java.io.*;

public class readfile{
    
    public ArrayList<Integer> buildFreTable(String path) throws Exception{
        int readnum;
        int numfreq;
        BufferedReader read = new BufferedReader(new FileReader(new File(path)));
        ArrayList<Integer> ve = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
        	ve.add(i, 0);
        }
        String str = null;
      	while((str = read.readLine()) != null){
        	readnum = Integer.parseInt(str);
        	numfreq = ve.get(readnum);
        	ve.set(readnum, numfreq + 1);	
        }
        
        return ve;
    }

}