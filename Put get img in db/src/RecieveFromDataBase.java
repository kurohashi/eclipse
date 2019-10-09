import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RecieveFromDataBase {

	public static byte[] get(int imgId) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		Statement stmt = null;
		ResultSet rstmt = null;
		Blob blb = null;
		dbConnection = ConnectionMaker.getConnection();
		stmt = dbConnection.createStatement();
		rstmt = stmt.executeQuery("select * from sampStoreImg where id = " + imgId);
		if(rstmt.next()){
			blb = rstmt.getBlob(1);
			byte b[] = blb.getBytes(1,(int) blb.length());
			return b;
		}
		return null;
		
	}

}
