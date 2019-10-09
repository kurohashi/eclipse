import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;


public class ConnectToDatabase {
	
	public static void putImage(int imgId, String imgUrl) throws IOException, ClassNotFoundException, SQLException {
		File f = new File(imgUrl);
		byte image[] = new byte[(int) f.length()];
		FileInputStream fin = new FileInputStream(f);
		BufferedInputStream bin = new BufferedInputStream(fin);
		bin.read(image);
		bin.close();
		SendToDataBase.put(image, imgId);
		
	}
	
	public static String getImage(int imgId) throws IOException, ClassNotFoundException, SQLException {
		File f = new File("C:\\Users\\Public\\Pictures\\Img.jpg");
		FileOutputStream fout = new FileOutputStream(f);
		BufferedOutputStream bout = new BufferedOutputStream(fout);
		byte[] image = RecieveFromDataBase.get(imgId);
		bout.write(image);
		System.out.println("Image saved at "+f.getPath());
		bout.close();
		return f.getPath();
		
	}

}
