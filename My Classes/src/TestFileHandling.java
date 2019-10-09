import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class TestFileHandling {

	public static void main(String[] args) throws IOException{
		File f = new File("D:\\abc.txt");
		FileOutputStream fout = null;
		fout = new FileOutputStream(f);
		fout.write(65);
		fout.close();
		f = null; 

	}

}
