public class ArrayOperation{
	public static boolean hasDuplicate(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i+1; j < array.length; j++) {
				//if (array[i] == array[j] && i != j) {
				if (array[i] == array[j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static int binarySearch(int[] array, int item){
		int p=array.length/2;
		int hl1 = 0, hl2 = p-1;
		int hh1 = p+1, hh2 = array.length - 1;
		while (true){
			System.out.println("("+p +", " + hl1 + ", " + hl2 + ", "  + hh1 + ", " + hh2+"); ");
			if (array[p]==item){
				return p;
			}
			else if (item>array[p]) {
				if (hh2-hh1>1) {
					p = hh1 + (hh2-hh1)/2;
					hl1 = hh1;
					hl2 = p-1;
					hh1 = p+1;
					//hh2=hh2;
				}
				else {
					for (int j=hh1; j<=hh2 ; j++){
						if (item==array[j]) return j;
					}
					return -1;
				}
			}
			else {
				if (hl2-hl1>1) {
					p=hl1+(hl2-hl1)/2;
					hh1 = p+1;
					hh2 = hl2;
					hl2 = p-1;
					//hl1=hl1;
				}
				else {
					for (int j=hl1; j<=hl2; j++){
						if (item==array[j]) return j;
					}
					return -1;
				}
			}
		}
	}

	public static void arrayUpdateTest(int[] array){
//array variable is the reference of original array, not the copy of the array,
// so its change here can be seen outside the function
		array[0]= 100;
	}

	public static void main(String[] args) {
		int[] a={0,1,2,3,4,5,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int[] b={1,2,3,4,8,5,6,7,8,9,0};
/*
		if (hasDuplicate(a)) {
			System.out.println("True");
		} else System.out.println("False");
*/
		System.out.println((""+hasDuplicate(a)).toUpperCase());
		System.out.println((""+hasDuplicate(b)).toUpperCase());
		for (int i=-1; i<a.length; i++) {
			System.out.println("Looking for "+i+"; its index is: "+binarySearch(a, i));
		}
		arrayUpdateTest(b);
		System.out.println(b[0]);	//print 100, so the change in arrayUpdateTest can be seen here
	}
}
