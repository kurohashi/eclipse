import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SendToDataBase {

	public static void put(byte image[], int imgId) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		dbConnection = ConnectionMaker.getConnection();
		pstmt = dbConnection.prepareStatement("insert into sampStoreImg values(?,?)");
		pstmt.setInt(2, imgId);
		pstmt.setObject(1, image);
		pstmt.executeUpdate();
		System.out.println("Image Sent To Database");
		ConnectionMaker.closeConnection(dbConnection, pstmt, null);
		return;
	}

}
