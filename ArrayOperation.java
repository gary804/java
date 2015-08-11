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
		int i=p;
		while (p>-1 && p<array.length) {
			System.out.print("("+p +", "+i+"); ");
			if (array[p]==item){
				return p;
			}
			else if (item>array[p]) {
				if (i<=3) {
					for (p=p+1; i>=0 && p<array.length; i--,p++) {
						System.out.print(p+", ");
						if (item==array[p]) {
							return p;
						}
						else if (item<array[p]) {
							return -1;
						}
					}
					return -1;
				}
				i= (i)/2 ;
				p = (p+i < array.length ) ? p+i : array.length-1;
			}
			else {
				if (i<=3){
					for (p = p-1; i>=0 && p>=0; i--, p--) {
						System.out.print(p+", ");
						if (item==array[p]) {
							return p;
						}
						else if (item>array[p]) {
							return -1;
						}
					}
					return -1;
				}
				i= (i)/2;
				p = (p-i >=0 ) ? p-i : 0;
			}
			System.out.println("("+p +", "+i+"); ");
		}
		//if (array[p]==item) return p; else return -1;
		return -1;
	}

	public static int binarySearch1(int[] array, int item){
		int p=array.length/2;
		int i=p;
		while (true){
			System.out.print("("+p +", "+i+"); ");
			if (array[p]==item){
				return p;
			}
			else if (item>array[p]) {
				if (i<=3) {
					for (int j=1; i>=0 && p+j<array.length; i--, j++){
						System.out.print((p+j)+", ");
						if (item==array[p+j]) return p+j;
					}
					return -1;
				}
				i= (i)/2 ;
				p = (p+i < array.length ) ? p+i : array.length-1;
			}
			else {
				if (i<=3) {
					for (int j=1; i>=0 && p-j>=0; i--, j++){
						System.out.print((p-j)+", ");
						if (item==array[p-j]) return p-j;
					}
					return -1;
				}
				i= (i)/2 ;
				p = (p-i >=0 ) ? p-i : 0;
			}
			System.out.println("("+p +", "+i+"); ");
		}
	}

	public static void main(String[] args) {
		int[] a={0,1,2,3,4,5,7,8,9};
		int[] b={1,2,3,4,8,5,6,7,8,9,0};
/*
		if (hasDuplicate(a)) {
			System.out.println("True");
		} else System.out.println("False");
*/
		System.out.println((""+hasDuplicate(a)).toUpperCase());
		System.out.println((""+hasDuplicate(b)).toUpperCase());
		for (int i=-1; i<11; i++) {
			System.out.println("Looking for "+i+"; its index is: "+binarySearch(a, i));
		}
	}
}
