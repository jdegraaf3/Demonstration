// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class FirstElePivotPartitioner implements Partitioner{

	@Override
	public int partition(String[] strs, int low, int high) {
		// TODO Auto-generated method stub
		if(strs.length == 0) {
			return 0;
		}
		if ((high - low) <=1) {
			return low;
		}
		
		int pivot = low;
		int rArrow = high -1;
		int lArrow = low;
		
		//else {
			lArrow = low +1;
			while(lArrow <= rArrow) {
				while(strs[pivot].compareTo(strs[rArrow]) <= 0 && rArrow > low) rArrow --;
				if(rArrow <= lArrow) {
					String temp1 = strs[pivot];
					strs[pivot] = strs[rArrow];
					strs[rArrow] = temp1;
					return rArrow;
				}
			
				while(strs[pivot].compareTo(strs[lArrow]) > 0 && lArrow < high-1) lArrow ++;
				if(lArrow < rArrow && pivot != rArrow) {
					String temp = strs[lArrow];
					strs[lArrow] = strs[rArrow];
					strs[rArrow] = temp;
				}
		
			}
			
			if(rArrow <= lArrow) {
				String temp1 = strs[pivot];
				strs[pivot] = strs[rArrow];
				strs[rArrow] = temp1;
				return rArrow;
			}
			
			return low;
		//}
	
	}
}
