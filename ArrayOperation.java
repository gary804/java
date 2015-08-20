
import java.util.StringTokenizer;
//import java.util.*;	//for Array
import java.util.Arrays;

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

	public static long atoi(String str) {
		long integer=0; 
		int sign=1;
		for (int i=0; i<str.length(); i++) {
			if (str.charAt(i)=='-') {
				sign *= -1;
			} else if (str.charAt(i)=='+') {
				continue;
			} else if (str.charAt(i)=='.') {
				break;
			} else if (str.charAt(i)>='0' && str.charAt(i)<='9') {
				integer = integer*10 + (str.charAt(i) - '0');
			} else return 0;
		}
		if (sign > 0) return integer; else return -integer;
	}

	public static void arrayUpdateTest(int[] array){
//array variable is the reference of original array, not the copy of the array,
// so its change here can be seen outside the function
		array[0]= 100;
	}

	public static String reverse_words(String str){
		StringTokenizer st = new StringTokenizer(str);
		StringBuilder sb = new StringBuilder();
		while(st.hasMoreTokens()){
			sb.insert(0, st.nextToken()+" ");
		}
		sb.trimToSize();
		if (sb.length()-1 >=0) sb.setLength(sb.length()-1);
		return sb.toString();
	}

	public static String reverse_words1(String str){
		String[] sa = str.split("\\s");	//at least one empty string in sa
		StringBuilder sb = new StringBuilder();
		for (int i=0; i< sa.length; i++){
			sb.insert(0, sa[i]+" ");
		}
		sb.trimToSize();
		sb.setLength(sb.length()-1);
		return sb.toString();
	}

	public static String reverse_words2(String str){
		String[] sa = str.split("\\s");
		StringBuilder sb = new StringBuilder(sa[sa.length-1]);
		for (int i=sa.length-2; i>=0; i--){
			sb.append(" "+sa[i]);
		}
		sb.trimToSize();
		return sb.toString();
	}

	public static String reverse_words3(String str){
		String[] sa = str.split("\\s");
		String st = sa[sa.length-1];
		for (int i=sa.length-2; i>=0; i--){
			st+=" "+sa[i];
		}
		return st;
	}

	public static String reverse_characters(String str){
		String[] sa = str.split("");	//split characters
		String st = sa[sa.length-1];
		for (int i=sa.length-2; i>=0; i--){
			st+=sa[i];
		}
		return st;
	}
	public static String reverse_characters1(String str){
		String st = "";
		for (int i=str.length()-1; i>=0; i--){
			st+=str.charAt(i);
		}
		return st;
	}

	public	static int[] merge(int[] a1, int[] a2){
		int[] array = new int[a1.length+a2.length];
		for (int i=0, i1=0,i2=0; i<array.length; i++){
			if (i1<a1.length&&i2<a2.length){
				array[i] = (a1[i1]<=a2[i2])?a1[i1++]:a2[i2++];
			} else if (i1<a1.length) {
				array[i] = a1[i1++];
			} else {array[i] = a2[i2++];}
		}
		return array;
	}

	public static int[] mergeSort(int[] array){
		if (array.length==1) return array;
		int l = array.length/2;
		//System.out.println("l="+l);

		//Arrays.copyOfRange(Object[] src, int from, int to);	//"int to" is not included in the substring
		int[] a1 = Arrays.copyOfRange(array, 0, l);
		int[] a2 = Arrays.copyOfRange(array, l, array.length);
		/*
		for (int i:a1) System.out.print(i+",");
		System.out.println();
		for (int i:a2) System.out.print(i+",");
		System.out.println();
		*/
		int[] a3 = mergeSort(a1);
		int[] a4 = mergeSort(a2);
		int[] a5 = merge(a3, a4);
		return a5;
	}
	public static int[] mergeSortIterate(int[] array){
		int subgroupSize;
		int rightGroupSize;
		for (subgroupSize=1; subgroupSize<=array.length/2;subgroupSize*=2){
			for (int subgroupStartPosition=0; subgroupStartPosition<array.length; subgroupStartPosition+=2*subgroupSize){
				if (subgroupStartPosition+subgroupSize>=array.length){
					break;
				} else if (subgroupStartPosition+2*subgroupSize>array.length){
					rightGroupSize = array.length - (subgroupStartPosition+subgroupSize);
				} else rightGroupSize = subgroupSize;
				
				//int[] a1 = Arrays.copyOfRange(array, subgroupStartPosition, subgroupStartPosition+subgroupSize); //works
				int[] a1 = new int[subgroupSize];
				for (int i=0; i<subgroupSize; i++){ a1[i]=array[subgroupStartPosition+i];}
				
				int[] a2 = Arrays.copyOfRange(array, subgroupStartPosition+subgroupSize, subgroupStartPosition+subgroupSize+rightGroupSize);
				int[] a3 = merge(a1, a2);
				//for (int i=0; i<subgroupSize+rightGroupSize; i++) array[subgroupStartPosition+i] = a3[i];	//works
				for (int i=0; i<a3.length; i++) array[subgroupStartPosition+i] = a3[i];	//works
			}
		}
		int[] a1 = Arrays.copyOfRange(array, 0, subgroupSize);
		int[] a2 = Arrays.copyOfRange(array, subgroupSize, array.length);
		int[] a3 = merge(a1, a2);
		return a3;
	}

	public static void quickSort(int[] array, int startIndex, int endIndex){
		int pivot = array[startIndex+(endIndex-startIndex)/2];
		int i = startIndex;
		int j = endIndex;
		int temp;
		//System.out.println("startIndex="+startIndex+",endIndex="+endIndex+",pivot="+pivot);
		//for (int i1:array) System.out.print(","+i1); System.out.println();
		if ( endIndex-startIndex<=1 ) {
			if (array[startIndex] > array[endIndex]) {
				temp = array[startIndex];
				array[startIndex] = array[endIndex];
				array[endIndex] = temp;
			}
			return;
		}
		while(i<j) {
			while( array[i] < pivot ) {
				i++;
			}
			while( array[j] > pivot ) {
				j--;
			}
			if ( i>=j ) break;
			temp = array[i];
			array[i++] = array[j];
			array[j--] = temp;
		}
		//System.out.println("i="+i+",j="+j+",pivot="+pivot);
		//for (int i1:array) System.out.print(","+i1); System.out.println();
		if ( i-1 - startIndex >=1 ) 
			quickSort(array, startIndex, i-1);
		else
			quickSort(array, startIndex, i);
		if ( endIndex - i >=1 )
			quickSort(array, i, endIndex);
		else
			quickSort(array, i-1, endIndex);
	}

	public static int findKthElement(int[] array, int kth, int startIndex, int endIndex){
		int i = startIndex;
		int j = endIndex;
		int pivot = array[startIndex + (endIndex-startIndex)/2];
		int temp;
		//System.out.println("<<<<<<kth="+kth+",startIndex="+startIndex+",endIndex="+endIndex);
		//for (int i1: array) System.out.print(","+i1); System.out.println();
		if (endIndex-startIndex<=1){
			if (array[startIndex]>array[endIndex]){
				temp = array[startIndex];
				array[startIndex] = array[endIndex];
				array[endIndex] = temp;
			}
			return array[kth-1];
		}
		while(i<j){
			while(array[i]<pivot) i++;
			while(array[j]>pivot) j--;
			if (i>=j) break;
			temp = array[i];
			array[i++] = array[j];
			array[j--] = temp;
		}
		//System.out.println(">>>>>>pivot="+pivot+",i="+i+",j="+j);
		//for (int i1: array) System.out.print(","+i1); System.out.println();

		if (i>=kth){
			//return findKthElement(array, kth, startIndex, (i-1!=startIndex)? i-1:i); //will cause problem
			//return findKthElement(array, kth, 0, (i-1!=startIndex)? i-1:i); //works
			return findKthElement(array, kth, 0, j);
		} else {
			//return findKthElement(array, kth, (endIndex!=i)? i:i-1, endIndex); //works
			return findKthElement(array, kth, i, endIndex);	//works
		}
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

assciToInteger:	{
			String str = "+1234";
			String str1 = "-1234.5";
			String str2 = "-123a4.5";
			System.out.print(""+atoi(str)+", "+atoi(str1)+", "+atoi(str2)+"\n");
		}
reverseStringTest: {
			String str = "Write a function to reverse the order of words in a string in place.";
			System.out.println("'"+reverse_words(str)+"'");
			System.out.println("'"+reverse_words1(str)+"'");
			System.out.println("'"+reverse_words2(str)+"'");
			System.out.println("'"+reverse_words3(str)+"'");
			System.out.println("'"+reverse_words("")+"'");
			System.out.println("'"+reverse_words1("")+"'");
			System.out.println("'"+reverse_words2("")+"'");
			System.out.println("'"+reverse_words3("")+"'");
			System.out.println("'"+reverse_characters(str)+"'");
			System.out.println("'"+reverse_characters1(str)+"'");
		}
SortTesting: {
			System.out.print("Merge test:\n");
			int[] a1 = {0,4,6,8};
			int[] a2 = {5,7,9};
			int[] a3 = merge(a1, a2);
			for(int i: a3){
				System.out.print(i+", ");
			}
			System.out.print("\nMergeSort test:\n");
			int[] a4 = {16,7,13,6,3,11,1,4,12,2,10,0,15,14,5,8,9};
			//int[] a5 = mergeSort(a4);
			int[] a5 = mergeSortIterate(a4);
			for(int i: a5){
				System.out.print(i+", ");
			}
			System.out.print("\nquickSort test:\n");
			int[] a6 = {15,0,8,8,2,6,10,14,4,12,1,11,9,3,7,5,13};
			quickSort(a6, 0, a6.length-1);
			for(int i: a6){
				System.out.print(i+", ");
			}
			System.out.println();

			//int [] a7 = {15,0,8,8,2,6,10,14,4,12,1,11,9,3,7,5,13};
			int [] a7 = {15,0,8,2,6,10,14,4,12,1,11,9,3,7,5,13};
			System.out.print("array is ");for (int i: a7) System.out.print(i+", ");System.out.println();
			int[] a8;
			for (int i=1; i<=a7.length; i++){
				a8 = Arrays.copyOfRange(a7, 0, a7.length);
				System.out.println(i+"th element is "+findKthElement(a8, i, 0, a7.length-1 ));
			}
			
		}
	}
}
