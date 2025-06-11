// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class CentralPivotPartitioner implements Partitioner {
	
	@Override
	public int partition(String[] strs, int low, int high) {
		// TODO Auto-generated method stub
		if((high-low) <= 1) {
			return low;
		}
		if(strs.length == 0) {
			return 0;
		}
		
		int pivot = ((high-low)/2) + low;
		int rArrow = high -1;
		int lArrow = low;
		
		
		while(lArrow <= rArrow) {
			while(strs[pivot].compareTo(strs[lArrow]) > 0 && lArrow < high - 1) lArrow ++;
			while(strs[pivot].compareTo(strs[rArrow]) <= 0 && rArrow > low) rArrow --;
			if(lArrow <= rArrow && pivot != lArrow) {
				String temp = strs[lArrow];
				strs[lArrow] = strs[rArrow];
				strs[rArrow] = temp;
			} 
			if (rArrow < lArrow && lArrow > pivot && strs[pivot] != strs[lArrow]) {
				String temp = strs[lArrow-1];
				strs[lArrow-1] = strs[pivot];
				strs[pivot] = temp;
				return lArrow -1;
			}
			if(pivot == rArrow || rArrow < lArrow) {
				String temp = strs[lArrow];
				strs[lArrow] = strs[pivot];
				strs[pivot] = temp;
				break;
			}
			if (lArrow == rArrow) {
				String temp = strs[lArrow];
				strs[lArrow] = strs[pivot];
				strs[pivot] = temp;
				break;
			}
			lArrow++;
		}
		return lArrow;
		
	}
}
