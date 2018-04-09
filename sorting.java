import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Arrays;

public class sorting {
    
    private static int[] arr;
    private static int[] arrCopy;
    private static int[] mergeArr;
    private static BufferedReader read;
    private static Random randomGenerator;
    
    private static int size;
    private static int random;

    private static void printArray(String msg) {
    	System.out.print(msg + " [" + arr[0]);
        for(int i=1; i<size; i++) {
            System.out.print(", " + arr[i]);
        }
        System.out.println("]");
    }
    
    public static void exchange(int i, int j){
        int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t; 
    }

	public static boolean isSorted(int low, int high){
		for (int i = 1; i < high - low; i++){
			if (arr[i-1] > arr[i]) return false;
		}
		return true;
	}

    public static void insertSort(int left, int right) {
	  // insertSort the subarray arr[left, right]
	  int i, j;

	  for(i=left+1; i<=right; i++) {
	    int temp = arr[i];           // store a[i] in temp
	    j = i;                       // start shifts at i
	    // until one is smaller,
	    while(j>left && arr[j-1] >= temp) {
		  arr[j] = arr[j-1];        // shift item to right
		  --j;                      // go left one position
	    }
	    arr[j] = temp;              // insert stored item
	  }  // end for
    }  // end insertSort()
    
    public static void insertionSort() {
    	insertSort(0, size-1);  
    } // end insertionSort()

    
    public static void maxheapify(int i, int n) { 
	   // Pre: the left and right subtrees of node i are max heaps.
	   // Post: make the tree rooted at node i as max heap of n nodes.
        int max = i;
        int left=2*i+1;
        int right=2*i+2;
        
        if(left < n && arr[left] > arr[max]) max = left;
        if(right < n && arr[right] > arr[max]) max = right;
        
        if (max != i) {  // node i is not maximal
            exchange(i, max);
            maxheapify(max, n);
        }
    }
    
    public static void heapsort(){
    	// Build an in-place bottom up max heap
        for (int i=size/2; i>=0; i--) maxheapify(i, size);
         
        for(int i=size-1;i>0;i--) {
            exchange(0, i);       // move max from heap to position i.
            maxheapify(0, i);     // adjust heap
        }
    }
    
    private static void mergesort(int low, int high) {
    	// sort arr[low, high-1]
        // Check if low is smaller then high, if not then the array is sorted
        if (low < high-1) {
          // Get the index of the element which is in the middle
          int middle = (high + low) / 2;
          // Sort the left side of the array
          mergesort(low, middle);
          // Sort the right side of the array
          mergesort(middle, high);
          // Combine them both
          merge(low, middle, high);
        }
      }

	private static void mergesort2(int low, int high) {
	if((high - low) <= 100){
		insertSort(low,high-1);}else{
    	// sort arr[low, high-1]
        // Check if low is smaller then high, if not then the array is sorted
        if (low < high-1) {
          // Get the index of the element which is in the middle
          int middle = (high + low) / 2;
          // Sort the left side of the array
          mergesort2(low, middle);
          // Sort the right side of the array
          mergesort2(middle, high);
          // Combine them both
          merge(low, middle, high);
        }
      }
}
    private static void merge(int low, int middle, int high) {
    	// merge arr[low, middle-1] and arr[middle, high-1] into arr[low, high-1]
    	
        // Copy first part into the arrCopy array
        for (int i = low; i < middle; i++) mergeArr[i] = arr[i];

        int i = low;
        int j = middle;
        int k = low;

        // Copy the smallest values from either the left or the right side back        // to the original array
        while (i < middle && j < high) 
          if (mergeArr[i] <= arr[j]) 
            arr[k++] = mergeArr[i++];
          else 
            arr[k++] = arr[j++];
	
        // Copy the rest of the left part of the array into the original array
        while (i < middle) arr[k++] = mergeArr[i++];
    }

    
    public static void naturalMergesort() {          
    	int run[], i, j, s, t, m;                                                                         
    	                                                                                                                                                                           
    	run = new int[size/2];                                                       
    	                                                                                                   
	    // Step 1: identify runs from the input array arr[]                                                                               
    	i = m = 1; 
    	run[0] = 0;                                                                          
        while (i < size) {                                                                                
    	    if (arr[i-1] > arr[i])                                                                   
    	       if (run[m-1]+1 == i) {     // make sure each run has at least two                          
    	                                                                                                  
    	          j = i+1;                                                                                 
    	          s = 0;                                                                                   
    	          while (j < size && arr[j-1] >= arr[j]) j++;     // not stable                                    
          
    	          // reverse arr[i-1, j-1];     
    	          s = i - 1;
    	          t = j - 1;
    	          while (s < t) exchange(s++, t--);
    	          
    	          i = j;                                                                                   
    	        } else                                                                                     
    	          run[m++] = i++;                                                                          
    	     else i++;                                                                                    
    	}                               	                                                                                                                                                                                            
    	                                                                                                   
        // Step 2: merge runs bottom-up into one run                                                                       
        t = 1;                                                                                         
    	while (t < m) {                                                                                
    	    s = t;                                                                                       
    	    t = s<<1;                                                                                    
    	    i = 0;                                                                                       
    	    while (i+t < m) {                                                                            
    	        merge(run[i], run[i+s], run[i+t]);                                                    
    	        i += t;                                                                                    
    	    }                                                                                            
    	    if (i+s < m) merge(run[i], run[i+s], size);                                                
    	}                                                                                              
                                                
    }   
      
