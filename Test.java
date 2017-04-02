import java.util.ArrayList;
public class Test {
	public static void main(String[] args) throws Exception {
        ReadFile file = new ReadFile();
        BuildTree b = new BuildTree();
		ArrayList<Integer> freqtable = file.buildFreTable(args[0]);
	    long start, end, costtime;
	   //pairing heap
	    start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
	    	b.buildtreep(freqtable);
	    }
	    end = System.currentTimeMillis();
	    costtime = end - start;
	    System.out.println("Time cost of pairing Heap is " + costtime);
	    //4-way heap
		start = System.currentTimeMillis();
	    for (int i = 0; i < 10; i++) {
	    	b.buildtreef(freqtable);
	    }
		end = System.currentTimeMillis();
	    costtime = end - start;
	    System.out.println("Time cost of 4-way Heap is " + costtime);

	    
	    //binary heap
		
		for (int i = 0; i < 10; i++) {
	    	start = System.currentTimeMillis();
	    	b.buildtreeb(freqtable);
	    	end = System.currentTimeMillis();
	    	costtime = end - start;
	    	System.out.println("Time cost of binary Heap is " + costtime);

	    }
	    
	    

    }

}