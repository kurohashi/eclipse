import java.util.Scanner;


public class ScannerTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a[] = {sc.nextInt(),sc.nextInt()};
		System.out.println(a[0]+a[1]);
		float f=10f;
		f=f+15;
		System.out.println(f);
		sc.close();
	}

}
