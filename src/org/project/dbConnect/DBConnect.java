package org.project.dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnect {

	public static Connection getConnection(){
		Connection conn = null;

	//	String driver = "oracle.jdbc.driver.OracleDriver";
	//	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	//	String user = "system";
	//	String password = "1234";
		
	//	try {
	//		Class.forName(driver);
	//		conn = DriverManager.getConnection(url, user, password);
	//	} catch (ClassNotFoundException e) {
	//		e.printStackTrace();
	//	} catch (SQLException e) {
	//		e.printStackTrace();
	//	} finally {}
		try {
			DataSource dataSource;
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracleDB");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
}
