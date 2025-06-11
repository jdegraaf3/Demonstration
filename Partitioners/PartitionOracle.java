
import java.util.Arrays;
import java.util.Random;

public class PartitionOracle {

    /**
     * Feel free to use this method to call partition. It catches any exceptions or
     * errors and returns a definitely-invalid pivot (-1) to turn errors into
     * potential failures. For example, in testPartition, you may use
     * 
     * runPartition(p, someArray, someLow, someHigh)
     * 
     * instead of
     * 
     * p.partition(someArray, someLow, someHigh)
     * 
     * @param p
     * @param strs
     * @param low
     * @param high
     * @return
     */
    public static int runPartition(Partitioner p, String[] strs, int low, int high) {
        try {
            return p.partition(strs, low, high);
        } catch (Throwable t) {
            return -1;
        }
    }

    // The three methods below are for you to fill in according to the PA writeup.
    // Feel free to make other helper methods as needed.

    public static String isValidPartitionResult(String[] before, int low, int high, int pivot, String[] after) {
    	
    	if (before.length != after.length) {
    		return "after array doesnt have correct length";
    	}
    	
    	if (high > (before.length + 1) || low < 0 || high < low || (high - low) < 0) {
    		return "Bad High and Low Bounds";
    	}
    	
    	
    	
    	for (int i =0; i < after.length; i++) {
    		if (Arrays.asList(after).contains(before[i]) == false) {
    			return "Not all elements in Array";
    		}
    	}
    	
    	if (pivot < low || pivot > high || pivot < 0) {
    		return "Pivot out of bounds";
    	}
    	
    	for(int i = low; i < pivot; i++) {
    		if (after[i].compareTo(after[pivot]) > 0) {
    			return "item before pivot is too large";
    		}
    	}
    	
    	for(int i = (high - 1); i > pivot; i--) {
    		if (after[pivot].compareTo(after[i])> 0) {
    			return "item after pivot is too small";
    		}
    	}
    	
    	for(int i = 0; i < after.length; i++) {
    		if(after[i] == null || before[i] == null) {
    			return "Lost an element";
    		}
    	}
    	
    	if(before.length > (high - low)) {
    		for(int i = 0; i < low; i++) {
    			if(before[i] != after[i]) {
    				return "Elements moved that shouldn't before low";
    			}
    		}
    		for(int i = high; i < after.length; i++) {
    			if(before[i] != after[i]) {
    				return "Elements moved that shouldn't after high";
    			}
    		}
    	}
    	
    	if(runPartition(new CentralPivotPartitioner(), before, low, high) == -1) {
    		return "exception caught";
    	}
    	
    	if(runPartition(new FirstElePivotPartitioner(), before, low, high) == -1) {
    		return "exception caught";
    	}
    	
    	if(runPartition(new WebPartitioner(), before, low, high) == -1) {
    		return "exception caught";
    	}
    	
    	else {
    		return null;
    	}
    }

    public static String[] generateInput(int n) {
    	
    	String[] generatedArray = new String[n];
    	for(int i = 0; i < n; i++) {
    		Random rn = new Random();
    		int letterNum = rn.nextInt(26) + 97;
    		String letter = String.valueOf((char)letterNum);
    		generatedArray[i] = letter;
    	}
    	
        return generatedArray;
    }

    public static CounterExample findCounterExample(Partitioner p) {
    	Random rn = new Random();
    	int testLength = rn.nextInt(20);
    	int testLow;
    	int testHigh;
    	if (testLength == 0) {
    		testLow = 0;
    		testHigh = 0;
    	}
    	else {
    		Random rn2 = new Random();
    		testLow = rn2.nextInt(testLength);
    	
    	
    		Random rn3 = new Random();
    		testHigh = rn3.nextInt(testLength + 1 - testLow) + testLow;
    		if (testHigh == testLow) {
    			testHigh += 1;
    		}
    	}
    	
    	
    	String[] testBefore = generateInput(testLength);
    	String[] testAfter = testBefore.clone();
    	int testPivot = runPartition(p, testAfter, testLow, testHigh);
    	
    	//System.out.println(Arrays.toString(testBefore));
    	//System.out.println(Arrays.toString(testAfter));
    	String testValidity = isValidPartitionResult(testBefore, testLow, testHigh, testPivot, testAfter);
    	
    	if(testValidity == null) {
    		return null;
    	}
    	
        CounterExample output = new CounterExample(testBefore, testLow, testHigh, testPivot, testAfter, testValidity);
        return output;
        
    	
    }

}