    private static void quicksort(int low, int high) {
	    int i = low, j = high;

	    // Get the pivot element from the middle of the list
	    int pivot = arr[(high+low)/2];

	    // Divide into two lists
	    while (i <= j) {
    	      // If the current value from the left list is smaller then the pivot
    	      // element then get the next element from the left list
    	      while (arr[i] < pivot) i++;
    	      
    	      // If the current value from the right list is larger then the pivot
    	      // element then get the next element from the right list
    	      while (arr[j] > pivot) j--;

    	      // If we have found a value in the left list which is larger than
    	      // the pivot element and if we have found a value in the right list
    	      // which is smaller then the pivot element then we exchange the
    	      // values.
    	      // As we are done we can increase i and j
    	      if (i < j) {
    	        exchange(i, j);
    	        i++;
    	        j--;
    	      } else if (i == j) { i++; j--; }
        }

    	// Recursion
    	if (low < j)
    	      quicksort(low, j);
    	if (i < high)
    	      quicksort(i, high);
    }
	

	private static void quicksort2(int low, int high) {
		if((high - low) <= 100) 
			{insertSort(low,high-1);}else{
		 
	    int i = low, j = high;

	    // Get the pivot element from the middle of the list
	    int pivot = arr[(high+low)/2];

	    // Divide into two lists
	    while (i <= j) {
    	      // If the current value from the left list is smaller then the pivot
    	      // element then get the next element from the left list
    	      while (arr[i] < pivot) i++;
    	      
    	      // If the current value from the right list is larger then the pivot
    	      // element then get the next element from the right list
    	      while (arr[j] > pivot) j--;

    	      // If we have found a value in the left list which is larger than
    	      // the pivot element and if we have found a value in the right list
    	      // which is smaller then the pivot element then we exchange the
    	      // values.
    	      // As we are done we can increase i and j
    	      if (i < j) {
    	        exchange(i, j);
    	        i++;
    	        j--;
    	      } else if (i == j) { i++; j--; }
        }

    	// Recursion
    	if (low < j)
    	      quicksort2(low, j);
    	if (i < high)
    	      quicksort2(i, high);
    }
	}


	private static void quicksort3(int low, int high) {
	if (isSorted(low,high)){return;}
	    int i = low, j = high;

	    // Get the pivot element from the middle of the list
	    int pivot = arr[(high+low)/2];

	    // Divide into two lists
	    while (i <= j) {
    	      // If the current value from the left list is smaller then the pivot
    	      // element then get the next element from the left list
    	      while (arr[i] < pivot) i++;
    	      
    	      // If the current value from the right list is larger then the pivot
    	      // element then get the next element from the right list
    	      while (arr[j] > pivot) j--;

    	      // If we have found a value in the left list which is larger than
    	      // the pivot element and if we have found a value in the right list
    	      // which is smaller then the pivot element then we exchange the
    	      // values.
    	      // As we are done we can increase i and j
    	      if (i < j) {
    	        exchange(i, j);
    	        i++;
    	        j--;
    	      } else if (i == j) { i++; j--; }
        }

    	// Recursion
    	if (low < j)
    	      quicksort3(low, j);
    	if (i < high)
    	      quicksort3(i, high);
    }

