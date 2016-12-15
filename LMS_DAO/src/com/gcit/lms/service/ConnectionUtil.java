package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private String driverName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:2579/library";
	private String uname = "root";
	private String pwd = "oogieboogie";

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		Connection conn = DriverManager.getConnection(url, uname, pwd);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}

}