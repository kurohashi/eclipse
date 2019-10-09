
public class BoundChecker {

	public static void main(String[] args) {
		StringBuffer s = new StringBuffer("abcd");
		s.replace(4, 5, "0");
		System.out.println(s.toString());
	}

}