	private static void quicksort4(int low, int high) {
		if(isSorted(low,high))return;
		if((high - low) <= 100) 
			{insertSort(low,high);}else{
		 
	    int i = low, j = high;

	    // Get the pivot element from the middle of the list
	    int pivot = arr[(high+low)/2];

	    // Divide into two lists
	    while (i <= j) {
    	      // If the current value from the left list is smaller then the pivot
    	      // element then get the next element from the left list
    	      while (arr[i] < pivot) i++;
    	      
    	      // If the current value from the right list is larger then the pivot
    	      // element then get the next element from the right list
    	      while (arr[j] > pivot) j--;

    	      // If we have found a value in the left list which is larger than
    	      // the pivot element and if we have found a value in the right list
    	      // which is smaller then the pivot element then we exchange the
    	      // values.
    	      // As we are done we can increase i and j
    	      if (i < j) {
    	        exchange(i, j);
    	        i++;
    	        j--;
    	      } else if (i == j) { i++; j--; }
        }

    	// Recursion
    	if (low < j)
    	      quicksort4(low, j);
    	if (i < high)
    	      quicksort4(i, high);
    }
	}
    private static void medianOfThree(int low, int high) {
	int mid = (high+low)/2;
	if (arr[low]>arr[mid]) exchange(low,mid);
	if (arr[low]>arr[high]) exchange(low,high);
	if (arr[mid]>arr[high]) exchange(mid,high);
    }

	private static void quicksort5(int low, int high) {
		if(isSorted(low,high))return;
		if((high - low) <= 100) 
			{insertSort(low,high);}else{
		 
	    int i = low, j = high;

	    // Get the pivot element from the middle of the list
	    medianOfThree(low,high);
	    int pivot = arr[(low+high)/2];

	    // Divide into two lists
	    while (i <= j) {
    	      // If the current value from the left list is smaller then the pivot
    	      // element then get the next element from the left list
    	      while (arr[i] < pivot) i++;
    	      
    	      // If the current value from the right list is larger then the pivot
    	      // element then get the next element from the right list
    	      while (arr[j] > pivot) j--;

    	      // If we have found a value in the left list which is larger than
    	      // the pivot element and if we have found a value in the right list
    	      // which is smaller then the pivot element then we exchange the
    	      // values.
    	      // As we are done we can increase i and j
    	      if (i < j) {
    	        exchange(i, j);
    	        i++;
    	        j--;
    	      } else if (i == j) { i++; j--; }
        }

    	// Recursion
    	if (low < j)
    	      quicksort5(low, j);
    	if (i < high)
    	      quicksort5(i, high);
    }
	}
	private static void quicksort6(int low, int high) {
		if(isSorted(low,high))return;
		if((high - low) <= 100) 
			{insertSort(low,high);}else{
		 
	    int i = low, j = high;

	    // Get the pivot element from the middle of the list
	    double s = (high-low)/8;
	    for (double n=low;n<(high-3*s);n+=3*s) medianOfThree((int)n,(int)(n+s*2));
	    medianOfThree(low,high);
	    int pivot = arr[(high+low)/2];

	    // Divide into two lists
	    while (i <= j) {
    	      // If the current value from the left list is smaller then the pivot
    	      // element then get the next element from the left list
    	      while (arr[i] < pivot) i++;
    	      
    	      // If the current value from the right list is larger then the pivot
    	      // element then get the next element from the right list
    	      while (arr[j] > pivot) j--;

    	      // If we have found a value in the left list which is larger than
    	      // the pivot element and if we have found a value in the right list
    	      // which is smaller then the pivot element then we exchange the
    	      // values.
    	      // As we are done we can increase i and j
    	      if (i < j) {
    	        exchange(i, j);
    	        i++;
    	        j--;
    	      } else if (i == j) { i++; j--; }
        }

    	// Recursion
    	if (low < j)
    	      quicksort6(low, j);
    	if (i < high)
    	      quicksort6(i, high);
    }
	}

