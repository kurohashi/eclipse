class SampleThread extends Thread {
	@Override
	public void run() {
		for(int i = 0; i<10; i++)
			System.out.println("Thread : "+i);
	}
}


public class TestThreading {

	public static void main(String[] args) {
		SampleThread s = new SampleThread();
		s.start();
		for(int i = 0; i<10; i++)
			System.out.println("Main : "+i);

	}

}
