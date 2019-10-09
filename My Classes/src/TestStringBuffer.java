
public class TestStringBuffer {

	public static void main(String[] args) {
		final StringBuffer sb = new StringBuffer("One Two Three");
		System.out.println(sb);
		sb.replace(0, sb.length(), "Three Four Five");
		System.out.println(sb);

	}

}
