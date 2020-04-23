package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Connection con;
		String driverClass = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "TicketManagement";
		String password = "sidsri";
		Class.forName(driverClass);
		con = DriverManager.getConnection(url,userName,password);
		return con;
	}
}
