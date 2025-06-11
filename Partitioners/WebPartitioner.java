// You can (and should) add "implements Partitioner" below once you have the implementation ready

// Web site for implementation: https://www.baeldung.com/java-quicksort
// Author: baeldung
// URL for license: https://github.com/eugenp/tutorials/blob/master/LICENSE

// Changes made: I had to change a lot of values because the implementation from the site was used
//               for integer arrays, so I had to adjust it to work with string arrays/values.
//               I also included a if statement to check for arrays with a length of 1. It was not
//               buggy and it worked very well, I just had to adjust for same letters that were the
//               same as the pivot to get placed after not before its new location, which took a 
//               simple deletion of = from <= in line 22.
//
// Bugs: There are no bugs for this class currently, the test to demonstrate this is in
//       TestPartitionOracle.java, when running TestPartitionOracle.java, it checks to see if
//       all the partitions work, if this class didn't work properly, a counter example would
//       be provided.
//
// Worst Case Runtime: O(n), this is because of the for loop which will always run the length of
//                     of the array n.
public class WebPartitioner implements Partitioner {

	@Override
	public int partition(String[] strs, int low, int high) {
		if((high - low) <= 1) {
			return low;
		}
		
		if(strs.length == 0) {
			return 0;
		}
		
	    String pivot = strs[high-1];
	    int i = low-1;

	    for (int j = low; j < high-1; j++) {
	        if (strs[j].compareTo(pivot) < 0) {
	            i++;

	            String swapTemp = strs[i];
	            strs[i] = strs[j];
	            strs[j] = swapTemp;
	        }
	    }

	    String swapTemp = strs[i+1];
	    strs[i+1] = strs[high-1];
	    strs[high-1] = swapTemp;

	    return i+1;
	}
}
