import java.io.IOException;


public class TestRuntime {

	public static void main(String[] args) {
		Runtime r = Runtime.getRuntime();
		try {
			r.exec("time /t");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
