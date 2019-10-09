import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionMaker {
	static Connection getConnection() throws ClassNotFoundException, SQLException{
		Connection connectionLink = null;
		Class.forName("com.mysql.jdbc.Driver");
		connectionLink = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata", "root" , "");
		System.out.println("Connection Done");
		return connectionLink;
		}
	
	static void closeConnection(Connection con, Statement stmt, ResultSet rstst) throws SQLException{
		if(con!=null){
			con.close();
			con=null;
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		if(rstst!=null){
			rstst.close();
			rstst=null;
		}
		
		
	}
	

}
