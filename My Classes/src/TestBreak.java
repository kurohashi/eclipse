
public class TestBreak {

	public static void main(String[] args) {
		lb:for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.println(i);
				if (i > 2) {
					break lb;
				}
			}
		}

	}

}
