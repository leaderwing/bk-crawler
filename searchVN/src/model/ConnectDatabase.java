package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
	private static Connection conn = null;

	private static String url = "jdbc:mysql://localhost/";
	private static String dbName = "search43";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "";

	protected static Connection connectDb() {

		try {
			Class.forName(ConnectDatabase.driver).newInstance();
			ConnectDatabase.conn = DriverManager
					.getConnection(
							ConnectDatabase.url
									+ ConnectDatabase.dbName
									+ "?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true",
							ConnectDatabase.userName, ConnectDatabase.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	protected static void closeDb() {
		try {
			ConnectDatabase.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
