package model;

import java.sql.Connection;
import java.sql.SQLException;

public class Newkeyword {
	private static java.sql.Statement stmt = null;
	private static int rs;
	private static Connection connect;
	public static void saveDocument(String new_key, String link,double best_fitness) {

		 connect = ConnectDatabase.connectDb();

		try {
			stmt = connect.createStatement();
			String sql = "INSERT INTO new_keyword (new_key,link,best_fitness) VALUES('"
					+ new_key + "','" + link + "'," + best_fitness + ")";
			rs = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi save db");
			e.printStackTrace();
		} 
	}
	public static void closeConnect() {
		if (stmt != null) {
			try {
				stmt.close();
				//connect.close();
			} catch (SQLException sqlEx) {
			}
			stmt = null;
		}
		if (connect != null) {
			try {
				connect.close();
				//connect.close();
			} catch (SQLException sqlEx) {
			}
			connect = null;
		}
	}
}
