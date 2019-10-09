package executeSQLQuery;

import java.sql.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class ExecuteSQLStatement {
	
	final static String ProFileName = "ConfigMySql.properties";
	
	public void updateDatabase(String sql) {
		
		File f = new File(ProFileName);
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
		Properties pr = new Properties();
		
		try {
			pr.load(fin);
		} catch (IOException e) {
			System.out.println("File " + e);
		}
		
		String driver = pr.getProperty("Driver");
		String url = pr.getProperty("URL");
		String user = pr.getProperty("Username");
		String password = pr.getProperty("Password");
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		int b = 0;
		
		try {
			b = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		if (b == 1) {
			System.out.println("Database Updated");			
		} else {
			System.out.println("Problem Occured");
		}
		
	}
	
	public void showData(String sql) {
		

		File f = new File(ProFileName);
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
		Properties pr = new Properties();
		
		try {
			pr.load(fin);
		} catch (IOException e) {
			System.out.println("File " + e);
		}
		
		String driver = pr.getProperty("Driver");
		String url = pr.getProperty("URL");
		String user = pr.getProperty("Username");
		String password = pr.getProperty("Password");
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);		
			stmt = con.createStatement();		
			rs = stmt.executeQuery(sql);		
			rsmd = rs.getMetaData();	
			int ncol = rsmd.getColumnCount();		
			while (rs.next()) {
				for (int i = 1; i <= ncol; i++) {
					System.out.println(rs.getString(i));
				}				
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

}
