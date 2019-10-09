package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteSql {
	
	protected Connection con = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	protected ResultSetMetaData rsmd = null;
	
	public ExecuteSql()
	{		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root");
			stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void runQuery(String query) {
		try {
			stmt.execute(query);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public int getItemId(String itemName) {
		int result = 0;
		try {
			rs = stmt.executeQuery("select Id from Item where Name = '" + itemName + "'");
			rs.next();
			result = Integer.parseInt(rs.getString(1));			
		} catch (NumberFormatException | SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	public String[] getData(String billno) {
		String[] result = new String[15];
		int x = 0;
		try {
			rs = stmt.executeQuery("select * from Bill where Billno = '" + billno + "';");
			rsmd = rs.getMetaData();	
			int ncol = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= ncol; i++) {
					result[x++] = rs.getString(i);
				}				
			}
			rs = stmt.executeQuery("select Icode,Qty,Rate,Amt from Billtr where Billno = '" + billno + "';");
			rsmd = rs.getMetaData();
			ncol = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= ncol; i++) {
					result[x++] = rs.getString(i);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
//	public static void main(String[] args) {
//		
//		ExecuteSql exec = new ExecuteSql();
//		exec.getData("B0001");
//		
//	}
	
}
