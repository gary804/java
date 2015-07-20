public class FindSubString {
	public static void main(String[] args){
		if (args.length!=2) {
			System.out.println("usage: FindSubString string substring");
			return;
		}
		for (int i=0; i<args[0].length();i++){
			for (int j=0; i+j<args[0].length(); j++ ) {
				if (args[0].charAt(i+j)!=args[1].charAt(j))
					break;
				if (j==args[1].length()-1) {
					System.out.println("yes");
					return;
				}
			}
		}
		System.out.println("no");
	}
}