    public static void demo1 (String input) {
    	// demonstration of sorting algorithms on random input
    	
        long start, finish;
        System.out.println();
        
        // Heap sort      
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        heapsort();
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        System.out.println("heapsort on " + input + " input: " + (finish-start) + " milliseconds.");
             
        // Merge sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        mergesort(0, size);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        System.out.println("mergesort on " + input + " input: " + (finish-start) + " milliseconds.");

        // Natural Merge sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        naturalMergesort();
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        System.out.println("natural mergesort on " + input + " input: " + (finish-start) + " milliseconds.");

        // Quick sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        System.out.println("quicksort on " + input + " input: " + (finish-start) + " milliseconds.");
    }
    
	public static void task1 () {
	System.out.print("\n\nTask 1:\n\t          ");
    	// demonstration of sorting algorithms on random input
	size = 1000000;
    	arr = new int[size];
        arrCopy = new int[size];
        mergeArr = new int[size];
        long [] timeArr = new long[4];
	String waiting = "waiting...";
	String [] nameArr = {"Merge Sort", "Merge Sort2", "Quick Sort", "Quick Sort2"};
        // fill array
	random = size*10;
        for(int i=0; i<size; i++) 
           arr[i] = arrCopy[i] = randomGenerator.nextInt(random);
        long start, finish;
        for(int sexy = 0; sexy < 100; sexy++){
             
        // Merge sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        mergesort(0, size);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[0] += finish - start;
	// Merge sort2
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        mergesort2(0, size);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[1] += finish - start;
        
        // Quick sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        //System.out.println("quicksort on " + input + " input: " + (finish-start) + " milliseconds.");
    
    	timeArr[2] += finish - start;
	// Quick sort2
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort2(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        //System.out.println("quicksort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[3] += finish - start;
	
	//clear all the old string from last iteration
	for(int i=0;i<16;i++) System.out.print("\b");
	//print new waiting text
	System.out.print(waiting.substring(0,sexy%11));
	
	//fill the blank space
	for(int i=0;i<11-(sexy%11);i++)System.out.print(" ");

	//print percentage
	System.out.printf("%3d %%",sexy+1);
}
	System.out.println("\n");
	for(int i = 0; i < 4; i++){
	System.out.printf("%s:%dms  ",nameArr[i],timeArr[i]);
	}
	System.out.println("\n\n");	
	
}

	public static void task2 () {
	System.out.print("\n\nTask 2:\n\t          ");
	size = 1000000;
    	arr = new int[size];
        arrCopy = new int[size];
        mergeArr = new int[size];
	
        long [] timeArr = new long[4];
	String waiting = "waiting...";
	String [] nameArr = {"Quick Sort", "Quick Sort2", "Quick Sort3", "Quick Sort4"};

        for(int sexy = 0; sexy < 30; sexy++){
             if (sexy < 10) {
		        // fill array with random
			random = size*10;
        		for(int i=0; i<size; i++) 
        		   arr[i] = arrCopy[i] = randomGenerator.nextInt(random);
             }else if (sexy < 20) {
		        for(int i=0; i<size;i++) arrCopy[i]=arr[size-i-1];
	     }
        // Quick Sort
	long start, finish;
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[0] += finish - start;
	// Quick Sort2
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort2(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[1] += finish - start;
        
        // Quick sort3
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort3(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        //System.out.println("quicksort on " + input + " input: " + (finish-start) + " milliseconds.");
    
    	timeArr[2] += finish - start;
	// Quick sort4
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort4(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        //System.out.println("quicksort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[3] += finish - start;
	
	//clear all the old string from last iteration
	for(int i=0;i<16;i++) System.out.print("\b");
	//print new waiting text
	System.out.print(waiting.substring(0,sexy%11));
	
	//fill the blank space
	for(int i=0;i<11-(sexy%11);i++)System.out.print(" ");

	//print percentage
	System.out.printf("%3d %%",(int)(sexy+1.0)/3*10);
}
	System.out.println("\n");
	for(int i = 0; i < 4; i++){
	System.out.printf("%s:%dms  ",nameArr[i],timeArr[i]);
	}
	System.out.println("\n\n");
}

	public static void task3 () {
	System.out.print("\n\nTask 3:\n\t          ");
	size = 1000000;
    	arr = new int[size];
        arrCopy = new int[size];
        mergeArr = new int[size];
	
        long [] timeArr = new long[5];
	String waiting = "waiting...";
	String [] nameArr = {"Heap Sort", "Quick Sort", "Quick Sort5", "Quick Sort6", "Quick Sort4"};

        for(int sexy = 0; sexy < 30; sexy++){
             if (sexy < 10) {
		        // fill array with random
			random = size*10;
        		for(int i=0; i<size; i++) 
        		   arr[i] = arrCopy[i] = randomGenerator.nextInt(random);
             }else if (sexy < 20) {
		        for(int i=0; i<size;i++) arrCopy[i]=arr[size-i-1];
	     }else{
		int a = size/2;
        	int b = size-a;
        	for(int i=0; i<a; i++) arrCopy[i] = a-i;
        	for(int i=0; i<b; i++) arrCopy[i+a] = i+1;
		}


	// Heap sort      
	long start, finish;
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        heapsort();
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[0] += finish - start;

	
	
	/* Normal quick sort fails(StackOverFlow) because of organ-pipe shaped data.

        // Quick Sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[1] += finish - start;
	*/

	// Quick Sort5
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort5(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[2] += finish - start;
        
        // Quick sort6
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort6(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        //System.out.println("quicksort on " + input + " input: " + (finish-start) + " milliseconds.");
    
    	timeArr[3] += finish - start;

	/* quicksort4 also fails(StackOverFlow) because of organ-pipe shaped data.

	// Quicksort4
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort4(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        //System.out.println("quicksort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[4] += finish - start;
	*/

	//clear all the old string from last iteration
	for(int i=0;i<16;i++) System.out.print("\b");
	//print new waiting text
	System.out.print(waiting.substring(0,sexy%11));
	
	//fill the blank space
	for(int i=0;i<11-(sexy%11);i++)System.out.print(" ");

	//print percentage
	System.out.printf("%3d %%",(int)(sexy+1.0)/3*10);
}
	System.out.println("\n");
	
	for(int i = 0; i < 5; i++){
	System.out.printf("%s:%dms  ",nameArr[i],timeArr[i]);
	}
	System.out.println("\n\nAlgorithm who has total time of zero is because they fails to complete(StackOverFlow)");
	
}

	public static void task4_1 () {
	System.out.print("\n\nTask 4 Part 1:\n\t          ");
    	arr = new int[size];
        arrCopy = new int[size];
        mergeArr = new int[size];
	long start, finish;
        long [] timeArr = new long[5];
	String waiting = "waiting...";
	String [] nameArr = {"QuickSort6","Heap Sort", "Merge Sort", "Natural Merge Sort", "Insertion"};
	int k = 0;
	while(timeArr[0]>=timeArr[4]){
		k += 10;
		System.out.println("\nk="+k);
		timeArr = new long[5];
		        // fill array with random
			random = size*10;
        		for(int sexy=0; sexy<size; sexy++) 
        		   arr[sexy] = arrCopy[sexy] = randomGenerator.nextInt(k)+sexy;
		for(int xavier = 0; xavier <  100; xavier++){

             
	
	// Quick sort6
        for (int awesome=0; awesome<size; awesome++) arr[awesome] = arrCopy[awesome];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort6(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        //System.out.println("quicksort on " + input + " input: " + (finish-start) + " milliseconds.");
    
    	timeArr[0] += finish - start;

	// Heap sort      
	
        for (int legend=0; legend<size; legend++) arr[legend] = arrCopy[legend];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        heapsort();
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[1] += finish - start;

	
	


        // Merge sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        mergesort2(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[2] += finish - start;

	// Natural Merge Sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        naturalMergesort();
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("Natural mergesort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[3] += finish - start;
        
        
	
	// Insertion sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        insertionSort();
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        //System.out.println("quicksort2 on " + input + " input: " + (finish-start) + " milliseconds.");

	timeArr[4] += finish - start;
	

	//clear all the old string from last iteration
	for(int i=0;i<16;i++) System.out.print("\b");
	//print new waiting text
	System.out.print(waiting.substring(0,xavier%11));
	
	//fill the blank space
	for(int i=0;i<11-(xavier%11);i++)System.out.print(" ");

	//print percentage
	System.out.printf("%3d %%",xavier+1);
	}}
	System.out.println("\n");
	
	for(int i = 0; i < 5; i++){
	System.out.printf("%s:%dms\n",nameArr[i],timeArr[i]);
	}
	System.out.println("\n");
}

		
	public static void task4_2 () {
	System.out.print("\n\nTask 4 Part 2:\n\t          ");
	long start, finish;
        long [] timeArr = new long[5];
	String waiting = "waiting...";
	String [] nameArr = {"QuickSort6","Heap Sort", "Merge Sort", "Natural Merge Sort", "Insertion"};
	int k = 0;
	while(timeArr[0]>=timeArr[4]){
		k += 100;
		System.out.println("\nk="+ k);
		timeArr = new long[5];
/*
			for (int pizza = 0; pizza < 100; pizza++) {
	    			int first_num  = randomGenerator.nextInt(size);
	    			int second_num  = randomGenerator.nextInt(size);
	    			exchange(first_num, second_num);
			}
	
*/
		for(int xavier = 0; xavier <  100; xavier++){

             
	
	// Quick sort6
        for (int awesome=0; awesome<size; awesome++) arr[awesome] = arrCopy[awesome];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        quicksort6(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        //System.out.println("quicksort on " + input + " input: " + (finish-start) + " milliseconds.");
    
    	timeArr[0] += finish - start;

	// Heap sort      
	
        for (int legend=0; legend<size; legend++) arr[legend] = arrCopy[legend];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        heapsort();
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[1] += finish - start;

	
	


        // Merge sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        mergesort2(0, size-1);
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("mergesort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[2] += finish - start;

	// Natural Merge Sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        naturalMergesort();
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");
        //System.out.println("Natural mergesort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[3] += finish - start;
        
	
	// Insertion sort
        for (int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
        insertionSort();
        finish = System.currentTimeMillis();
        if (size < 101) printArray("out");   
        //System.out.println("quicksort2 on " + input + " input: " + (finish-start) + " milliseconds.");
	timeArr[4] += finish - start;
	

	//clear all the old string from last iteration
	for(int i=0;i<16;i++) System.out.print("\b");
	//print new waiting text
	System.out.print(waiting.substring(0,xavier%11));
	
	//fill the blank space
	for(int i=0;i<11-(xavier%11);i++)System.out.print(" ");

	//print percentage
	System.out.printf("%3d %%",xavier+1);
	}}
	System.out.println("\n");
	
	for(int i = 0; i < 5; i++){
	System.out.printf("%s:%dms\n",nameArr[i],timeArr[i]);
	}
	System.out.println("\n");
}
    public static void task4(){
	size = 5000000;
	task4_1();
	task4_2();
    }

    public static void demo2 (String input) {
       	// demonstration of sorting algorithms on nearly sorted input
    	
        long start, finish;
	    
        demo1(input); 
	
        // Insert sort on nearly-sorted array      
        for(int i=0; i<size; i++) arr[i] = arrCopy[i];
        if (size < 101) printArray("in");
        start = System.currentTimeMillis();
	insertionSort();	    
	finish = System.currentTimeMillis();
	if (size < 101) printArray("out");
	System.out.println("insertsort on " + input + " input: " + (finish-start) + " milliseconds.");
    }
    
    public static void main(String[] args) {
        
        read = new BufferedReader(new InputStreamReader(System.in));
        
        randomGenerator = new Random();
        /*
        try {
            System.out.print("Please enter the array size : ");
            size = Integer.parseInt(read.readLine());
        } catch(Exception ex){
            ex.printStackTrace();
        }
           

        // create array
        arr = new int[size];
        arrCopy = new int[size];
        mergeArr = new int[size];
            
        // fill array
	random = size*10;
        for(int i=0; i<size; i++) 
           arr[i] = arrCopy[i] = randomGenerator.nextInt(random);    
        demo1("random");
        
	 arr[0..size-1] is already sorted. We randomly swap 100 pairs to make it nearly-sorted.
	for (int i = 0; i < 100; i++) {
	    int j  = randomGenerator.nextInt(size);
	    int k  = randomGenerator.nextInt(size);
	    exchange(j, k);
	}
	for(int i=0; i<size; i++) arrCopy[i] = arr[i];
	demo2("nearly sorted");
        
	for(int i=0; i<size; i++) arrCopy[i] = size-i;
	demo1("reversely sorted");
	*/	
	task1();
	task2();
	task3();
	task4();
    }
    
    
}
