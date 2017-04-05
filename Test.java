// @Author: Xing He  UFID: 5901-7218
// Project for Advanced Data Structure Course in University of Florida
//Final Version Date: April 4, 2017

import java.util.ArrayList;
public class Test {
	public static void main(String[] args) throws Exception {
        ReadFile file = new ReadFile();
        BuildTree b = new BuildTree();
		ArrayList<Integer> freqtable = file.buildFreTable(args[0]);
	    long start, end, costtime;
	   // //pairing heap
	   //  start = System.currentTimeMillis();
		
	   //  	b.buildtreep(freqtable);
	    
	   //  end = System.currentTimeMillis();
	   //  costtime = end - start;
	   //  System.out.println("Time cost of pairing Heap is " + costtime);
	    //4-way heap
		// start = System.currentTimeMillis();
	    
	 //    	b.buildtreef(freqtable);
	    
		// end = System.currentTimeMillis();
	 //    costtime = end - start;
	 //    System.out.println("Time cost of 4-way Heap is " + costtime);

	    
	    //binary heap
		

	    	start = System.currentTimeMillis();
	    	b.buildtreeb(freqtable);
	    	end = System.currentTimeMillis();
	    	costtime = end - start;
	    	System.out.println("Time cost of binary Heap is " + costtime);

	    
	    

    }

}